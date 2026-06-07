package ru.effectivemobile.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateLaunchRequest {
    private String description;
}
