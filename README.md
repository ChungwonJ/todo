# todo
이 프로젝트는 Todo 항목을 관리하기 위한 RESTful API입니다. 사용자는 Todo 항목을 생성, 조회, 수정, 삭제할 수 있습니다.
# API 명세서
| 기능       | Method | URL              | Request Body                                             | Response                                                 |
|------------|--------|------------------|---------------------------------------------------------|---------------------------------------------------------|
| 입력       | POST   | /todos           | { "username": "이름", "contents": "내용", "password": "비밀번호" } | { "id": 1, "username": "이름", "contents": "내용", "date": "2025-02-04T10:31:15.177278" } |
| 전체 조회  | GET    | /todos           | -                                                       | [ { "id": 1, "username": "이름", "contents": "컨텐츠", "date": "2025-01-27T20:21:07" }, { "id": 2, "username": "이름1", "contents": "컨텐츠1", "date": "2025-02-03T15:00:01" } ] |
| 단건 조회  | GET    | /todos/{id}     | -                                                       | { "id": 1, "username": "이름", "contents": "컨텐츠", "date": "2025-01-27T20:21:07" } |
| 수정       | PUT    | /todos/{id}     | { "username": "새로운 작성자명1", "contents": "수정할 할일 내용1", "password": "비밀번호12" } | { "id": 1, "username": "새로운 작성자명1", "contents": "수정할 할일 내용1", "date": "2025-02-04T11:08:02" } |
| 삭제       | DELETE | /todos/{id}     | { "password": "비밀번호12" }                           | -                                                       |

# ERD
| Column   | Type        | Null | Key | Comment             |
|----------|-------------|------|-----|---------------------|
| id       | BIGINT     | NO   | PRI | 메모 식별자         |
| username | VARCHAR(100)| NO   |     | 이름                |
| contents | TEXT       | YES  |     | 내용                |
| password | VARCHAR(100)| NO   |     | 비밀번호            |
| date     | DATETIME(0)| YES  |     | 작성일              |

