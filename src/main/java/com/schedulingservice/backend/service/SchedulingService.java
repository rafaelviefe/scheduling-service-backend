package com.schedulingservice.backend.service;

import com.schedulingservice.backend.entity.Scheduling;
import com.schedulingservice.backend.repository.SchedulingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SchedulingService {

    private SchedulingRepository schedulingRepository;

    public SchedulingService(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    public List<Scheduling> create(Scheduling scheduling) {
        schedulingRepository.save(scheduling);
        return list();
    }

    public List<Scheduling> list() {
        return schedulingRepository.findAll();
    }

    public Optional<Scheduling> get(UUID id) {
        return schedulingRepository.findById(id);
    }

    public List<Scheduling> update(Scheduling scheduling) {
        schedulingRepository.save(scheduling);
        return list();
    }

    public List<Scheduling> delete(UUID id) {
        schedulingRepository.deleteById(id);
        return list();
    }

}
