package com.sparta.schedulertask.service;

import com.sparta.schedulertask.dto.SchedulerRequestDto;
import com.sparta.schedulertask.dto.SchedulerResponseDto;
import com.sparta.schedulertask.entity.Scheduler;
import com.sparta.schedulertask.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    // 일정 등록
    public SchedulerResponseDto createScheduler (SchedulerRequestDto schedulerRequestDto) {
        // RequestDto -> Entity
        Scheduler scheduler = new Scheduler(schedulerRequestDto);
        // DB 저장
        Scheduler saveScheduler = schedulerRepository.save(scheduler);
        // Entity -> ResponseDto
        SchedulerResponseDto schedulerResponseDto = new SchedulerResponseDto(scheduler);
        return schedulerResponseDto;
    }

    // 선택한 일정 조회
    public SchedulerResponseDto getSchedule (Long id) {
        return schedulerRepository.findById(id);

    }

    // 일정 목록 조회
    public List<SchedulerResponseDto> getSchedules(String updateDate, String username) {
        return schedulerRepository.findByDateOrName(updateDate, username);
    }

    // 선택한 일정 수정
    public SchedulerResponseDto updateSchedule(Long id, String password, SchedulerRequestDto schedulerRequestDto) {
        //해당 일정이 있는지 조회
        SchedulerResponseDto scheduler = schedulerRepository.findById(id);
        if(scheduler != null ) {
            //비밀번호 확인
            if(!schedulerRepository.checkPassword(password, id)) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");}
            //일정 내용 수정
            schedulerRepository.update(id, password, schedulerRequestDto);
            // 수정한 내용 반환
            scheduler.setUsername(schedulerRequestDto.getUsername());
            scheduler.setContents(schedulerRequestDto.getContents());
            return scheduler;
        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
    }

    // 선택한 일정 삭제
    public String deleteSchedule(Long id, String password) {
        //해당 일정이 있는지 조회
        SchedulerResponseDto scheduler = schedulerRepository.findById(id);
        if(scheduler != null ) {
            //비밀번호 확인
            if(!schedulerRepository.checkPassword(password, id)) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");}
            //일정 내용 삭제
            schedulerRepository.delete(id,password);
            return id + " 번의 일정이 삭제되었습니다.";
        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
    }

}
