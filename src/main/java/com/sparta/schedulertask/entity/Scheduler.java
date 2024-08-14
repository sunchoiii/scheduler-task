package com.sparta.schedulertask.entity;

import com.sparta.schedulertask.dto.SchedulerRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Scheduler {

    private Long id;
    private String username;
    private String contents;
    private String password;
    private Date createDate;
    private Date updateDate;


    public Scheduler(SchedulerRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.createDate = requestDto.getCreateDate();
        this.updateDate = requestDto.getUpdateDate();

    }
}
