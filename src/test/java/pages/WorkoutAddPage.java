package pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

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

    public WorkoutAddPage openPage() {
        open("/WorkoutAdd");
        return this;
    }

    public void addWorkout(String data, String workoutName, String workoutDescription, String distance,
                           boolean selectFartlek) {
        $x(RUN_BUTTON_XPATH).shouldBe(visible).click();

        if (selectFartlek) {
            $x(FARTLEK_BUTTON_CSS).click();
        }

        $(DATA_FIELD_CSS).setValue(data);
        $(WORKOUT_NAME_FIELD_CSS).setValue(workoutName);
        $(WORKOUT_DESCRIPTION_FIELD_CSS).setValue(workoutDescription);
        $(DISTANCE_FIELD_CSS).setValue(distance);
        $(SAVE_BUTTON_CSS).click();
    }

    public void clickRunButton() {
        $x(RUN_BUTTON_XPATH).shouldBe(visible).click();
    }

    public void clickFartlekButton(boolean selectFartlek) {
        if (selectFartlek) {
            $x(FARTLEK_BUTTON_CSS).click();
        }
    }

    public void setData(String data) {
        $(DATA_FIELD_CSS).setValue(data);
    }

    public void setWorkoutName(String workoutName) {
        $(WORKOUT_NAME_FIELD_CSS).setValue(workoutName);
    }

    public void setWorkoutDescription(String workoutDescription) {
        $(WORKOUT_DESCRIPTION_FIELD_CSS).setValue(workoutDescription);
    }

    public void setDistance(String distance) {
        $(DISTANCE_FIELD_CSS).setValue(distance);
    }

    public void clickSaveButton() {
        $(SAVE_BUTTON_CSS).click();
    }

    public WorkoutAddPage errorMessage(String error) {
        $x(ERROR_MESSAGE_XPATH).shouldBe(visible).shouldHave(text(error));
        return this;
    }

    public void basicIsEnabled() {
        $(BASIC_WORKOUT_BUTTON_CSS).shouldBe(visible).shouldBe(enabled);
    }

    public void basicIsDisabled() {
        $(BASIC_WORKOUT_BUTTON_CSS).shouldBe(visible).shouldBe(disabled);
    }

    public void advancedIsEnabled() {
        $(ADVANCED_WORKOUT_BUTTON_CSS).shouldBe(visible).shouldBe(enabled);
    }

    public void advancedIsDisabled() {
        $(ADVANCED_WORKOUT_BUTTON_CSS).shouldBe(visible).shouldBe(disabled);
    }

    public void clickCalendarButton() {
        $(CALENDAR_BUTTON_CSS).click();
    }

    public void clickDataInCalendar(String day) {
        $$(CALENDAR_DATA_BUTTON_CSS).findBy(text(day)).click();
    }

    public void clickAdvancedButton() {
        $(ADVANCED_WORKOUT_BUTTON_CSS).click();
    }
}
