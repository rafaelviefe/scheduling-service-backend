package com.schedulingservice.backend.service;

import com.schedulingservice.backend.entity.Schedule;
import com.schedulingservice.backend.exception.BadRequestException;
import com.schedulingservice.backend.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> create(Schedule schedule) {
        scheduleRepository.save(schedule);
        return list();
    }

    public List<Schedule> list() {
        return scheduleRepository.findAll();
    }

    public Schedule get(UUID id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Schedule %s does not exist".formatted(id.toString())));
    }

    public List<Schedule> update(UUID id, Schedule schedule) {

        scheduleRepository.findById(id).ifPresentOrElse((existingSchedule) -> {
            schedule.setId(id);
            scheduleRepository.save(schedule);
        }, () -> {
            throw new BadRequestException("Schedule %s do not exists".formatted(id.toString()));
        });

        return list();
    }

    public List<Schedule> delete(UUID id) {
        scheduleRepository.findById(id).ifPresentOrElse(scheduleRepository::delete, () -> {
            throw new BadRequestException("Schedule %s do not exists".formatted(id.toString()));
        });

        return list();
    }

}