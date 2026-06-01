package ru.effectivemobile.browser;

import org.openqa.selenium.MutableCapabilities;
import ru.effectivemobile.config.FrameworkConfig;

public interface BrowserOptionsFactory {

    MutableCapabilities create(FrameworkConfig config);
}
