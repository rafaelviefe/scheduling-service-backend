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

	@Sql("/inserts.sql")
	@Test
	void testGetScheduleSuccess() {
		webTestClient
				.get().uri("/schedules/" + SCHEDULE.getId())
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.id").isEqualTo(SCHEDULE.getId().toString())
				.jsonPath("$.scheduledDateTime").isEqualTo(SCHEDULE.getScheduledDateTime())
				.jsonPath("$.recipient").isEqualTo(SCHEDULE.getRecipient())
				.jsonPath("$.message").isEqualTo(SCHEDULE.getMessage())
				.jsonPath("$.communicationType").isEqualTo(SCHEDULE.getCommunicationType());
	}

	@Test
	void testGetScheduleFailure() {
		UUID nonExistentId = UUID.fromString("123e4567-e89b-12d3-a456-426613214009");

		webTestClient
				.get().uri("/schedules/" + nonExistentId)
				.exchange()
				.expectStatus().isNotFound();
	}

	@Sql("/inserts.sql")
	@Test
	void testUpdateScheduleSuccess() {
		var schedule = new Schedule(
				SCHEDULE.getId(),
				SCHEDULE.getScheduledDateTime(),
				SCHEDULE.getRecipient(),
				SCHEDULE.getMessage() + "new",
				SCHEDULE.getCommunicationType());

		webTestClient
				.put().uri("/schedules/" + SCHEDULE.getId())
				.bodyValue(schedule)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(4)
				.jsonPath("$[0].scheduledDateTime").isEqualTo(schedule.getScheduledDateTime())
				.jsonPath("$[0].recipient").isEqualTo(schedule.getRecipient())
				.jsonPath("$[0].message").isEqualTo(schedule.getMessage())
				.jsonPath("$[0].communicationType").isEqualTo(schedule.getCommunicationType());
	}

	@Test
	void testUpdateScheduleFailure() {
		UUID nonExistentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174009");

		webTestClient
				.put()
				.uri("/schedules/" + nonExistentId)
				.bodyValue(
						new Schedule(
								nonExistentId, LocalDateTime.parse("2025-01-06T13:59:00"),
								"test", "test", Schedule.CommunicationType.SMS
						)
				).exchange()
				.expectStatus().isNotFound();
	}

	@Sql("/inserts.sql")
	@Test
	void testDeleteScheduleSuccess() {
		webTestClient
				.delete().uri("/schedules/" + SCHEDULE.getId())
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(3);
	}

	@Test
	void testDeleteScheduleFailure() {
		UUID nonExistentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174009");

		webTestClient
				.delete().uri("/schedules/" + nonExistentId)
				.exchange()
				.expectStatus().isNotFound();
	}

}