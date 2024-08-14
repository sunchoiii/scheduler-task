package com.sparta.schedulertask.controller;

import com.sparta.schedulertask.dto.SchedulerRequestDto;
import com.sparta.schedulertask.dto.SchedulerResponseDto;
import com.sparta.schedulertask.entity.Scheduler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    private final JdbcTemplate jdbcTemplate;

    public SchedulerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //일정 등록
    @PostMapping("/scheduler")
    public SchedulerResponseDto createScheduler (@RequestBody SchedulerRequestDto schedulerRequestDto) {
        // RequestDto -> Entity
        Scheduler scheduler = new Scheduler(schedulerRequestDto);
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        // 작성일, 수정일 현재날짜와 시간 자동출력
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        scheduler.setCreateDate(currentTimestamp);
        scheduler.setUpdateDate(currentTimestamp);

        String sql = "INSERT INTO scheduler (username, contents, password, createDate, updateDate) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, scheduler.getUsername());
                    preparedStatement.setString(2, scheduler.getContents());
                    preparedStatement.setString(3, scheduler.getPassword());
                    preparedStatement.setObject(4, scheduler.getCreateDate());
                    preparedStatement.setObject(5, scheduler.getUpdateDate());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        scheduler.setId(id);

        // Entity -> ResponseDto
        SchedulerResponseDto schedulerResponseDto = new SchedulerResponseDto(scheduler);

        return schedulerResponseDto;
    }











}
