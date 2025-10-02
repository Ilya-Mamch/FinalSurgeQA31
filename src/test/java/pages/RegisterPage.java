package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class RegisterPage {

    private final String FIRST_NAME_FIELD_CSS = "#create_first";
    private final String LAST_NAME_FIELD_CSS = "#create_last";
    private final String EMAIL_FIELD_CSS = "#create_email";
    private final String PASSWORD_FIELD_CSS = "#password_meter";
    private final String RETYPE_FIELD_CSS = "#create_passwordmatch";
    private final String ERROR_MESSAGE_CSS = "label.error";
    private final String INVALID_MESSAGE_XPATH = "//*[@id='login-wrapper']//div[contains(@class,'alert-error')]";
    private final String CREATE_NEW_ACCOUNT_BUTTON_XPATH = "//button[contains(@class,'btn-beoro-1')]";

    @Step("Открытие страницы регистрации")
    public RegisterPage openPage() {
        log.info("Opening Register page");
        open("/register");
        return this;
    }

    @Step("Регистрация нового пользователя: {name} {lastName}, email={email}")
    public CalendarPage register(String name, String lastName, String email, String password, String retypePassword) {
        log.info("Registering new user: {} {}, email={}", name, lastName, email);
        $(FIRST_NAME_FIELD_CSS).setValue(name);
        $(LAST_NAME_FIELD_CSS).setValue(lastName);
        $(EMAIL_FIELD_CSS).setValue(email);
        $(PASSWORD_FIELD_CSS).setValue(password);
        $(RETYPE_FIELD_CSS).setValue(retypePassword);
        $x(CREATE_NEW_ACCOUNT_BUTTON_XPATH).click();
        return new CalendarPage();
    }

    @Step("Ввод имени: {name}")
    public RegisterPage setName(String name) {
        log.info("Setting first name: {}", name);
        $(FIRST_NAME_FIELD_CSS).setValue(name);
        return this;
    }

    @Step("Ввод фамилии: {lastName}")
    public RegisterPage setLastName(String lastName) {
        log.info("Setting last name: {}", lastName);
        $(LAST_NAME_FIELD_CSS).setValue(lastName);
        return this;
    }

    @Step("Ввод email: {email}")
    public RegisterPage setEmail(String email) {
        log.info("Setting email: {}", email);
        $(EMAIL_FIELD_CSS).setValue(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegisterPage setPassword(String password) {
        log.info("Setting password");
        $(PASSWORD_FIELD_CSS).setValue(password);
        return this;
    }

    @Step("Ввод повторного пароля")
    public RegisterPage setRetypePassword(String retypePassword) {
        log.info("Setting retype password");
        $(RETYPE_FIELD_CSS).setValue(retypePassword);
        return this;
    }

    @Step("Нажатие кнопки 'Create New Account'")
    public CalendarPage clickCreateNewAccount() {
        log.info("Clicking 'Create New Account' button");
        $x(CREATE_NEW_ACCOUNT_BUTTON_XPATH).click();
        return new CalendarPage();
    }

    @Step("Проверка сообщения об ошибке: {error}")
    public RegisterPage checkErrorMessage(String error) {
        log.info("Checking error message: {}", error);
        $(ERROR_MESSAGE_CSS).shouldHave(text(error));
        return this;
    }

    @Step("Проверка сообщения о неверной регистрации: {invalid}")
    public RegisterPage checkInvalidMessage(String invalid) {
        log.info("Checking invalid message: {}", invalid);
        $x(INVALID_MESSAGE_XPATH).shouldBe(visible).shouldHave(text(invalid));
        return this;
    }
}
