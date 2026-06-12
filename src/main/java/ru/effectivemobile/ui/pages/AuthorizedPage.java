package ru.effectivemobile.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.experimental.Accessors;
import ru.effectivemobile.ui.pages.components.SidebarComponent;

import static com.codeborne.selenide.Selenide.$;

@Accessors(fluent = true)
@Getter
public abstract class AuthorizedPage {

    private final SidebarComponent sidebar = new SidebarComponent();
    private final SelenideElement notification = $("[data-automation-id='notificationItem']");
}
