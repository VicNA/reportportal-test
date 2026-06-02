package ru.effectivemobile.utils;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WaitUtils {

    public static void waitForFormToLoad() {
        $("form").shouldBe(visible);
    }

    public static void waitForFormToUnload() {
        $("form").shouldNotBe(visible);
    }
}
