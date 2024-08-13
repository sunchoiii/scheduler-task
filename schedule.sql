CREATE TABLE IF NOT EXISTS SCHEDULER
(
    schedulerId varchar(100) primary key comment '일정 ID',
    username varchar(100)  not null comment '담당자명',
    password int not null comment '비밀번호',
    contents varchar(100) not null comment '할일',
    createDate datetime not null comment '작성일',
    updateDate datetime not null comment '수정일'
);
