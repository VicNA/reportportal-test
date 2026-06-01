package ru.effectivemobile.config;

import ru.effectivemobile.browser.BrowserType;

public record FrameworkConfig(
        BrowserType browser,
        boolean headless,
        boolean torEnabled,
        long timeout,
        String url
) {
}
