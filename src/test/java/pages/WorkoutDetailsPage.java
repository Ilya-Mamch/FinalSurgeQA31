package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class WorkoutDetailsPage {

    private final String FARTLEK_IN_WORKOUT_DETAILS_CSS = ".activityTypeName";
    private final String VIEV_AND_EDIT_XPATH = "//ul[@id='breadcrumbs']//li/span[text()='View and Edit your workout.']";

    @Step("Проверка, что страницы Workout details открыта")
    public WorkoutDetailsPage pageIsOpened() {
        log.info("Checking that Workout details page is opened");
        $x(VIEV_AND_EDIT_XPATH).shouldBe(visible);
        return this;
    }

    @Step("Проверка, что выбранный подтип тренировки отображается: {expectedSubtype}")
    public WorkoutDetailsPage checkActivitySubtype(String expectedSubtype) {
        log.info("Checking that activity subtype is '{}'", expectedSubtype);
        $(FARTLEK_IN_WORKOUT_DETAILS_CSS).shouldBe(visible).shouldHave(text(expectedSubtype));
        return this;
    }
}
