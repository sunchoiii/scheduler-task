package com.sparta.schedulertask.repository;

import com.sparta.schedulertask.dto.SchedulerResponseDto;
import com.sparta.schedulertask.entity.Scheduler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

public class SchedulerRepository {

    private final JdbcTemplate jdbcTemplate;

    public SchedulerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Scheduler save(Scheduler scheduler) {
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

        return scheduler;
    }

    public SchedulerResponseDto findById (Long id) {
        Scheduler scheduler = new Scheduler();
        // DB 조회
        String sql = "SELECT * FROM scheduler where id = ?";

        return jdbcTemplate.query(sql, result -> {
            if (result.next()) {
                scheduler.setId(result.getLong("id"));
                scheduler.setUsername(result.getString("username"));
                scheduler.setContents(result.getString("contents"));
                scheduler.setPassword(result.getString("password"));
                scheduler.setCreateDate(result.getDate("createDate"));
                scheduler.setUpdateDate(result.getDate("updateDate"));
                return new SchedulerResponseDto(scheduler);
            } else{
                return null;
            }
        }, id );
    }


}
