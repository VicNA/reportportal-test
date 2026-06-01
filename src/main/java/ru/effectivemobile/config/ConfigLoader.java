package ru.effectivemobile.config;

import ru.effectivemobile.browser.BrowserType;

public class ConfigLoader {

    public static FrameworkConfig load() {
        BrowserType browser = BrowserType.valueOf(
                System.getProperty("browser", "chrome").toUpperCase());

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false"));

        boolean torEnabled = Boolean.parseBoolean(
                System.getProperty("tor", "false"));

        long timeout = Long.parseLong(
                System.getProperty("timeout", "30000"));

        String remoteUrl = System.getProperty("remote", "");

        return new FrameworkConfig(
                browser,
                headless,
                torEnabled,
                timeout,
                remoteUrl
        );
    }
}
