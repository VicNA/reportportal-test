package ru.effectivemobile.pages.components;

import com.codeborne.selenide.SelenideElement;

import java.time.LocalDateTime;

public class LaunchRow {

    private final SelenideElement element;

    public LaunchRow(SelenideElement element) {
        this.element = element;
    }

    public String name() {
        return element.attr("class");
    }

    public String status() {
        return element.attr("class");
    }

    public LocalDateTime startTime() {
        return LocalDateTime.parse(element.$("").getText());
    }

    public void open() {
        element.click();
    }
}
