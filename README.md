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


