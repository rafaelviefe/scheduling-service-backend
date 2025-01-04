package com.schedulingservice.backend.service;

import com.schedulingservice.backend.entity.Schedule;
import com.schedulingservice.backend.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> create(Schedule scheduling) {
        scheduleRepository.save(scheduling);
        return list();
    }

    public List<Schedule> list() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> get(UUID id) {
        return scheduleRepository.findById(id);
    }

    public List<Schedule> update(Schedule scheduling) {
        scheduleRepository.save(scheduling);
        return list();
    }

    public List<Schedule> delete(UUID id) {
        scheduleRepository.deleteById(id);
        return list();
    }

}