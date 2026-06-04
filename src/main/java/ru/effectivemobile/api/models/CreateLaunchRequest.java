package ru.effectivemobile.api.models;

import java.time.OffsetDateTime;

public class CreateLaunchRequest {

    private String startTime;
    private String name;
    private String description;
    private String mode;

    public CreateLaunchRequest() {
    }

    public CreateLaunchRequest(String startTime, String name, String description, String mode) {
        this.startTime = startTime;
        this.name = name;
        this.description = description;
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String description;
        private String startTime;
        private String mode;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder startTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder mode(String mode) {
            this.mode = mode;
            return this;
        }

        public CreateLaunchRequest build() {
            return new CreateLaunchRequest(
                    startTime,
                    name,
                    description,
                    mode
            );
        }
    }
}
