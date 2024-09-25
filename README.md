
# API 명세서

| 기능 | Method | URL | request |response|
|:---:|:---:|:---:|:---|:---|
| 일정 등록 | POST | /api/scheduler  | {<br>"username": "담당자명", <br>"contents": "일정내용", <br>"password": "비밀번호"<br>} |{<br>"id": 1,<br>"username": "담당자명",<br>"contents": "일정내용",<br>"createDate": "작성일",<br>"updateDate": "수정일"<br>}|
| 일정 조회 | GET  | /api/scheduler/{id}| {<br>"id": 1<br>} |{<br>"id": 1,<br>"username": "담당자명",<br>"contents": "일정내용",<br>"createDate": "작성일",<br>"updateDate": "수정일"<br>}|
| 일정 목록 조회 | GET  | /api/scheduler/{updateDate}/{username} | {<br>"updateDate": "수정일",<br>"username": "담당자명"<br>} |[{<br>"id": 1,<br>"username": "담당자명",<br>"contents": "일정내용",<br>"createDate": "작성일",<br>"updateDate": "수정일"<br>}]|
| 일정 수정 | PUT  | /api/scheduler/{id}/{password}| {<br>"id": 1,<br>"password": "비밀번호"<br>}|{<br>"id": 1,<br>"username": "담당자명",<br>"contents": "일정내용",<br>"createDate": "작성일",<br>"updateDate": "수정일"<br>}|
| 일정 삭제 | DELETE | /api/scheduler/{id}/{password}| {<br>"id": 1,<br>"password": "비밀번호"<br>} | id + " 번의 일정이 삭제되었습니다."|

<br>

# ERD

<img width="412" alt="스크린샷 2024-08-15 오후 6 59 17" src="https://github.com/user-attachments/assets/f78f2b88-74f6-446b-9198-dda7a12a5938">
