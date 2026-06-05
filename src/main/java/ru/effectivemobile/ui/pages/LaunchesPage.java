package ru.effectivemobile.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class LaunchesPage extends AuthorizedPage {

    public SelenideElement launchTable() {
        return $("div[class*='grid_']");
    }

    public ElementsCollection launchRows() {
        return $$("div[class*='gridRow__grid-row-wrapper']");
    }

    public LaunchesPage clickAddFilter() {
        $("[class*='launchFiltersToolbar__add-filter-button']").click();

        return this;
    }

    public LaunchesPage enterFilter(String filter) {
        $("input[class*='inputConditional']").clear();
        $("input[class*='inputConditional']").sendKeys(filter);

        return this;
    }

    public LaunchesPage clickSaveFilter() {
        $x("//span[text()='Save']").click();

        return this;
    }

    public LaunchesPage enterNewFilter(String filter) {
        $("input[class*='input__input']").clear();
        $("input[class*='input__input']").setValue(filter);

        return this;
    }

    public LaunchesPage clickAddNewFilter() {
        $x("//button[text()='Add']").click();

        return this;
    }

    public LaunchesPage clickCancelFilter() {
        $("a[class*='filterItem__active']").click();

        return this;
    }

    public LaunchesPage addNewFilter(String filter) {
        clickAddFilter();
        enterFilter(filter);
        clickSaveFilter();
        enterNewFilter(filter);
        clickAddNewFilter();

        return this;
    }

}
