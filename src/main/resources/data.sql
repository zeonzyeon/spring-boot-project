-- ARTICLE 테이블 생성
CREATE TABLE article (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         content TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- BOOK 테이블 생성
CREATE TABLE book (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      author VARCHAR(255) NOT NULL
);

-- 초기 데이터 삽입
INSERT INTO article (title, content, created_at, updated_at)
VALUES ('제목1', '내용1', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at)
VALUES ('제목2', '내용2', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at)
VALUES ('제목3', '내용3', NOW(), NOW());

INSERT INTO book(name, author)
VALUES ('JPA 프로그래밍', '김영한');
INSERT INTO book(name, author)
VALUES ('채식주의자', '한강');