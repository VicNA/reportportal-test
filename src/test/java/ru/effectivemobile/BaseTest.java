package ru.effectivemobile;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.effectivemobile.config.ConfigLoader;
import ru.effectivemobile.config.FrameworkConfig;
import ru.effectivemobile.core.SelenideConfigurator;
import ru.effectivemobile.utils.WaitUtils;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {

    @BeforeAll
    static void setup() {
        FrameworkConfig config = ConfigLoader.load();
        SelenideConfigurator.configure(config);
    }

    @BeforeEach
    void setupTest() {
//        open("https://demo.reportportal.io");
        open("http://localhost:8080/");

        WaitUtils.waitForFormToLoad();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}
