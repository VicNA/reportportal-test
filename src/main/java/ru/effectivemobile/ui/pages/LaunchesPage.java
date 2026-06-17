package ru.effectivemobile.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Accessors(fluent = true)
public class LaunchesPage extends AuthorizedPage {

    private static final Logger logger = LogManager.getLogger(LaunchesPage.class);

    @Getter
    private final ElementsCollection launchRows = $$("[class*='gridRow__grid-row-wrapper']");
    @Getter
    private final ElementsCollection pageButtons = $$("[class*='pageButton__page-button']");
    @Getter
    private final ElementsCollection selectedItems = $$("[class*='selectedItems__item']");
    @Getter
    private final SelenideElement launchTable = $("div[class*='grid_']");
    @Getter
    private final SelenideElement emptySearchResult = $("[class*='noItemMessage__message']");
    @Getter
    private final SelenideElement activePage = $("[class*='pageButton__active']");

    private final ElementsCollection launchFilters = $$("[class*='filterItem__filter-item']");
    private final ElementsCollection dropdownSortingOptions = $$("[class*='inputDropdownSortingOption__single-option']");
    private final ElementsCollection filterControls = $$("[class*='filterControls__control-button']");
    private final ElementsCollection actionButtons = $$("[class*='action-button-']");
    private final ElementsCollection modelFooterButtons = $$("[class*='modalFooter__button-container']");

    private final SelenideElement inputFilter = $("input[class*='inputConditional']");
    private final SelenideElement clearSearchIcon = $("[class*='inputConditional__clear-icon']");
    private final SelenideElement addFilterButton = $("[class*='launchFiltersToolbar__add-filter-button']");
    private final SelenideElement inputNewFilter = $("input[class*='input__input']");
    private final SelenideElement dropdownSorting = $("[class*='inputDropdownSorting__value']");
    private final SelenideElement checkboxHeaderCell = $("[class*='checkbox-header-cell']");
    private final SelenideElement pageSizeControl = $("[class*='pageSizeControl__size-text']");
    private final SelenideElement inputPageSize = $("input[class*='input__input']");

    public List<String> launchNamesFromLaunchRows() {
        launchRows.first().shouldBe(visible);
        return launchRows.stream()
                .map(el -> el.find("* a[class*='itemInfo__name-link'] * span"))
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public LaunchesPage search(String input) {
        logger.info("Launch search for '{}'", input);

        addFilterButton.click();
        inputFilter.setValue(input);

        return this;
    }

    public LaunchesPage clearSearch() {
        clearSearchIcon.click();

        return this;
    }

    public LaunchesPage saveFilter(String filter) {
        logger.info("Save '{}' launch filter", filter);

        clickSaveFilter();
        enterNewFilter(filter);
        clickAddNewFilter();

        return this;
    }

    public SelenideElement filterItem(String filter) {
        return launchFilters.find(text(filter));
    }

    public LaunchesPage sortByName() {
        logger.info("Sort by Launch name");

        addFilterButton.click();
        dropdownSorting.click();
        dropdownSortingOptions.find(text("Launch name")).click();

        return this;
    }

    public LaunchesPage changePageSize(int size) {
        logger.info("Change the page size to {}", size);

        pageSizeControl.click();
        inputPageSize.setValue(String.valueOf(size))
                .sendKeys(Keys.ENTER);

        return this;
    }

    public LaunchesPage prevPage() {
        pageButtons.get(1).click();

        return this;
    }

    public LaunchesPage nextPage() {
        pageButtons.get(pageButtons.size() - 2).click();

        return this;
    }

    public int currentPageNumber() {
        return Integer.parseInt(activePage().getText());
    }

    public LaunchesPage checkboxAllRows() {
        checkboxHeaderCell.click();

        return this;
    }

    public LaunchesPage refresh() {
        logger.info("Refresh Launches page");

        actionButtons.find(text("Refresh")).click();

        return this;
    }

    private void clickSaveFilter() {
        filterControls.find(text("Save")).click();
    }

    private void enterNewFilter(String filter) {
        inputNewFilter.clear();
        inputNewFilter.setValue(filter);
    }

    private void clickAddNewFilter() {
        modelFooterButtons.find(text("Add")).click();
    }
}
