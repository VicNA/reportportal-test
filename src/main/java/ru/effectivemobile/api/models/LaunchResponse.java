package ru.effectivemobile.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LaunchResponse {
    private String description;
    private Long id;
    private String uuid;
    private String name;
    private Long number;
    private OffsetDateTime startTime;
    private String status;
}
