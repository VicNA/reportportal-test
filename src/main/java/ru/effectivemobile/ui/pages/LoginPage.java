package ru.effectivemobile.ui.pages;

import ru.effectivemobile.config.AppConfig;
import ru.effectivemobile.config.UiConfig;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public AuthorizedPage login() {
        UiConfig ui = AppConfig.ui();

        enterLogin(ui.login());
        enterPassword(ui.password());
        clickLoginButton();

        return PageFactory.detectAuthorizedPage();
    }

    private void enterLogin(String login) {
        $("input[name='login']").setValue(login);
    }

    private void enterPassword(String password) {
        $("input[name='password']").setValue(password);
    }

    private void clickLoginButton() {
        $("button[type='submit']").click();
    }
}
