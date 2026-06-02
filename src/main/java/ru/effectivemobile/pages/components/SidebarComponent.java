package ru.effectivemobile.pages.components;

import ru.effectivemobile.pages.LaunchesPage;

import static com.codeborne.selenide.Selenide.$;

public class SidebarComponent {

    public LaunchesPage openLaunchesPage() {
        $("a[href*='launches']").click();

        return new LaunchesPage();
    }
}
