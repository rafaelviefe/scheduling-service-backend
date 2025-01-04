package com.schedulingservice.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private LocalDateTime scheduledDateTime;

    @NotBlank
    private String recipient;

    @NotBlank
    private String message;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CommunicationType communicationType;

    public enum CommunicationType {
        EMAIL,
        SMS,
        PUSH,
        WHATSAPP
    }

    public Schedule() {
    }

    public Schedule(LocalDateTime scheduledDateTime, String recipient, String message, CommunicationType communicationType) {
        this.scheduledDateTime = scheduledDateTime;
        this.recipient = recipient;
        this.message = message;
        this.communicationType = communicationType;
    }

    public Schedule(UUID id, LocalDateTime scheduledDateTime, String recipient, String message, CommunicationType communicationType) {
        this.id = id;
        this.scheduledDateTime = scheduledDateTime;
        this.recipient = recipient;
        this.message = message;
        this.communicationType = communicationType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommunicationType getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(CommunicationType communicationType) {
        this.communicationType = communicationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule that = (Schedule) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Scheduling{" +
                "id=" + id +
                ", scheduledDateTime=" + scheduledDateTime +
                ", recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", communicationType=" + communicationType +
                '}';
    }
}

