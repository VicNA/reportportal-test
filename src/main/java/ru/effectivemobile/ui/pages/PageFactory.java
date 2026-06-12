package ru.effectivemobile.ui.pages;

import com.codeborne.selenide.WebDriverRunner;

import java.util.Objects;

public class PageFactory {

    public static AuthorizedPage detectAuthorizedPage() {
        if (Objects.requireNonNull(WebDriverRunner.url()).contains("launches")) {
            return new LaunchesPage();
        }

        if (Objects.requireNonNull(WebDriverRunner.url()).contains("dashboard")) {
            return new DashboardPage();
        }

        throw new IllegalStateException("Не удалось определить открытую страницу");
    }
}
