package ru.effectivemobile.ui;

import net.datafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.effectivemobile.api.models.CreateLaunchResponse;
import ru.effectivemobile.api.services.LaunchesService;
import ru.effectivemobile.ui.pages.AuthorizedPage;
import ru.effectivemobile.ui.pages.LaunchesPage;
import ru.effectivemobile.ui.pages.LoginPage;
import ru.effectivemobile.utils.LaunchesHelper;
import ru.effectivemobile.utils.LaunchesPageHelper;
import ru.effectivemobile.utils.SourceValueConstants;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaunchesUiTest extends BaseUiTest {

    private static final LaunchesService launchesService = new LaunchesService();
    private static final LaunchesHelper launchesHelper = new LaunchesHelper(launchesService);
    private static final LaunchesPageHelper pageHelper = new LaunchesPageHelper();

    @BeforeAll
    static void setup() {
        Stream.generate(launchesHelper::createLaunch)
                .map(CreateLaunchResponse::getId)
                .limit(5)
                .forEach(launchesHelper::getLaunchById);
    }

    @AfterAll
    static void teardown() {
        launchesHelper.clear();
    }

    @DisplayName("Open Launches page")
    @Test
    void shouldOpenLaunchesPageAfterLogin() {
        AuthorizedPage homePage = new LoginPage().login();

        homePage.notification()
                .shouldBe(visible)
                .shouldHave(partialText("Signed")
                        .or(partialText("Успешный"))
                );

        homePage.sidebar().openLaunchesPage()
                .launchTable()
                .shouldBe(visible);
    }

    @DisplayName("Display launches list")
    @Test
    void shouldDisplayLaunchesList() {
        LaunchesPage launchesPage = pageHelper.getLaunchesPage();

        launchesPage.launchRows()
                .shouldHave(sizeGreaterThanOrEqual(launchesHelper.responseSize()));
    }

    @DisplayName("Find launches by name")
    @Test
    void shouldFindLaunchByName() {
        LaunchesPage launchesPage = pageHelper.getLaunchesPage();

        String launchName = launchesHelper.getFirstLaunch().getName();
        launchesPage.search(launchName);

        launchesPage.launchRows()
                .first()
                .shouldHave(text(launchName));
    }

    @DisplayName("Empty result for find unknown launch")
    @Test
    void shouldShowEmptyStateForUnknownLaunch() {
        LaunchesPage launchesPage = pageHelper.getLaunchesPage();

        launchesPage.search("launch-that-does-not-exist");

        launchesPage.emptySearchResult()
                .shouldBe(visible)
                .shouldHave(text("No results found"));
    }

    @DisplayName("Clear search input filter")
    @Test
    void shouldClearSearchFilter() {
        LaunchesPage launchesPage = pageHelper.getLaunchesPage();

        launchesPage.search(launchesHelper.getFirstLaunch().getName())
                .launchRows()
                .shouldHave(size(1));

        launchesPage.clearSearch()
                .launchRows()
                .shouldHave(sizeGreaterThan(1));

    }

    @DisplayName("Save search filter")
    @Test
    void shouldSaveSearchFilter() {
        LaunchesPage launchesPage = pageHelper.getLaunchesPage();

        String filterName = pageHelper.appName(new Faker(), 3);

        launchesPage.search(launchesHelper.getFirstLaunch().getName())
                .saveFilter(filterName)
                .sidebar()
                .openLaunchesPage()
                .filterItem(filterName)
                .shouldBe(visible);
    }

    @DisplayName("Sort launches by name")
    @Test
    void shouldSortLaunchesByName() {
        LaunchesPage launchesPage = pageHelper.getLaunchesPage();

        List<String> expected = launchesPage.launchNamesFromLaunchRows();
        Collections.sort(expected);

        launchesPage.sortByName();
        List<String> actual = launchesPage.launchNamesFromLaunchRows();

        assertEquals(expected, actual);
        assertEquals(expected.getFirst(), actual.getFirst());
    }

    @DisplayName("Change page size")
    @ParameterizedTest
    @ValueSource(ints = {
            SourceValueConstants.THREE_ELEMENTS_PER_PAGE,
            SourceValueConstants.FIFTY_ELEMENTS_PER_PAGE}
    )
    void shouldChangePageSize(int pageSize) {
        LaunchesPage launchesPage = pageHelper.getLaunchesPage();

        launchesPage.changePageSize(pageSize)
                .launchRows()
                .shouldBe(sizeLessThanOrEqual(pageSize));
    }

    @DisplayName("Switch page next-previous")
    @ParameterizedTest
    @ValueSource(ints = {
            SourceValueConstants.THREE_ELEMENTS_PER_PAGE,
            SourceValueConstants.FIFTY_ELEMENTS_PER_PAGE}
    )
    void shouldSwitchPages(int pageSize) {
        LaunchesPage launchesPage = pageHelper.getLaunchesPage();

        launchesPage.changePageSize(pageSize);

        Assumptions.assumeTrue(!launchesPage.pageButtons().isEmpty(), "Отсутствуют элементы навигации");

        int currentPageNumber = launchesPage.currentPageNumber();

        launchesPage.nextPage()
                .activePage()
                .shouldHave(text(String.valueOf(currentPageNumber + 1)));

        launchesPage.prevPage()
                .activePage()
                .shouldHave(text(String.valueOf(currentPageNumber)));
    }

    @DisplayName("Mark all launch rows")
    @Test
    void shouldMarkAllLaunchRows() {
        LaunchesPage launchesPage = pageHelper.getLaunchesPage();

        launchesPage.checkboxAllRows()
                .selectedItems()
                .shouldBe(size(launchesPage.launchRows().size()));
    }

    @DisplayName("Refresh launches list")
    @Test
    void shouldRefreshLaunchesList() {
        LaunchesPage launchesPage = pageHelper.getLaunchesPage();

        int before = launchesPage.launchRows().size();

        launchesHelper.addLaunch(launchesHelper.createLaunch());

        launchesPage.refresh()
                .launchRows()
                .shouldHave(sizeGreaterThanOrEqual(before + 1));
    }
}
