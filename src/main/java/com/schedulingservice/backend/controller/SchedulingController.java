package com.schedulingservice.backend.controller;

import com.schedulingservice.backend.entity.Scheduling;
import com.schedulingservice.backend.service.SchedulingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/schedulings")
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping
    List<Scheduling> create(@RequestBody @Valid Scheduling scheduling) {
        return schedulingService.create(scheduling);
    }

    @GetMapping
    List<Scheduling> list() {
        return schedulingService.list();
    }

    @GetMapping("/{id}")
    Optional<Scheduling> get(@PathVariable("id") UUID id) {
        return schedulingService.get(id);
    }

    @PutMapping
    List<Scheduling> update(@RequestBody @Valid Scheduling scheduling) {
        return schedulingService.update(scheduling);
    }

    @DeleteMapping("/{id}")
    List<Scheduling> delete(@PathVariable("id") UUID id) {
        return schedulingService.delete(id);
    }

}
