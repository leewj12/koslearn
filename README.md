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
| 서버 포트 | 9090 (로컬) / 8080 (Docker 컨테이너 내부) |
| 데이터베이스 | MySQL 8.0, 스키마: `project` |
| 배포 주소 | https://koslearn.wonjae.cloud |

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
| Infra | AWS EC2 t3.small, Nginx, Let's Encrypt (HTTPS) |

---

## 아키텍처

```
[Browser]
    │
    ▼
[Nginx :80/:443]  ← HTTPS (Let's Encrypt)
    │
    ▼
[Spring Boot App :8080]
    ├── Controller (Thymeleaf MVC)
    ├── RestController (JSON API)
    ├── Service
    ├── MyBatis Mapper ──── [MySQL :3306]
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
CREATE DATABASE project CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

`src/main/resources/sql/schema.sql`을 실행하여 테이블을 생성합니다.

```bash
mysql -u root -p project < src/main/resources/sql/schema.sql
```

### 2. 환경 설정

`src/main/resources/application-local.yml`을 생성합니다 (`.gitignore`에 포함되어 커밋되지 않습니다).

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

- Docker Desktop

### 1. 환경 변수 파일 준비

```bash
cp .env.example .env
# .env 파일을 열어 비밀번호 및 메일 정보 입력
```

### 2. JAR 빌드

```bash
./gradlew bootJar -x test
```

### 3. 빌드 및 실행

```bash
docker compose up --build
```

MySQL 헬스체크가 통과된 후 앱이 기동됩니다 (약 1~2분 소요).

### 4. 접속

`http://localhost:9090`

### 5. 종료

```bash
docker compose down          # 컨테이너 종료 (데이터 유지)
docker compose down -v       # 컨테이너 + 볼륨 삭제 (DB + 업로드 파일 초기화)
```

---

## CI/CD (GitHub Actions)

`main` 브랜치에 푸시하면 자동으로 빌드 → EC2 배포가 실행됩니다.

### GitHub Secrets 설정

GitHub 레포 → Settings → Secrets and variables → Actions에서 아래 3개를 등록합니다.

| 키 | 값 |
|----|-----|
| `EC2_HOST` | EC2 퍼블릭 IP 또는 도메인 |
| `EC2_USER` | `ubuntu` |
| `EC2_SSH_KEY` | EC2 접속용 `.pem` 파일 내용 전체 (`-----BEGIN RSA PRIVATE KEY-----` 포함) |

### EC2 사전 설정 (최초 1회)

```bash
# EC2에서
mkdir -p ~/koslearn
cd ~/koslearn
# .env 파일 작성
cp /path/to/.env.example .env
vi .env
# docker-compose.yml 준비 (git clone 또는 직접 작성)
```

### 동작 방식

1. GitHub Actions 러너에서 `./gradlew bootJar` 빌드
2. 빌드된 JAR을 EC2의 `~/koslearn/app.jar`로 SCP
3. SSH로 `docker compose up -d --build` 실행

### 업로드 파일 영속성

런타임에 업로드된 파일은 Docker named volume에 저장되어 컨테이너 재시작 후에도 유지됩니다.

| 볼륨명 | 컨테이너 내 경로 | 용도 |
|--------|-----------------|------|
| `courseimages` | `/app/src/main/resources/courseimages` | 강의 썸네일 이미지 |
| `coursefiles` | `/app/src/main/resources/coursefiles` | 강의 첨부파일 |
| `instructor_images` | `/app/src/main/resources/Instructorimages` | 강사 프로필 이미지 |
| `userimages` | `/app/src/main/resources/userimages` | 사용자 프로필 이미지 |
| `notice` | `/app/src/main/resources/notice` | 공지사항 첨부파일 |
| `communityupload` | `/app/src/main/resources/communityupload` | 커뮤니티 첨부파일 |

---

## EC2 배포

- **서버**: AWS EC2 t3.small (Ubuntu 24.04 LTS)
- **도메인**: [koslearn.wonjae.cloud](https://koslearn.wonjae.cloud)
- **SSL**: Let's Encrypt (Certbot, 자동 갱신)
- **리버스 프록시**: Nginx (포트 80/443 → 앱 8080)
- **실행 방식**: Docker Compose (mysql + app 컨테이너)
- **재시작 정책**: 인스턴스 재부팅 시 crontab으로 자동 기동

### Nginx 설정

```nginx
server {
    listen 80;
    server_name wonjae.cloud www.wonjae.cloud;
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

### 멀티 프로젝트 포트 계획 (동일 인스턴스)

| 프로젝트 | 앱 포트 |
|----------|---------|
| proj2 (KosLearn) | 9090 |
| proj3 | 9091 |
| proj4 | 9092 |

---

## 환경 변수

`.env.example`을 복사하여 `.env`를 생성한 뒤 실제 값을 입력합니다.

| 변수명 | 설명 |
|--------|------|
| `MYSQL_ROOT_PASSWORD` | MySQL root 비밀번호 |
| `MAIL_HOST` | SMTP 서버 (예: `smtp.gmail.com`) |
| `MAIL_USERNAME` | 발신 이메일 주소 |
| `MAIL_PASSWORD` | Gmail 앱 비밀번호 |
