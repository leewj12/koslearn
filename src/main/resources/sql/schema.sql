CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,              -- 사용자 고유 ID
    username VARCHAR(50) NOT NULL UNIQUE,              -- 사용자 아이디
    password VARCHAR(255) NOT NULL,                    -- 비밀번호
    name VARCHAR(100) NOT NULL,                        -- 이름
    nickname VARCHAR(50),                              -- 닉네임 (선택 사항)
    phone_number VARCHAR(20),                          -- 연락처 (선택 사항)
    email VARCHAR(100) NOT NULL UNIQUE,                -- 이메일 (유니크)
    gender ENUM('male', 'female') NOT NULL,            -- 성별
    role VARCHAR(50) DEFAULT 'STUDENT',                -- 사용자 역할 (기본값 ROLE_STUDENT)
    terms_agreement BOOLEAN NOT NULL,                  -- 정보 동의 여부
    marketing_agreement BOOLEAN NOT NULL,              -- 마케팅 동의 여부
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,    -- 가입일 (자동으로 현재 시간)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 수정일 (자동으로 현재 시간)
);


CREATE TABLE Instructors (
    instructor_id BIGINT NOT NULL AUTO_INCREMENT,   -- 인스트럭터 고유 ID (자동 증가)
    biography TEXT,                                  -- 경력
    introduction TEXT,                               -- 소개
    greeting TEXT,                                   -- 인사말
    user_id BIGINT NOT NULL,                         -- 사용자 ID (Foreign Key)
    origin_filename VARCHAR(255),                    -- 원본 파일명
    hashing_filename VARCHAR(255),                   -- 해싱된 파일명
    filesize BIGINT,                                 -- 파일 크기 (byte 단위)

    PRIMARY KEY (instructor_id),                     -- 인스트럭터 고유 ID를 기본 키로 설정
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE  -- 외래 키 (users 테이블의 id를 참조, 사용자 삭제 시 강사 정보도 삭제)
);

CREATE TABLE courses (
    course_id BIGINT AUTO_INCREMENT PRIMARY KEY,            -- 강의 ID, 자동 증가
    title VARCHAR(255) NOT NULL,                            -- 강의 제목
    description TEXT,                                       -- 강의 설명
    summary TEXT,                                           -- 강의 간단 설명
    url VARCHAR(255) NOT NULL,                              -- 강의 URL
    thumbnail_url VARCHAR(255),                             -- 썸네일 URL
    category VARCHAR(50) NOT NULL,                          -- 강의 카테고리
    image VARCHAR(255),                                     -- 강의 이미지 (파일 경로 또는 UUID 파일명)
    original_image_name VARCHAR(255),                       -- 강의 이미지 원본 파일명
    image_size BIGINT,                                      -- 강의 이미지 파일 크기
    level VARCHAR(50) NOT NULL,                             -- 강의 난이도
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,         -- 강의 등록일, 기본값은 현재 시간
    instructor_id BIGINT NOT NULL,                          -- 강사 ID (Instructors 테이블 참조)
    user_id BIGINT NOT NULL,                                -- 사용자 ID (users 테이블 참조)
    materials TEXT,                                         -- 첨부파일 (파일 경로 또는 UUID 파일명들, 쉼표로 구분)
    original_materials TEXT,                                -- 첨부파일 원본 파일명들 (쉼표로 구분)
    price INT,                                              -- 강의 가격
    FOREIGN KEY (instructor_id) REFERENCES instructors(instructor_id) ON DELETE CASCADE, -- 강사 외래 키
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE  -- 사용자 외래 키
);


