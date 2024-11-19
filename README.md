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

<br>

## Test Code

- 단위 테스트 프레임워크 `JUnit`
- 자주 사용하는 `JUnit` 어노테이션: `@BeforeAll`, `@BeforeEach`, `@AfterAll`, `@AfterEach`

<br>

## 테스트 주도 개발 (TDD)

- 테스트 주도 개발의 진행 방식
  - TDD를 이용한 개발은 크게 `질문 → 응답 → 정제` 세 단계가 반복적으로 이루어진다.

> 질문 : 테스트 작성을 통해 시스템에 질문 (테스트 수행 결과는 실패) <br>
> 응답 : 테스트를 통과하는 코드를 작성해서 질문에 대답 (테스트 성공) <br>
> 정제 : 아이디어를 통합, 불필요한 것은 제거, 모호한 것은 명확하게 해서 대답을 정제 (리팩토링)

- 은행 계좌 입금 / 출금 서비스

> 1. 계죄 생성
> 2. 잔고 조회
> 3. 입금과 출금



