package com.sparta.schedulertask.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SchedulerRequestDto {
    private Long id;
    private String username;
    private String contents;
    private String password;
    private Date createDate;
    private Date updateDate;


}
