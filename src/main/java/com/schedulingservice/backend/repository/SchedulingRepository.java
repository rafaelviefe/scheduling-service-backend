package com.schedulingservice.backend.repository;

import com.schedulingservice.backend.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SchedulingRepository extends JpaRepository<Scheduling, String> {

}
