package com.sparta.schedulertask.controller;

import com.sparta.schedulertask.dto.SchedulerRequestDto;
import com.sparta.schedulertask.dto.SchedulerResponseDto;
import com.sparta.schedulertask.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SchedulerController {

    private final SchedulerService schedulerService;

    // 일정 등록
    @PostMapping("/scheduler")
    public SchedulerResponseDto createScheduler(@RequestBody SchedulerRequestDto schedulerRequestDto) {
        return schedulerService.createScheduler(schedulerRequestDto);
    }

    // 선택한 일정 조회
    // 일정의 고유 식별자(ID)를 사용하여 선택한 일정 단건의 정보를 조회
    @GetMapping("/scheduler")
    public SchedulerResponseDto getSchedule(@RequestBody SchedulerRequestDto schedulerRequestDto) {
        return schedulerService.getSchedule(schedulerRequestDto.getId());
    }

    // 일정 목록 조회
    @GetMapping("/scheduler/{updateDate}/{username}")
    public List<SchedulerResponseDto> getSchedules(@PathVariable String updateDate, @PathVariable String username ) {
        return schedulerService.getSchedules(updateDate, username );
    }

    //리퀘스트바디 두개는 불가
    // 선택한 일정 수정
    @PutMapping("/scheduler")
    public SchedulerResponseDto putSchedule(@RequestBody SchedulerRequestDto schedulerRequestDto) {
        return schedulerService.updateSchedule(schedulerRequestDto.getId(), schedulerRequestDto.getPassword(), schedulerRequestDto);

    }

    // 선택한 일정 삭제
    @DeleteMapping("/scheduler")
    public String deleteSchedule(@RequestBody SchedulerRequestDto schedulerRequestDto) {
        return schedulerService.deleteSchedule(schedulerRequestDto.getId(), schedulerRequestDto.getPassword());
    }
}
