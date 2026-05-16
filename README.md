# KosLearn — 온라인 강의 판매 플랫폼

Spring Boot 기반의 온라인 강의 판매 팀 프로젝트입니다.  
수강생·강사·관리자 역할을 구분하고, 강의 구매부터 수강까지 전 과정을 지원합니다.

---

## 목차

1. [프로젝트 소개](#프로젝트-소개)
2. [주요 기능](#주요-기능)
3. [기술 스택](#기술-스택)
4. [아키텍처](#아키텍처)
5. [실행 방법](#실행-방법)
6. [Docker 실행](#docker-실행)
7. [EC2 배포](#ec2-배포)
8. [환경 변수](#환경-변수)

---

## 프로젝트 소개

| 항목 | 내용 |
|------|------|
| 프로젝트 유형 | 팀 프로젝트 (포트폴리오) |
| 개발 기간 | 2024.11 ~ 2024.12 |
| 서버 포트 | 9090 (로컬) / 8080 (Docker) |
| 데이터베이스 | MySQL 8.0, 스키마: `proj2_db` |

---

## 주요 기능

### 회원
- 회원가입 / 로그인 / 로그아웃 (Spring Security)
- 아이디 찾기 / 이메일 인증 기반 비밀번호 재설정
- 프로필 이미지 업로드 및 수정

### 수강생
- 강의 목록 검색 및 카테고리 필터링
- 강의 상세 보기 (커리큘럼, 강사 소개, 리뷰)
- 장바구니 담기 및 결제 (Portone 연동)
- 구매한 강의 수강 페이지
- 수강한 강의에 리뷰 작성

### 강사
- 강사 프로필 등록 및 수정
- 강의 등록 (이미지 + 첨부자료 업로드)
- 내 강의 목록 및 평점 확인

### 커뮤니티
- 게시글 작성 / 수정 / 삭제 (파일 첨부 포함)
- 댓글 기능
- 페이징 처리

### 공지사항
- 관리자 공지사항 등록 (이미지 포함)
- 공지사항 조회 수 집계

### 관리자
- 회원 목록 조회 / 검색 / 삭제
- 강의 목록 관리 및 삭제
- 결제 내역 조회

---

## 기술 스택

| 구분 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.3.4 |
| ORM | MyBatis 3.0, Spring Data JPA |
| View | Thymeleaf |
| Security | Spring Security 6 |
| Database | MySQL 8.0 |
| Build | Gradle |
| Mail | Spring Mail (Gmail SMTP) |
| Container | Docker, Docker Compose |
| Infra | AWS EC2, Nginx |

---

## 아키텍처

```
[Browser]
    │
    ▼
[Nginx]  ──── 리버스 프록시 (80 → 8080)
    │
    ▼
[Spring Boot App]
    ├── Controller (Thymeleaf MVC)
    ├── RestController (JSON API)
    ├── Service
    ├── MyBatis Mapper ──── [MySQL]
    └── Spring Security (STUDENT / INSTRUCTOR / ADMIN)
```

---

## 실행 방법

### 사전 요구사항

- Java 17
- MySQL 8.0
- Gradle (gradlew 포함)

### 1. 데이터베이스 준비

```sql
CREATE DATABASE proj2_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

`src/main/resources/sql/schema.sql`을 실행하여 테이블을 생성합니다.

```bash
mysql -u root -p proj2_db < src/main/resources/sql/schema.sql
```

### 2. 환경 설정

`src/main/resources/application-local.yml`을 생성합니다 (`.gitignore`에 포함, 커밋되지 않습니다).

```yaml
spring:
  datasource:
    username: root
    password: your_mysql_password

  mail:
    host: smtp.gmail.com
    username: your_email@gmail.com
    password: your_gmail_app_password
```

### 3. 실행

```bash
./gradlew bootRun
```

브라우저에서 `http://localhost:9090` 접속

---

## Docker 실행

### 사전 요구사항

- Docker Desktop (또는 Docker Engine + Docker Compose)

### 1. 환경 변수 파일 준비

```bash
cp .env.example .env
# .env 파일을 열어 비밀번호 및 메일 정보 입력
```

### 2. 빌드 및 실행

```bash
docker compose up --build
```

처음 실행 시 Gradle 빌드와 MySQL 초기화가 함께 진행됩니다.  
MySQL 헬스체크가 통과된 후 앱이 기동됩니다 (약 2~3분 소요).

### 3. 접속

`http://localhost:9090`

### 4. 종료

```bash
docker compose down          # 컨테이너 종료 (데이터 유지)
docker compose down -v       # 컨테이너 + 볼륨 삭제 (DB 초기화)
```

---

## EC2 배포

### 환경

- AWS EC2 t3.small (Ubuntu 22.04)
- OpenJDK 17, Docker, Nginx

### 1. EC2 기본 설정

```bash
sudo apt update && sudo apt install -y docker.io docker-compose-plugin nginx
sudo usermod -aG docker $USER
```

### 2. 프로젝트 복사 및 실행

```bash
git clone <repo-url>
cd koslearn
cp .env.example .env && vi .env   # 실제 값 입력

docker compose up -d --build
```

### 3. Nginx 리버스 프록시 설정

`/etc/nginx/sites-available/koslearn` 생성:

```nginx
server {
    listen 80;
    server_name your-domain.com;   # 도메인 또는 EC2 퍼블릭 IP

    client_max_body_size 100M;

    location / {
        proxy_pass         http://localhost:9090;
        proxy_set_header   Host $host;
        proxy_set_header   X-Real-IP $remote_addr;
        proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header   X-Forwarded-Proto $scheme;
    }
}
```

```bash
sudo ln -s /etc/nginx/sites-available/koslearn /etc/nginx/sites-enabled/
sudo nginx -t && sudo systemctl reload nginx
```

### 4. HTTPS 설정 (Let's Encrypt)

```bash
sudo apt install -y certbot python3-certbot-nginx
sudo certbot --nginx -d your-domain.com
```

### 5. 보안 그룹 (AWS 콘솔)

| 포트 | 프로토콜 | 허용 대상 |
|------|----------|-----------|
| 22 | TCP | 내 IP |
| 80 | TCP | 0.0.0.0/0 |
| 443 | TCP | 0.0.0.0/0 |

> 9090 포트는 외부에 열지 않습니다. Nginx를 통해서만 접근합니다.

### 멀티 프로젝트 운영 (proj3, proj4 예정)

같은 EC2에 여러 프로젝트를 올릴 경우 각 앱 포트를 다르게 설정합니다.

| 프로젝트 | 앱 포트 | Nginx 경로 |
|----------|---------|------------|
| proj2 (KosLearn) | 9090 | `/` 또는 서브도메인 |
| proj3 | 9091 | 서브도메인 또는 `/proj3/` |
| proj4 | 9092 | 서브도메인 또는 `/proj4/` |

---

## 환경 변수

| 변수명 | 설명 |
|--------|------|
| `MYSQL_ROOT_PASSWORD` | MySQL root 비밀번호 |
| `DB_USERNAME` | DB 접속 계정 (docker 환경: `root`) |
| `DB_PASSWORD` | DB 접속 비밀번호 |
| `MAIL_HOST` | SMTP 서버 (예: `smtp.gmail.com`) |
| `MAIL_USERNAME` | 발신 이메일 주소 |
| `MAIL_PASSWORD` | Gmail 앱 비밀번호 |
