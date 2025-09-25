package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final String EMAIL_FIELD_CSS = "#login_name";
    private final String PASSWORD_FIELD_CSS = "#login_password";
    private final String ERROR_MESSAGE_CSS = "label.error";
    private final String REMEMBER_ME_CSS = "#login_remember";
    private final String INVALID_MESSAGE_XPATH = "//div[contains(@class,'alert-error')]//strong";
    private final String LOGIN_BUTTON_XPATH = "//button[contains(@class,'btn-beoro-1')]";

    SelenideElement rememberMe = $(REMEMBER_ME_CSS);

    public LoginPage openPage() {
        open("/login");
        return this;
    }

    public LoginPage login(String name, String password, boolean remember) {
        if (rememberMe.isSelected() != remember) {
            rememberMe.click();
        }
        $(EMAIL_FIELD_CSS).setValue(name);
        $(PASSWORD_FIELD_CSS).setValue(password);
        return this;
    }

    public LoginPage setName(String name) {
        $(EMAIL_FIELD_CSS).setValue(name);
        return this;
    }

    public LoginPage setPassword(String password) {
        $(PASSWORD_FIELD_CSS).setValue(password);
        return this;
    }

    public LoginPage clickRememberMe(boolean remember) {
        if (rememberMe.isSelected() != remember) {
            rememberMe.click();
        }
        return this;
    }

    public void clickLogin() {
        $x(LOGIN_BUTTON_XPATH).click();
    }

    public LoginPage errorMessage(String error) {
        $(ERROR_MESSAGE_CSS).shouldHave(text(error));
        return this;
    }

    public LoginPage errorMessages(String firstError, String secondError) {
        $$(ERROR_MESSAGE_CSS).get(0).shouldHave(text(firstError));
        $$(ERROR_MESSAGE_CSS).get(1).shouldHave(text(secondError));
        return this;
    }

    public LoginPage invalidMessage(String invalid) {
        $x(INVALID_MESSAGE_XPATH).shouldBe(visible).shouldHave(text(invalid));
        return this;
    }
}
