package ru.effectivemobile.ui;

import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import ru.effectivemobile.config.AppConfig;
import ru.effectivemobile.config.UiConfig;
import ru.effectivemobile.ui.core.SelenideConfigurator;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseUiTest {

    private static final Logger logger = LogManager.getLogger(BaseUiTest.class);

    private static final UiConfig config = AppConfig.ui();

    @BeforeAll
    static void setup() {
        SelenideConfigurator.configure(config);
    }

    @BeforeEach
    void setupTest(TestInfo testInfo) {
        logger.info("START TEST: {}", testInfo.getDisplayName());

        open(config.url());
    }

    @AfterEach
    void tearDownTest(TestInfo testInfo) {
        Selenide.closeWebDriver();

        logger.info("FINISH TEST: {}", testInfo.getDisplayName());
    }
}
