# Spring Boot SSR 방명록 CRUD 과제

본 프로젝트는 Spring Boot와 Mustache를 활용하여 구현한 간단한 방명록(Guestbook) 웹 애플리케이션입니다. 기본적인 CRUD 흐름과 SSR(Server-Side Rendering) 방식의 데이터 처리를 중점적으로 구현하였습니다.

## 🚀 주요 기능 및 구현 사항

### 1. Guestbook Entity 및 JpaRepository (배점: 20점)
- **Entity (`Guestbook.java`)**: 
  - `id` (Long, PK, Auto-increment), `author` (String), `content` (String), `createdAt` (LocalDateTime) 필드를 정의했습니다.
  - `@Data` (Lombok)를 사용하여 Getter/Setter를 자동 생성하였으며, `createdAt`은 객체 생성 시 현재 시간으로 자동 초기화되도록 구현했습니다.
- **Repository (`GuestbookRepository.java`)**: 
  - `JpaRepository`를 상속받아 기본 CRUD 기능을 확보했습니다.
  - 요구사항인 '최신순 정렬'을 위해 `findAllByOrderByIdDesc()` 메서드를 정의하여 최신 데이터가 상단에 오도록 처리했습니다.

### 2. Controller 구현 (배점: 30점)
- **Endpoint (`GuestbookController.java`)**:
  - `GET /guestbook`: 전체 방명록 목록을 최신순으로 조회하여 Model에 담아 전달합니다.
  - `GET /guestbook/write`: 방명록 작성을 위한 폼 페이지로 이동합니다.
  - `POST /guestbook/write`: 사용자가 입력한 데이터를 저장하고 목록 페이지로 리다이렉트합니다.
  - `POST /guestbook/{id}/delete`: 특정 ID의 방명록을 삭제하고 목록 페이지로 리다이렉트합니다.

### 3. Mustache 템플릿 구현 (배점: 30점)
- **목록 페이지 (`list.mustache`)**: 
  - `{{#list}}` 문법을 사용하여 방명록 목록을 반복 출력합니다.
  - 작성자, 내용, 작성일을 테이블 형식으로 표시하며, 각 행마다 **삭제 버튼**을 포함한 POST 방식의 폼을 구현했습니다.
- **작성 페이지 (`write.mustache`)**: 
  - `<form action="/guestbook/write" method="post">`를 사용하여 데이터를 서버로 전송할 수 있도록 구성했습니다.

### 4. CRUD 흐름 및 리다이렉트 처리 (배점: 20점)
- 저장 및 삭제 성공 후 `redirect:/guestbook`을 호출하여 사용자 경험을 개선하고 데이터 정합성을 유지했습니다.
- H2 Console 또는 기타 DB 설정을 통해 데이터가 정상적으로 영속화되는 것을 확인하였습니다.

---

## 📂 프로젝트 구조
```text
src/main/java/com/example/demo/
├── DemoApplication.java
├── Guestbook.java             # Entity
├── GuestbookRepository.java   # JpaRepository
└── GuestbookController.java   # Controller

src/main/resources/
├── templates/
│   └── guestbook/
│       ├── list.mustache      # 목록 페이지
│       └── write.mustache     # 작성 폼 페이지
└── application.properties     # 설정 파일
```

## 🛠 실행 방법
1. 프로젝트를 빌드합니다: `./gradlew build`
2. 애플리케이션을 실행합니다: `./gradlew bootRun`
3. 브라우저에서 `http://localhost:8080/guestbook`으로 접속합니다.

