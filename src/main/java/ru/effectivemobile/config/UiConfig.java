package ru.effectivemobile.config;

import ru.effectivemobile.ui.browser.BrowserType;

public record UiConfig(
        String login,
        String password,
        BrowserType browser,
        boolean headless,
        boolean torEnabled,
        long timeout,
        String url
) {
}
