create table SCHEDULER
(
    id         bigint auto_increment comment '일정 ID'
        primary key,
    username   varchar(100)                       not null comment '담당자명',
    password   varchar(50)                        not null comment '비밀번호',
    contents   varchar(100)                       not null comment '할일',
    createDate datetime default CURRENT_TIMESTAMP not null comment '작성일',
    updateDate datetime default CURRENT_TIMESTAMP not null comment '수정일'
);

