package ru.effectivemobile.api.data;

import net.datafaker.Faker;
import ru.effectivemobile.api.models.CreateLaunchRequest;

import java.time.OffsetDateTime;

public final class LaunchFactory {

    private static final Faker faker = new Faker();

    public static CreateLaunchRequest launch() {
        return CreateLaunchRequest.builder()
                .startTime(OffsetDateTime.now())
                .name(faker.app().name())
                .description(faker.lorem().sentence())
                .mode("DEFAULT")
                .build();
    }
}