CREATE TABLE community_board (
  post_id BIGint NOT NULL AUTO_INCREMENT,            -- 게시글 ID
  title varchar(255) NOT NULL UNIQUE,             -- 제목 (유니크 제약 추가)
  category varchar(255) NOT NULL,                 -- 카테고리
  file_name varchar(255) DEFAULT NULL,            -- 파일 이름
  content text NOT NULL,                          -- 게시글 내용
  user_id BIGint NOT NULL,                           -- 작성자 ID
  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP, -- 생성 시간
  updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 시간
  read_view int DEFAULT 0,                        -- 조회수, 기본값 0
  file_size bigint DEFAULT NULL,                   -- 파일 크기, 기본값 NULL로 변경
  origin_file_name varchar(255) DEFAULT NULL,     -- 원본 파일 이름
  mode varchar(50) DEFAULT NULL,                  -- 새로운 'MODE' 컬럼 추가 (예시: varchar(50))
  PRIMARY KEY (post_id),                          -- 게시글 ID를 기본 키로 설정
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE -- 외래 키 (users 테이블과 연결)
);


CREATE TABLE cart (
    cart_id BIGINT AUTO_INCREMENT PRIMARY KEY,   -- 장바구니 항목 ID
    user_id BIGINT NOT NULL,                      -- 사용자 ID (외래 키)
    course_id BIGINT NOT NULL,                    -- 강좌 ID (외래 키)
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 추가 시간 (기본 값: 현재 시간)
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, -- 사용자 외래 키 제약
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE -- 강좌 외래 키 제약
);


CREATE TABLE users_image (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    origin_filename VARCHAR(255) NOT NULL,
    hashing_filename VARCHAR(255) NOT NULL,
    filesize BIGINT NOT NULL, -- 파일 크기 (byte 단위로 저장)
    user_id BIGINT -- 사용자 ID (외래 키)
 -- 외래 키 설정 (users 테이블의 user_id와 연결)
);


CREATE TABLE Payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,  -- 데이터 타입을 BIGINT로 수정
    course_id BIGINT,  -- 데이터 타입을 BIGINT로 수정
    amount DECIMAL(10, 2),
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method VARCHAR(50),
    payment_status ENUM('PENDING', 'SUCCESS', 'FAILED', 'CANCELLED') DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,  -- 외래 키 관계 설정
    FOREIGN KEY (course_id) REFERENCES Courses(course_id) ON DELETE CASCADE  -- 외래 키 관계 설정
);


CREATE TABLE notice (
    notice_id INT AUTO_INCREMENT PRIMARY KEY, -- 게시글 고유 ID
    user_id BIGINT NOT NULL, -- 작성자 ID (외래키로 사용될 수 있음)
    title VARCHAR(255) NOT NULL, -- 공지 제목
    content TEXT NOT NULL, -- 공지 내용

    -- 이미지 관련 컬럼
    original_thumbnail_image VARCHAR(255), -- 원본 썸네일 이미지 파일명
    hashed_thumbnail_image VARCHAR(255), -- 해싱된 썸네일 이미지 파일명
    original_banner_image VARCHAR(255), -- 원본 배너 이미지 파일명
    hashed_banner_image VARCHAR(255), -- 해싱된 배너 이미지 파일명

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 작성일자 (자동으로 현재 시간)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일자 (자동 갱신)
    view_count INT DEFAULT 0, -- 조회수, 기본값 0

    FOREIGN KEY (user_id) REFERENCES Users(id) -- 사용자 ID에 대한 외래키
);


CREATE TABLE reviews (
    review_id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- 리뷰 ID
    review_text TEXT,                                 -- 리뷰 내용
    course_rating INT,                                -- 강의 별점
    instructor_rating INT,                            -- 강사 별점
    course_id BIGINT,                                 -- 강의 ID (foreign key)
    user_id BIGINT,                                   -- 사용자 ID (foreign key) 리뷰 작성자
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   -- 리뷰 작성일
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 리뷰 수정일
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE,  -- 강의 외래키
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE   -- 사용자 외래키
);

create table communityreview(
      review_id BIGint NOT NULL AUTO_INCREMENT,
        username varchar(50) NOT NULL,
        content varchar(255) NOT NULL,
        postId BIGint NOT NULL,
        PRIMARY KEY (review_id),
        FOREIGN KEY (postId) REFERENCES community_board(post_id) ON DELETE CASCADE
);