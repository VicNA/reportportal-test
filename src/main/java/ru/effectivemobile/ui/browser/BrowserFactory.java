package ru.effectivemobile.ui.browser;

import org.openqa.selenium.MutableCapabilities;
import ru.effectivemobile.ui.browser.options.ChromeOptionsFactory;
import ru.effectivemobile.ui.browser.options.EdgeOptionsFactory;
import ru.effectivemobile.ui.browser.options.FirefoxOptionsFactory;
import ru.effectivemobile.config.UiConfig;

public class BrowserFactory {

    public static MutableCapabilities create(UiConfig config) {
        return switch (config.browser()) {
            case CHROME -> new ChromeOptionsFactory().create(config);
            case FIREFOX -> new FirefoxOptionsFactory().create(config);
            case EDGE -> new EdgeOptionsFactory().create(config);
        };
    }
}
