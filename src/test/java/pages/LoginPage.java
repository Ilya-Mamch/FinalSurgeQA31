package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage {

    private final String EMAIL_FIELD_CSS = "#login_name";
    private final String PASSWORD_FIELD_CSS = "#login_password";
    private final String ERROR_MESSAGE_CSS = "label.error";
    private final String REMEMBER_ME_CSS = "#login_remember";
    private final String INVALID_MESSAGE_XPATH = "//div[contains(@class,'alert-error')]//strong";
    private final String LOGIN_BUTTON_XPATH = "//button[contains(@class,'btn-beoro-1')]";

    SelenideElement rememberMe = $(REMEMBER_ME_CSS);

    @Step("Открытие страницы авторизации")
    public LoginPage openPage() {
        log.info("Opening Login page");
        open("/login");
        return this;
    }

    @Step("Авторизация: пользователь='{name}', rememberMe={remember}")
    public LoginPage login(String name, String password, boolean remember) {
        log.info("Logging in with username='{}', rememberMe={}", name, remember);
        if (rememberMe.isSelected() != remember) {
            log.debug("Toggling Remember Me checkbox");
            rememberMe.click();
        }
        $(EMAIL_FIELD_CSS).setValue(name);
        $(PASSWORD_FIELD_CSS).setValue(password);
        return this;
    }

    @Step("Ввод email: '{name}'")
    public LoginPage setName(String name) {
        log.info("Setting username: {}", name);
        $(EMAIL_FIELD_CSS).setValue(name);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage setPassword(String password) {
        log.info("Setting password");
        $(PASSWORD_FIELD_CSS).setValue(password);
        return this;
    }

    @Step("Выбор состояния Remember me: '{remember}'")
    public LoginPage clickRememberMe(boolean remember) {
        log.info("Clicking Remember Me checkbox, expected state={}", remember);
        if (rememberMe.isSelected() != remember) {
            rememberMe.click();
        }
        return this;
    }

    @Step("Нажатие кнопки Login")
    public CalendarPage clickLogin() {
        log.info("Clicking Login button");
        $x(LOGIN_BUTTON_XPATH).click();
        return new CalendarPage();
    }

    @Step("Проверка сообщения об ошибке: '{error}'")
    public LoginPage errorMessage(String error) {
        log.info("Checking error message: {}", error);
        $(ERROR_MESSAGE_CSS).shouldHave(text(error));
        return this;
    }

    @Step("Проверка сообщений об ошибке: '{firstError}' и '{secondError}'")
    public LoginPage checkErrorMessages(String firstError, String secondError) {
        log.info("Checking two error messages: '{}' and '{}'", firstError, secondError);
        $$(ERROR_MESSAGE_CSS).get(0).shouldHave(text(firstError));
        $$(ERROR_MESSAGE_CSS).get(1).shouldHave(text(secondError));
        return this;
    }

    @Step("Проверка сообщения о неверном логине: {invalid}")
    public LoginPage checkInvalidMessage(String invalid) {
        log.info("Checking invalid login message: {}", invalid);
        $x(INVALID_MESSAGE_XPATH).shouldBe(visible).shouldHave(text(invalid));
        return this;
    }
}
