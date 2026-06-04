package ru.effectivemobile.api.models;

import java.util.UUID;

public class CreateLaunchResponse {

    private UUID id;
    private Long number;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
