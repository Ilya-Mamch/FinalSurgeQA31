package pages;

import dto.Workout;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class WorkoutAddPage {

    private final String RUN_BUTTON_XPATH = "//a[@data-code='run']";
    private final String DATA_FIELD_CSS = "#WorkoutDate";
    private final String WORKOUT_NAME_FIELD_CSS = "#Name";
    private final String WORKOUT_DESCRIPTION_FIELD_CSS = "#Desc";
    private final String DISTANCE_FIELD_CSS = "#Distance";
    private final String SAVE_BUTTON_CSS = "#saveButton";
    private final String FARTLEK_BUTTON_CSS = "//li[@data-guid='00000001-0001-0001-0001-000000000001']/a";
    private final String ERROR_MESSAGE_XPATH = "//div[contains(@class,'alert-error')]";
    private final String BASIC_WORKOUT_BUTTON_CSS = "#tBasic";
    private final String ADVANCED_WORKOUT_BUTTON_CSS = "#tIntervals";
    private final String CALENDAR_BUTTON_CSS = "#w-date .add-on";
    private final String CALENDAR_DATA_BUTTON_CSS = ".day";
    private final String FARTLEK_IN_WORKOUT_DETAILS_CSS = ".activityTypeName";
    private final String WORKOUT_PAGE_IS_OPENED_CSS = "#noneselected";

    @Step("Открытие страницы добавления тренировки")
    public WorkoutAddPage openPage() {
        log.info("Opening Workout Add page");
        open("/WorkoutAdd");
        return this;
    }

    @Step("Добавление тренировки: {dto.workoutName}, дата={dto.date}, дистанция={dto.distance}")
    public WorkoutDetailsPage addWorkout(Workout dto) {
        log.info("Adding workout: name='{}', date={}, distance={}",
                dto.getWorkoutName(), dto.getDate(), dto.getDistance());
        $x(RUN_BUTTON_XPATH).shouldBe(visible).click();
        if (dto.isSelectFartlek()) {
            log.info("Selecting Fartlek option");
            $x(FARTLEK_BUTTON_CSS).click();
        }
        $(DATA_FIELD_CSS).setValue(dto.getDate());
        $(WORKOUT_NAME_FIELD_CSS).setValue(dto.getWorkoutName());
        $(WORKOUT_DESCRIPTION_FIELD_CSS).setValue(dto.getDescription());
        $(DISTANCE_FIELD_CSS).setValue(dto.getDistance());
        log.info("Clicking Save button");
        $(SAVE_BUTTON_CSS).click();
        return new WorkoutDetailsPage();
    }

    @Step("Нажатие кнопки Run")
    public WorkoutAddPage clickRunButton() {
        log.info("Clicking Run button");
        $x(RUN_BUTTON_XPATH).shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие кнопки Fartlek (если выбрано)")
    public WorkoutAddPage clickFartlekButton(boolean selectFartlek) {
        if (selectFartlek) {
            log.info("Clicking Fartlek button");
            $x(FARTLEK_BUTTON_CSS).click();
        }
        return this;
    }

    @Step("Ввод даты тренировки: {data}")
    public WorkoutAddPage setDate(String data) {
        log.info("Setting workout date: {}", data);
        $(DATA_FIELD_CSS).setValue(data);
        return this;
    }

    @Step("Ввод названия тренировки: {workoutName}")
    public WorkoutAddPage setWorkoutName(String workoutName) {
        log.info("Setting workout name: {}", workoutName);
        $(WORKOUT_NAME_FIELD_CSS).setValue(workoutName);
        return this;
    }

    @Step("Ввод описания тренировки")
    public WorkoutAddPage setWorkoutDescription(String workoutDescription) {
        log.info("Setting workout description: {}", workoutDescription);
        $(WORKOUT_DESCRIPTION_FIELD_CSS).setValue(workoutDescription);
        return this;
    }

    @Step("Ввод дистанции: {distance}")
    public WorkoutAddPage setDistance(String distance) {
        log.info("Setting workout distance: {}", distance);
        $(DISTANCE_FIELD_CSS).setValue(distance);
        return this;
    }

    @Step("Нажатие кнопки Save")
    public WorkoutAddPage clickSaveButton() {
        log.info("Clicking Save button");
        $(SAVE_BUTTON_CSS).click();
        return this;
    }

    @Step("Проверка сообщения об ошибке: {error}")
    public WorkoutAddPage checkErrorMessage(String error) {
        log.info("Checking error message: {}", error);
        $x(ERROR_MESSAGE_XPATH).shouldBe(visible).shouldHave(text(error));
        return this;
    }

    @Step("Проверка, что кнопка Basic Workout активна")
    public WorkoutAddPage basicIsEnabled() {
        log.info("Checking Basic Workout button is enabled");
        $(BASIC_WORKOUT_BUTTON_CSS).shouldBe(visible).shouldBe(enabled);
        return this;
    }

    @Step("Проверка, что кнопка Basic Workout неактивна")
    public WorkoutAddPage basicIsDisabled() {
        log.info("Checking Basic Workout button is disabled");
        $(BASIC_WORKOUT_BUTTON_CSS).shouldBe(visible).shouldBe(disabled);
        return this;
    }

    @Step("Проверка, что кнопка Advanced Workout активна")
    public WorkoutAddPage advancedIsEnabled() {
        log.info("Checking Advanced Workout button is enabled");
        $(ADVANCED_WORKOUT_BUTTON_CSS).shouldBe(visible).shouldBe(enabled);
        return this;
    }

    @Step("Проверка, что кнопка Advanced Workout неактивна")
    public WorkoutAddPage advancedIsDisabled() {
        log.info("Checking Advanced Workout button is disabled");
        $(ADVANCED_WORKOUT_BUTTON_CSS).shouldBe(visible).shouldBe(disabled);
        return this;
    }

    @Step("Нажатие кнопки Calendar")
    public WorkoutAddPage clickCalendarButton() {
        log.info("Clicking Calendar button");
        $(CALENDAR_BUTTON_CSS).click();
        return this;
    }

    @Step("Выбор даты {day} в календаре")
    public WorkoutAddPage clickDataInCalendar(String day) {
        log.info("Clicking date '{}' in calendar", day);
        $$(CALENDAR_DATA_BUTTON_CSS).findBy(text(day)).click();
        return this;
    }

    @Step("Нажатие кнопки Advanced Workout")
    public WorkoutAddPage clickAdvancedButton() {
        log.info("Clicking Advanced Workout button");
        $(ADVANCED_WORKOUT_BUTTON_CSS).click();
        return this;
    }

    @Step("Проверка, что в поле даты отображается значение: {expectedDate}")
    public WorkoutAddPage checkDateFieldValue(String expectedDate) {
        log.info("Checking that WorkoutDate field has value '{}'", expectedDate);
        $(DATA_FIELD_CSS).shouldHave(value(expectedDate));
        return this;
    }

    @Step("Проверка, что страница Workout Add открыта")
    public WorkoutAddPage pageIsOpened() {
        log.info("Checking that Workout Add page is opened");
        $(WORKOUT_PAGE_IS_OPENED_CSS)
                .shouldHave(text("Please select an Activity Type to begin adding your workout."));
        return this;
    }
}
