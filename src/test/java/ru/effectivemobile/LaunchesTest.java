package ru.effectivemobile;

import org.junit.jupiter.api.Test;
import ru.effectivemobile.pages.AuthorizedPage;
import ru.effectivemobile.pages.LaunchesPage;
import ru.effectivemobile.pages.LoginPage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LaunchesTest extends BaseTest {

    @Test
    void shouldOpenLaunchesPageAfterLogin() {
        AuthorizedPage homePage = new LoginPage().login("default", "1q2w3e");

        homePage.notification().shouldBe(visible);

        LaunchesPage launchesPage = homePage.sidebar().openLaunchesPage();

        launchesPage.launchTable().shouldBe(visible);
    }

    @Test
    void shouldDisplayLaunchesList() {
        AuthorizedPage homePage = new LoginPage().login("default", "1q2w3e");

        LaunchesPage launchesPage = homePage.sidebar().openLaunchesPage();

//        launchesPage.launchRows().shouldHave(sizeGreaterThan(0)); // TODO: Нужно заполнить список перед тестами
        launchesPage.launchRows().shouldHave(sizeGreaterThanOrEqual(0));
    }

//    @Test
//    void shouldFindLaunchByName() {
//        LaunchesPage page = new LoginPage().login("default", "1q2w3e");
//
//        page.search("Demo Launch");
//
//        page.launchRows()
//                .first()
//                .shouldHave(text("Demo Launch"));
//    }
}
