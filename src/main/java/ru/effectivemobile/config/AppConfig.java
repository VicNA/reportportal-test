package ru.effectivemobile.config;

import ru.effectivemobile.ui.browser.BrowserType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static final ApiConfig API;
    private static final UiConfig UI;

    static {
        Properties properties = loadProperties();

        API = buildApiConfig(properties);
        UI = buildUiConfig(properties);
    }

    public static ApiConfig api() {
        return API;
    }

    public static UiConfig ui() {
        return UI;
    }

    private static ApiConfig buildApiConfig(Properties props) {
        return new ApiConfig(
                getProperty("project", props),
                getProperty("api_key", props),
                getProperty("api_version", props),
                getProperty("endpoint", props)
        );
    }

    private static UiConfig buildUiConfig(Properties props) {
        return new UiConfig(
                getProperty("login", props),
                getProperty("password", props),
                BrowserType.valueOf(getProperty("browser", props).toUpperCase()),
                Boolean.parseBoolean(getProperty("headless", props)),
                Boolean.parseBoolean(getProperty("tor", props)),
                Long.parseLong(getProperty("timeout", props)),
                getProperty("endpoint", props)
        );
    }

    private static String getProperty(String key, Properties props) {
        String envKey = key.toUpperCase();

        String value = System.getenv(envKey);
        if (value != null && !value.isBlank()) {
            return value;
        }

        value = System.getProperty(key);
        if (value != null && !value.isBlank()) {
            return value;
        }

        value = props.getProperty(key);
        if (value != null && !value.isBlank()) {
            return value;
        }

        throw new IllegalStateException("Required config is missing: " + key);
    }

    private static Properties loadProperties() {
        String resource = "app.properties";

        Properties props = new Properties();
        try (InputStream is = AppConfig.class.getClassLoader().getResourceAsStream(resource)) {
            if (is == null) {
                throw new IllegalStateException(resource + " not found");
            }

            props.load(is);

            return props;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + resource, e);
        }
    }
}
