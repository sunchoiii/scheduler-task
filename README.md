
### API 명세서

| 기능       | Method | URL                                    | request |response|
|---------|----|-----------------------|-------------|--------------|
| 일정 등록    | POST | /api/scheduler  | {"username": "담당자명", "contents": "일정내용", "password": "비밀번호"} |{"id": 1,"username": "담당자명","contents": "일정내용","createDate": "작성일","updateDate": "수정일"}|
| 일정 조회    | GET  | /api/scheduler/{id}| {"id": 1} |{"id": 1,"username": "담당자명","contents": "일정내용","createDate": "작성일","updateDate": "수정일"}|
| 일정 목록 조회 | GET  | /api/scheduler/{updateDate}/{username} | {"updateDate": "수정일","username": "담당자명"} |[{"id": 1,"username": "담당자명","contents": "일정내용","createDate": "작성일","updateDate": "수정일"}]|
| 일정 수정    | PUT  | /api/scheduler/{id}/{password}| {"id": 1,"password": "비밀번호"}|{"id": 1,"username": "담당자명","contents": "일정내용","createDate": "작성일","updateDate": "수정일"}|
| 일정 삭제    | DELETE | /api/scheduler/{id}/{password}| {"id": 1,"password": "비밀번호"} | id + " 번의 일정이 삭제되었습니다."|

<br>

### ERD

![_scheduler-task.png](../../../Downloads/_scheduler-task.png)