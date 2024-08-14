package com.sparta.schedulertask.controller;

import com.sparta.schedulertask.dto.SchedulerRequestDto;
import com.sparta.schedulertask.dto.SchedulerResponseDto;
import com.sparta.schedulertask.service.SchedulerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    private final JdbcTemplate jdbcTemplate;

    public SchedulerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정 등록
    @PostMapping("/scheduler")
    public SchedulerResponseDto createScheduler (@RequestBody SchedulerRequestDto schedulerRequestDto) {
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        return schedulerService.createScheduler(schedulerRequestDto);
    }

    // 선택한 일정 조회
    // 일정의 고유 식별자(ID)를 사용하여 선택한 일정 단건의 정보를 조회
    @GetMapping("/scheduler/{id}")
    public SchedulerResponseDto getSchedule (@PathVariable Long id) {
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        return schedulerService.getSchedule(id);
    }

    // 일정 목록 조회
    //1. 다음 조건을 바탕으로 등록된 일정 목록을 전부 조회할 수 있습니다.
    //    1. `수정일` (형식 : YYYY-MM-DD)
    //    2. `담당자명`
    //2. 조건 중 한 가지만을 충족하거나, 둘 다 충족을 하지 않을 수도, 두 가지를 모두 충족할 수도 있습니다.
    //3. `수정일` 기준 내림차순으로 정렬하여 조회합니다.
//    @GetMapping("/scheduler")
//    public List<SchedulerResponseDto> getSchedules () {
//     // DB 조회
//    String sql = "SELECT * FROM scheduler where id = VALUE (?)";
//
//        return jdbcTemplate.query(sql, new RowMapper<SchedulerResponseDto>() {
//        @Override
//        public SchedulerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//            // SQL 의 결과로 받아온 scheduler 데이터들을 SchedulerResponseDto 타입으로 변환해줄 메서드
//            Long id = rs.getLong("id");
//            String username = rs.getString("username");
//            String contents = rs.getString("contents");
//            Date createDate = rs.getDate("createDate");
//            Date updateDate = rs.getDate("updateDate");
//
//            return new SchedulerResponseDto(id, username, contents, createDate, updateDate);
//        }
//    });




    }
