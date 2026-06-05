package ru.effectivemobile.ui.pages;

import com.codeborne.selenide.SelenideElement;
import ru.effectivemobile.ui.pages.components.SidebarComponent;

import static com.codeborne.selenide.Selenide.$x;

public abstract class AuthorizedPage {

    private final SidebarComponent sidebar = new SidebarComponent();

    public SidebarComponent sidebar() {
        return sidebar;
    }

    public SelenideElement notification() {
        return $x("//h2[contains(text(),'Signed') or contains(text(), 'Успешный')]");
    }
}
