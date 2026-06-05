package ru.effectivemobile.config;

public record ApiConfig(
        String project,
        String apiKey,
        String apiVersion,
        String endpoint
) {
}
