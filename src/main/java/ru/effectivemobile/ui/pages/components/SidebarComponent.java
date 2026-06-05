package ru.effectivemobile.ui.pages.components;

import ru.effectivemobile.ui.pages.LaunchesPage;

import static com.codeborne.selenide.Selenide.$;

public class SidebarComponent {

    public LaunchesPage openLaunchesPage() {
        $("a[href*='launches']").click();

        return new LaunchesPage();
    }
}
