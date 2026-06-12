package ru.effectivemobile.ui;

import net.datafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.effectivemobile.api.data.LaunchFactory;
import ru.effectivemobile.api.models.CreateLaunchResponse;
import ru.effectivemobile.api.models.LaunchResponse;
import ru.effectivemobile.api.services.LaunchesService;
import ru.effectivemobile.ui.pages.AuthorizedPage;
import ru.effectivemobile.ui.pages.LaunchesPage;
import ru.effectivemobile.ui.pages.LoginPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
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
    private static final List<LaunchResponse> responses = new ArrayList<>();

    @BeforeAll
    static void setup() {
        Stream.generate(LaunchesUiTest::createLaunch)
                .map(CreateLaunchResponse::getId)
                .map(id -> launchesService.getLaunchById(id).as(LaunchResponse.class))
                .limit(5)
                .forEach(responses::add);
    }

    @AfterAll
    static void teardown() {
        responses.forEach(resp -> {
            launchesService.finishLaunch(resp.getUuid(), LaunchFactory.finish());
            launchesService.deleteLaunch(resp.getId());
        });
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
        LaunchesPage launchesPage = getLaunchesPage();

        launchesPage.launchRows()
                .shouldHave(sizeGreaterThanOrEqual(responses.size()));
    }

    @DisplayName("Find launches by name")
    @Test
    void shouldFindLaunchByName() {
        LaunchesPage launchesPage = getLaunchesPage();

        String launchName = responses.getFirst().getName();
        launchesPage.search(launchName);

        launchesPage.launchRows()
                .first()
                .shouldHave(text(launchName));
    }

    @DisplayName("Empty result for find unknown launch")
    @Test
    void shouldShowEmptyStateForUnknownLaunch() {
        LaunchesPage launchesPage = getLaunchesPage();

        launchesPage.search("launch-that-does-not-exist");

        launchesPage.emptySearchResult()
                .shouldBe(visible)
                .shouldHave(text("No results found"));
    }

    @DisplayName("Clear search input filter")
    @Test
    void shouldClearSearchFilter() {
        LaunchesPage launchesPage = getLaunchesPage();

        launchesPage.search(responses.getFirst().getName())
                .launchRows()
                .shouldHave(size(1));

        launchesPage.clearSearch()
                .launchRows()
                .shouldHave(sizeGreaterThan(1));

    }

    @DisplayName("Save search filter")
    @Test
    void shouldSaveSearchFilter() {
        LaunchesPage launchesPage = getLaunchesPage();

        String filterName = appName(new Faker(), 3);

        launchesPage.search(responses.getLast().getName())
                .saveFilter(filterName)
                .sidebar()
                .openLaunchesPage()
                .filterItem(filterName)
                .shouldBe(visible);
    }

    @DisplayName("Sort launches by name")
    @Test
    void shouldSortLaunchesByName() {
        LaunchesPage launchesPage = getLaunchesPage();

        List<String> expected = launchesPage.launchNamesFromLaunchRows();
        Collections.sort(expected);

        launchesPage.sortByName();
        List<String> actual = launchesPage.launchNamesFromLaunchRows();

        assertEquals(expected, actual);
        assertEquals(expected.getFirst(), actual.getFirst());
    }

    @DisplayName("Change page size")
    @ParameterizedTest
    @ValueSource(ints = {3, 50})
    void shouldChangePageSize(int pageSize) {
        LaunchesPage launchesPage = getLaunchesPage();

        launchesPage.changePageSize(pageSize)
                .launchRows()
                .shouldBe(sizeLessThanOrEqual(pageSize));
    }

    @DisplayName("Switch page next-previous")
    @ParameterizedTest
    @ValueSource(ints = {3, 50})
    void shouldSwitchPages(int pageSize) {
        LaunchesPage launchesPage = getLaunchesPage();

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
        LaunchesPage launchesPage = getLaunchesPage();

        launchesPage.checkboxAllRows()
                .selectedItems()
                .shouldBe(size(launchesPage.launchRows().size()));
    }

    @DisplayName("Refresh launches list")
    @Test
    void shouldRefreshLaunchesList() {
        LaunchesPage launchesPage = getLaunchesPage();

        int before = launchesPage.launchRows().size();

        addLaunch();

        launchesPage.refresh()
                .launchRows()
                .shouldHave(sizeGreaterThanOrEqual(before + 1));
    }

    private LaunchesPage getLaunchesPage() {
        return new LoginPage().login()
                .sidebar()
                .openLaunchesPage();
    }

    private static CreateLaunchResponse createLaunch() {
        return launchesService.createLaunch(LaunchFactory.create())
                .as(CreateLaunchResponse.class);
    }

    private static void addLaunch() {
        UUID id = createLaunch().getId();
        responses.add(launchesService.getLaunchById(id).as(LaunchResponse.class));
    }

    private static String appName(Faker faker, int minLength) {
        String name;
        do {
            name = faker.app().name();
        } while (name.length() < minLength);

        return name;
    }
}
