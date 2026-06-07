package ru.effectivemobile.config;

public record ApiConfig(
        String project,
        String apiKey,
        String apiVersion,
        String endpoint
) {
    public String launchPath() {
        return "%s/%s/launch".formatted(apiVersion, project);
    }
}
