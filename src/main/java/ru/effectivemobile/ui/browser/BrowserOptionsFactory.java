package ru.effectivemobile.ui.browser;

import org.openqa.selenium.MutableCapabilities;
import ru.effectivemobile.config.UiConfig;

public interface BrowserOptionsFactory {

    MutableCapabilities create(UiConfig config);
}
