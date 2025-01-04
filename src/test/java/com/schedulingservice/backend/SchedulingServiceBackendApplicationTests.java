package com.schedulingservice.backend;

import com.schedulingservice.backend.entity.Schedule;
import org.hibernate.query.sqm.tree.from.SqmCrossJoin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.schedulingservice.backend.TestConstants.SCHEDULE;
import static com.schedulingservice.backend.TestConstants.SCHEDULES;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")
class SchedulingServiceBackendApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateScheduleSuccess() {
		var schedule = new Schedule(
				LocalDateTime.parse("2025-01-06T13:59:00"),
				"some recipient",
				"test message",
				Schedule.CommunicationType.SMS
		);

		webTestClient
				.post().uri("/schedules")
				.bodyValue(schedule)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].scheduledDateTime").isEqualTo(schedule.getScheduledDateTime())
				.jsonPath("$[0].recipient").isEqualTo(schedule.getRecipient())
				.jsonPath("$[0].message").isEqualTo(schedule.getMessage())
				.jsonPath("$[0].communicationType").isEqualTo(schedule.getCommunicationType());
	}

	@Test
	void testCreateScheduleFailure() {
		webTestClient
				.post().uri("/schedules")
				.bodyValue(
						new Schedule(
								LocalDateTime.parse("2025-01-06T13:59:00"),
								"", "", Schedule.CommunicationType.SMS
						)
				).exchange()
				.expectStatus().isBadRequest();
	}


	@Test
	void contextLoads() {
	}

}
