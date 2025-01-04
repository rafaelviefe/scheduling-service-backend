package com.schedulingservice.backend;

import com.schedulingservice.backend.entity.Schedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestConstants {

    public static final List<Schedule> SCHEDULES = new ArrayList<>() {
        {
            add(new Schedule(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                    LocalDateTime.parse("2025-01-06T13:59:00"),
                    "1", "1",
                    Schedule.CommunicationType.SMS));
            add(new Schedule(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"),
                    LocalDateTime.parse("2025-01-06T13:59:00"),
                    "2", "2",
                    Schedule.CommunicationType.EMAIL));
            add(new Schedule(UUID.fromString("123e4567-e89b-12d3-a456-426614174002"),
                    LocalDateTime.parse("2025-01-06T13:59:00"),
                    "3", "3",
                    Schedule.CommunicationType.WHATSAPP));
            add(new Schedule(UUID.fromString("123e4567-e89b-12d3-a456-426614174003"),
                    LocalDateTime.parse("2025-01-06T13:59:00"),
                    "4", "4",
                    Schedule.CommunicationType.PUSH));
        }
    };

    public static final Schedule SCHEDULE = SCHEDULES.get(0);
}
