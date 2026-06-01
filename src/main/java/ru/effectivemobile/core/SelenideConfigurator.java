package ru.effectivemobile.core;

import com.codeborne.selenide.Configuration;
import ru.effectivemobile.browser.BrowserFactory;
import ru.effectivemobile.config.FrameworkConfig;

public class SelenideConfigurator {

    public static void configure(FrameworkConfig config) {
        Configuration.browser = config.browser().name().toLowerCase();

        Configuration.browserCapabilities = BrowserFactory.create(config);

        Configuration.timeout = config.timeout();

        if (!config.url().isBlank()) {
            Configuration.remote = config.url();
        }
    }
}
