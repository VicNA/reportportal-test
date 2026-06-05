package ru.effectivemobile.browser;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.effectivemobile.config.UiConfig;

public class ChromeOptionsFactory implements BrowserOptionsFactory {

    @Override
    public MutableCapabilities create(UiConfig config) {
        ChromeOptions options = new ChromeOptions();

        if (config.headless()) {
            options.addArguments("--headless=new");
        }

        return options;
    }
}
