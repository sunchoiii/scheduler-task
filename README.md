
### API 명세서

| 기능       | Method | URL                                    | request |response|
|----------|------|----------------------------------------|------|---|
| 일정 등록    | POST | /api/scheduler  | {
"username": "담당자명",
"contents": "일정내용",
"password": "비밀번호"
} |{
"id": 1,
"username": "담당자명",
"contents": "일정내용",
"createDate": "작성일",
"updateDate": "수정일"
}|
| 일정 조회    | GET  | /api/scheduler/{id}| {
"id": 1
} |{
"id": 1,
"username": "담당자명",
"contents": "일정내용",
"createDate": "작성일",
"updateDate": "수정일"
}|
| 일정 목록 조회 | GET  | /api/scheduler/{updateDate}/{username} | {
"updateDate": "수정일",
"username": "담당자명"
} |[{
"id": 1,
"username": "담당자명",
"contents": "일정내용",
"createDate": "작성일",
"updateDate": "수정일"
}]|
| 일정 수정    | PUT  | /api/scheduler/{id}/{password}| {
"id": 1,
"password": "비밀번호"
} {
"username":"수정할 담담자명",
"contents":"수정할 일정내용"
}|{
"id": 1,
"username": "담당자명",
"contents": "일정내용",
"createDate": "작성일",
"updateDate": "수정일"
}|
| 일정 삭제    | DELETE | /api/scheduler/{id}/{password}| {
"id": 1,
"password": "비밀번호"
} | id + " 번의 일정이 삭제되었습니다."|



### ERD

<img width="439" alt="스크린샷 2024-08-13 오후 9 33 53" src="https://github.com/user-attachments/assets/d02bdbb6-aa43-4d20-9a61-f321a223e55d">
