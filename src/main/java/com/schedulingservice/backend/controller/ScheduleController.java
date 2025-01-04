package com.schedulingservice.backend.controller;

import com.schedulingservice.backend.entity.Schedule;
import com.schedulingservice.backend.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService schedulingService;

    public ScheduleController(ScheduleService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping
    List<Schedule> create(@RequestBody @Valid Schedule scheduling) {
        return schedulingService.create(scheduling);
    }

    @GetMapping
    List<Schedule> list() {
        return schedulingService.list();
    }

    @GetMapping("/{id}")
    Optional<Schedule> get(@PathVariable("id") UUID id) {
        return schedulingService.get(id);
    }

    @PutMapping
    List<Schedule> update(@RequestBody @Valid Schedule scheduling) {
        return schedulingService.update(scheduling);
    }

    @DeleteMapping("/{id}")
    List<Schedule> delete(@PathVariable("id") UUID id) {
        return schedulingService.delete(id);
    }

}