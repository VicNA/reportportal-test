package ru.effectivemobile.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    public LoginPage openPage() {
//        open("https://demo.reportportal.io");
        open("http://localhost:8080/");
        return this;
    }

    public LoginPage enterLogin(String login) {
        $("[name='login']").shouldBe(visible).setValue(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        $("[name='password']").shouldBe(visible).setValue(password);
        return this;
    }

    public void clickLoginButton() {
        $("button[type='submit']").click();
    }
}
