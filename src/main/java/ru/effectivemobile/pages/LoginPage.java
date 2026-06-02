package ru.effectivemobile.pages;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    public AuthorizedPage login(String login, String password) {
        enterLogin(login);
        enterPassword(password);
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
