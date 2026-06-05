package ru.effectivemobile.ui.browser.options;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.effectivemobile.ui.browser.BrowserOptionsFactory;
import ru.effectivemobile.config.UiConfig;

public class FirefoxOptionsFactory implements BrowserOptionsFactory {

    @Override
    public MutableCapabilities create(UiConfig config) {
        FirefoxOptions options = new FirefoxOptions();

        if (config.headless()) {
            options.addArguments("-headless");
        }

        if (config.torEnabled()) {
            enableTor(options);
        }

        return options;
    }

    private void enableTor(FirefoxOptions options) {
        options.addPreference("network.proxy.type", 1);
        options.addPreference("network.proxy.socks", "127.0.0.1");
        options.addPreference("network.proxy.socks_port", 9150);
        options.addPreference("network.proxy.socks_remote_dns", true);
    }
}
