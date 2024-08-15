package com.sparta.schedulertask.repository;

import com.sparta.schedulertask.dto.SchedulerRequestDto;
import com.sparta.schedulertask.dto.SchedulerResponseDto;
import com.sparta.schedulertask.entity.Scheduler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SchedulerRepository {

    private final JdbcTemplate jdbcTemplate;

    public SchedulerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정 등록
    public Scheduler save(Scheduler scheduler) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        // 작성일, 수정일 현재날짜와 시간 자동출력
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        scheduler.setCreateDate(currentTimestamp.toLocalDateTime());
        scheduler.setUpdateDate(currentTimestamp.toLocalDateTime());

        String sql = "INSERT INTO scheduler (username, contents, password, createDate, updateDate) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
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

    // 선택한 일정 조회
    public SchedulerResponseDto findById(Long id) {
        Scheduler scheduler = new Scheduler();
        // DB 조회
        String sql = "SELECT * FROM scheduler where id = ?";

        return jdbcTemplate.query(sql, result -> {
            if (result.next()) {
                scheduler.setId(result.getLong("id"));
                scheduler.setUsername(result.getString("username"));
                scheduler.setContents(result.getString("contents"));
                scheduler.setPassword(result.getString("password"));
                scheduler.setCreateDate(result.getTimestamp("createDate").toLocalDateTime());
                scheduler.setUpdateDate(result.getTimestamp("updateDate").toLocalDateTime());
                return new SchedulerResponseDto(scheduler);
            } else {
                return null;
            }
        }, id);
    }

    // 일정 목록 조회
    public List<SchedulerResponseDto> findByDateOrName(String updateDate, String username) {
        Date formattedDate = null;
        Date nextDay = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = dateFormat.parse(updateDate);

            // 다음 날을 계산합니다.
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formattedDate);
            calendar.add(Calendar.DATE, 1);
            nextDay = calendar.getTime();

        } catch (Exception e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }

        String sql = "SELECT * FROM scheduler WHERE (updateDate BETWEEN ? AND ?) OR username = ? ORDER BY updateDate DESC";

        return jdbcTemplate.query(sql, new Object[]{formattedDate, nextDay, username}, new RowMapper<SchedulerResponseDto>() {
            @Override
            public SchedulerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String userName = rs.getString("username");
                String contents = rs.getString("contents");
                LocalDateTime createDate = rs.getTimestamp("createDate").toLocalDateTime();
                LocalDateTime updateDate = rs.getTimestamp("updateDate").toLocalDateTime();
                return new SchedulerResponseDto(id, userName, contents, createDate, updateDate);
            }
        });
    }

    // 선택한 일정 수정
    public void update(Long id, String password, SchedulerRequestDto schedulerRequestDto) {
        String sql = "UPDATE scheduler SET username = ?, contents = ?, updateDate = ? WHERE id = ? AND password = ?";
        jdbcTemplate.update(sql, schedulerRequestDto.getUsername(), schedulerRequestDto.getContents(),
                //수정 날짜 수정시점으로 변경
                java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()), id, password);
    }

    // 선택한 일정 삭제
    public void delete(Long id, String password) {
        String sql = "DELETE FROM scheduler WHERE id = ? AND password = ?";
        jdbcTemplate.update(sql, id, password);
    }

    // 비밀번호 확인
    public boolean checkPassword(String password, Long id) {
        String sql = "SELECT password FROM scheduler WHERE id = ?";
        return Boolean.TRUE.equals(jdbcTemplate.query(sql, new Object[]{id}, result -> {
            if (result.next()) {
                String storedPassword = result.getString("password");
                return password.equals(storedPassword);
            } else {
                return false;
            }
        }));
    }


}
