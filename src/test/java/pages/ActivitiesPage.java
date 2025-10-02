package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.awt.*;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


@Log4j2
public class ActivitiesPage {

    private final String NEW_ACTIVITY_FIELD_CSS = "#ATypeName";
    private final String BACKGROUND_COLOR_BUTTON_XPATH = "//*[@id='cp3']/input";
    private final String WHITE_TEXT_COLOR_BUTTON_CSS = "#TC1";
    private final String BLACK_TEXT_COLOR_BUTTON_CSS = "#TC2";
    private final String SAVE_BUTTON_CSS = "#saveButton";
    private final String ERROR_MESSAGE_CSS = "label.error";
    private final String INVALID_MESSAGE_XPATH = "//div[contains(@class,'alert-error')]";

    @Step("Открытие страницы Activities")
    public ActivitiesPage openPage() {
        log.info("Opening Activities page");
        open("/Activities");
        return this;
    }

    @Step("Ввод type: '{type}'")
    public ActivitiesPage setType(String type) {
        log.info("Setting activity type: {}", type);
        $(NEW_ACTIVITY_FIELD_CSS).setValue(type);
        return this;
    }

    @Step("Ввод цвета: '{color}'")
    public ActivitiesPage setBackgroundColor(String color) {
        log.info("Setting background color: {}", color);
        $x(BACKGROUND_COLOR_BUTTON_XPATH).setValue(color);
        return this;
    }

    @Step("Выбор цвета текста: Белый")
    public ActivitiesPage clickWhiteButton() {
        log.info("Clicking on White Text Color button");
        $(WHITE_TEXT_COLOR_BUTTON_CSS).click();
        return this;
    }

    @Step("Выбор цвета текста: Черный")
    public ActivitiesPage clickBlackButton() {
        log.info("Clicking on Black Text Color button");
        $(BLACK_TEXT_COLOR_BUTTON_CSS).click();
        return this;
    }

    @Step("Нажатие кнопки сохранения")
    public ActivitiesPage clickSaveButton() {
        log.info("Clicking Save button");
        $(SAVE_BUTTON_CSS).click();
        return this;
    }

    @Step("Проверка сообщения об ошибке: '{error}'")
    public ActivitiesPage checkErrorMessage(String error) {
        log.info("Checking error message: {}", error);
        $(ERROR_MESSAGE_CSS).shouldHave(text(error));
        return this;
    }

    @Step("Проверка сообщения о невалидных данных: {invalid}")
    public ActivitiesPage checkInvalidMessage(String invalid) {
        log.info("Checking invalid message: {}", invalid);
        $x(INVALID_MESSAGE_XPATH).shouldHave(text(invalid));
        return this;
    }

    @Step("Проверка создания activity '{type}'")
    public ActivitiesPage checkActivityCreated(String type) {
        log.info("Checking that activity '{}' is created and visible", type);
        $$("div.w-box")
                .findBy(text(type))
                .shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    @Step("Проверка цвета метки активности '{type}'")
    public ActivitiesPage checkActivityLabelColor(String type, String expectedHex) {
        String expectedRgba = hexToRgba(expectedHex);
        log.info("Checking label color for activity '{}'. Expected HEX={}, converted RGBA={}",
                type, expectedHex, expectedRgba);
        SelenideElement label = $$("div.w-box")
                .findBy(text(type))
                .find("span.label");
        label.shouldHave(cssValue("background-color", expectedRgba));
        return this;
    }

    private String hexToRgba(String hex) {
        Color color = Color.decode(hex);
        return String.format("rgba(%d, %d, %d, 1)",
                color.getRed(), color.getGreen(), color.getBlue());
    }

    @Step("Раскрыть активность '{type}'")
    public ActivitiesPage expandActivity(String type) {
        log.info("Expanding activity '{}'", type);
        SelenideElement header = $$("div.w-box")
                .findBy(text(type))
                .find("div.w-box-header");
        header.click();
        return this;
    }

    @Step("Проверка выбранного цвета текста у активности '{type}'")
    public ActivitiesPage checkTextColorText(String type, String expected) {
        log.info("Checking selected text color for activity '{}'. Expected={}", type, expected);
        SelenideElement activityBlock = $$("div.w-box")
                .findBy(text(type));

        if (expected.equalsIgnoreCase("white")) {
            activityBlock.$("input[name='TextColor'][value='0']").shouldBe(selected);
        } else if (expected.equalsIgnoreCase("black")) {
            activityBlock.$("input[name='TextColor'][value='1']").shouldBe(selected);
        }
        return this;
    }
}
