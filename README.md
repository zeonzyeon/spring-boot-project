## 블로그 만들기

- `Spring Boot`로 API 구현
- `Thymleaf`를 이용하여 Blog 화면 구성
- `JavaScript`로 버튼 기능 구현

<br>

## 책 정보 동적 페이지

- `Spring Boot`로 API 구현
- `Thymleaf`를 이용하여 Form 작성

<br>

## Filter / Intercepter

- 클라이언트의 요청이 처리되는 순서

> HTTP 요청 → 필터 → 인터셉터 → Controller → 인터셉터 → 필터 → 응답

<br>

## 관점 지향 프로그래밍 (AOP)

- `TimeLoggingAop`: 클래스로 호출 시간을 로깅하는 AOP

<br>

## Spring Security

- 회원 도메인
  1. 회원 정보를 저장할 테이블을 만들고 테이블과 연결할 도메인 만들기
  2. 이 테이블과 연결할 엔티티 만들기
  3. 회원 엔티티와 연결되어 데이터를 조회하게 해줄 레파지토리 만들기
  4. 스프링 시큐리티에서 사용자 정보를 가져오는 서비스 만들기


- 시큐리티 설정
  - `WebSecurityConfig`: 실제 인증처리를 하는 시큐리티 설정 파일


- 회원 가입 / 로그아웃 구현
- `Thymleaf`를 이용하여 회원 기입 / 로그인 / 로그아웃 뷰 작성
- 권한 추가

<br>

## API 문서 자동화 - Swagger UI

- `SwaggerConfig` 설정
- `'/swagger-ui/index.html'` 경로로 접속하여 확인
- `@Tag`, `@Operation`, `@ApiResponses` API 어노테이션 설정

<br>

## REST API 구현

- `MySQL` DB로 연결
- 댓글 정보 comment 테이블 추가
- 댓글 정보를 조회, 생성, 수정, 삭제하는 `REST API` 구현

<br>

## AWS EC2 서비스에 배포

- `Jasypt`를 통해 민감한 정보 암호화 (AES 알고리즘)

<br>

## Docker

- `Docker Compose`: `docker-compose.yml` 파일을 사용해 여러 컨테이너를 쉽게 설정하고 실행
- `Docker Image`: `Dockerfile`을 기반으로 애플리케이션의 컨테이너 이미지를 생성


