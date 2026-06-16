package ru.effectivemobile.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.effectivemobile.config.AppConfig;
import ru.effectivemobile.config.UiConfig;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    private final SelenideElement inputLogin = $("input[name='login']");
    private final SelenideElement inputPassword = $("input[name='password']");
    private final SelenideElement submitButton = $("button[type='submit']");

    public AuthorizedPage login() {
        UiConfig ui = AppConfig.ui();

        logger.info("Login as {}", ui.login());

        enterLogin(ui.login());
        enterPassword(ui.password());
        clickLoginButton();

        return PageFactory.detectAuthorizedPage();
    }

    private void enterLogin(String login) {
        inputLogin.shouldBe(visible).setValue(login);
    }

    private void enterPassword(String password) {
        inputPassword.setValue(password);
    }

    private void clickLoginButton() {
        submitButton.click();
        submitButton.shouldNotBe(visible);
    }
}
