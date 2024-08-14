package com.sparta.schedulertask.dto;

import com.sparta.schedulertask.entity.Scheduler;
import lombok.Getter;

import java.util.Date;

@Getter
public class SchedulerResponseDto {

    private Long id;
    private String username;
    private String contents;
    private Date createDate;
    private Date updateDate;

    public SchedulerResponseDto(Scheduler scheduler) {
        this.id = scheduler.getId();
        this.username = scheduler.getUsername();
        this.contents = scheduler.getContents();
        this.createDate = scheduler.getCreateDate();
        this.updateDate = scheduler.getUpdateDate();
    }
}
