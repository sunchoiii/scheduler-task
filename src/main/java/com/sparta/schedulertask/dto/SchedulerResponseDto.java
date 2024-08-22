package com.sparta.schedulertask.dto;

import com.sparta.schedulertask.entity.Scheduler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SchedulerResponseDto {
    private Long id;
    private String username;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public SchedulerResponseDto(Scheduler scheduler) {
        this.id = scheduler.getId();
        this.username = scheduler.getUsername();
        this.contents = scheduler.getContents();
        this.createDate = scheduler.getCreateDate();
        this.updateDate = scheduler.getUpdateDate();
    }

}
