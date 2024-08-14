package com.sparta.schedulertask.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class SchedulerRequestDto {
    private String username;
    private String contents;
    private String password;
    private Date createDate;
    private Date updateDate;

}
