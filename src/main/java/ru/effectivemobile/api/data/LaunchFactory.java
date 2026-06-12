package ru.effectivemobile.api.data;

import net.datafaker.Faker;
import ru.effectivemobile.api.models.CreateLaunchRequest;
import ru.effectivemobile.api.models.FinishLaunchRequest;

import java.time.OffsetDateTime;

public final class LaunchFactory {

    private static final Faker faker = new Faker();

    public static CreateLaunchRequest create() {
        return CreateLaunchRequest.builder()
                .startTime(OffsetDateTime.now())
                .name("Launch Api Test: " + faker.app().name())
                .description(faker.lorem().sentence())
                .mode("DEFAULT")
                .build();
    }

    public static FinishLaunchRequest finish() {
        return FinishLaunchRequest.builder()
                .endTime(OffsetDateTime.now())
                .status("PASSED")
                .description(faker.lorem().sentence())
                .build();
    }
}
