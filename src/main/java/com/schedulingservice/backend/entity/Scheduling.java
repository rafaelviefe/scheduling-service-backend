package com.schedulingservice.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedulings")
public class Scheduling {

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

}
