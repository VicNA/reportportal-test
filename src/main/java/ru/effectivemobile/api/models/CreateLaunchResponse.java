package ru.effectivemobile.api.models;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateLaunchResponse {
    private UUID id;
    private Long number;
}
