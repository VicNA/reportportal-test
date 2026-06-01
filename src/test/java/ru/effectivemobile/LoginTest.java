package ru.effectivemobile;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.effectivemobile.pages.LoginPage;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    void successLoginTest() {
        LoginPage loginPage = new LoginPage();

        loginPage.openPage()
                .enterLogin("default")
                .enterPassword("1q2w3e")
                .clickLoginButton();

        assertTrue($x("//h2[contains(text(),'Signed') or contains(text(), 'Успешный')]")
                .shouldBe(Condition.visible).exists());

//        try {
//            Thread.sleep(300_00);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
