package ru.effectivemobile.utils;

import net.datafaker.Faker;
import ru.effectivemobile.ui.pages.LaunchesPage;
import ru.effectivemobile.ui.pages.LoginPage;

public class LaunchesPageHelper {

    public LaunchesPage getLaunchesPage() {
        return new LoginPage().login()
                .sidebar()
                .openLaunchesPage();
    }

    public String appName(Faker faker, int minLength) {
        String name;
        do {
            name = faker.app().name();
        } while (name.length() < minLength);

        return name;
    }
}
