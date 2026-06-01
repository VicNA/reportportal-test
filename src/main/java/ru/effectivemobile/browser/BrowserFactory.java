package ru.effectivemobile.browser;

import org.openqa.selenium.MutableCapabilities;
import ru.effectivemobile.config.FrameworkConfig;

public class BrowserFactory {

    public static MutableCapabilities create(FrameworkConfig config) {
        return switch (config.browser()) {
            case CHROME -> new ChromeOptionsFactory().create(config);
            case FIREFOX -> new FirefoxOptionsFactory().create(config);
            case EDGE -> new EdgeOptionsFactory().create(config);
        };
    }
}
