package com.sparta.schedulertask.controller;

import com.sparta.schedulertask.dto.SchedulerRequestDto;
import com.sparta.schedulertask.dto.SchedulerResponseDto;
import com.sparta.schedulertask.service.SchedulerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(JdbcTemplate jdbcTemplate) {
        this.schedulerService = new SchedulerService(jdbcTemplate);
    }

    // 일정 등록
    @PostMapping("/scheduler")
    public SchedulerResponseDto createScheduler(@RequestBody SchedulerRequestDto schedulerRequestDto) {
        return schedulerService.createScheduler(schedulerRequestDto);
    }

    // 선택한 일정 조회
    // 일정의 고유 식별자(ID)를 사용하여 선택한 일정 단건의 정보를 조회
    @GetMapping("/scheduler/{id}")
    public SchedulerResponseDto getSchedule(@PathVariable Long id) {
        return schedulerService.getSchedule(id);
    }

    // 일정 목록 조회
    @GetMapping("/scheduler/{updateDate}/{username}")
    public List<SchedulerResponseDto> getSchedules(@PathVariable String updateDate, @PathVariable String username ) {
        return schedulerService.getSchedules(updateDate, username );
    }

    // 선택한 일정 수정
    @PutMapping("/scheduler/{id}/{password}")
    public SchedulerResponseDto putSchedule(@PathVariable Long id,@PathVariable String password, @RequestBody SchedulerRequestDto schedulerRequestDto) {
        return schedulerService.updateSchedule(id, password, schedulerRequestDto);

    }

    // 선택한 일정 삭제
    @DeleteMapping("/scheduler/{id}/{password}")
    public String deleteSchedule(@PathVariable Long id, @PathVariable String password) {
        return schedulerService.deleteSchedule(id, password);
    }
}
