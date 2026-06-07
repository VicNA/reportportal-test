package ru.effectivemobile.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
public class FinishLaunchRequest {
    private OffsetDateTime endTime;
    private String status;
    private String description;
}
