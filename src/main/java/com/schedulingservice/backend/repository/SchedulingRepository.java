package com.schedulingservice.backend.repository;

import com.schedulingservice.backend.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {

}
