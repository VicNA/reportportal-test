package ru.effectivemobile.ui.core;

import com.codeborne.selenide.Configuration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.effectivemobile.ui.browser.BrowserFactory;
import ru.effectivemobile.config.UiConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SelenideConfigurator {

    public static void configure(UiConfig config) {
        Configuration.browser = config.browser().name().toLowerCase();
        Configuration.browserCapabilities = BrowserFactory.create(config);
        Configuration.timeout = config.timeout();
        Configuration.baseUrl = config.url();
    }
}
