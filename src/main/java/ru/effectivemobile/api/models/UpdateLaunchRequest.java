package ru.effectivemobile.api.models;

public class UpdateLaunchRequest {

    private String description;

    public UpdateLaunchRequest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

     public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String description;

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public UpdateLaunchRequest build() {
            return new UpdateLaunchRequest(description);
        }
    }
}
