package ru.effectivemobile;

import org.junit.jupiter.api.BeforeAll;
import ru.effectivemobile.config.ConfigLoader;
import ru.effectivemobile.config.FrameworkConfig;
import ru.effectivemobile.core.SelenideConfigurator;

public abstract class BaseTest {

    @BeforeAll
    static void setup() {
        FrameworkConfig config = ConfigLoader.load();
        SelenideConfigurator.configure(config);
    }
}
