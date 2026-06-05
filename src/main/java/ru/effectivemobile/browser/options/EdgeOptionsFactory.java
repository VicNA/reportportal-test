package ru.effectivemobile.browser;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.edge.EdgeOptions;
import ru.effectivemobile.config.UiConfig;

public class EdgeOptionsFactory implements BrowserOptionsFactory {

    @Override
    public MutableCapabilities create(UiConfig config) {
        EdgeOptions options = new EdgeOptions();

        if (config.headless()) {
            options.addArguments("--headless=new");
        }

        return options;
    }
}
