package ru.effectivemobile.ui.pages;

import com.codeborne.selenide.WebDriverRunner;
import ru.effectivemobile.utils.WaitUtils;

import java.util.Objects;

public class PageFactory {

    public static AuthorizedPage detectAuthorizedPage() {
        WaitUtils.waitForFormToUnload();

//        if ($("[class*='pageLayout__page-layout']").shouldBe(visible).exists()) {
        if (Objects.requireNonNull(WebDriverRunner.url()).contains("launches")) {
            return new LaunchesPage();
        }

//        if ($("[class*='dashboardTable']").shouldBe(visible).exists()) {
        if (Objects.requireNonNull(WebDriverRunner.url()).contains("dashboard")) {
            return new DashboardPage();
        }

        throw new IllegalStateException("Не удалось определить открытую страницу");
    }
}
