package com.sparta.schedulertask.service;

import com.sparta.schedulertask.dto.SchedulerRequestDto;
import com.sparta.schedulertask.dto.SchedulerResponseDto;
import com.sparta.schedulertask.entity.Scheduler;
import com.sparta.schedulertask.repository.SchedulerRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class SchedulerService {

    private final JdbcTemplate jdbcTemplate;

    public SchedulerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SchedulerResponseDto createScheduler (SchedulerRequestDto schedulerRequestDto) {
        // RequestDto -> Entity
        Scheduler scheduler = new Scheduler(schedulerRequestDto);
        // DB 저장
        SchedulerRepository schedulerRepository = new SchedulerRepository(jdbcTemplate);
        Scheduler saveScheduler = schedulerRepository.save(scheduler);
        // Entity -> ResponseDto
        SchedulerResponseDto schedulerResponseDto = new SchedulerResponseDto(scheduler);

        return schedulerResponseDto;
    }


    public SchedulerResponseDto getSchedule (Long id) {
        SchedulerRepository schedulerRepository = new SchedulerRepository(jdbcTemplate);
        return schedulerRepository.findById(id);

    }



}
