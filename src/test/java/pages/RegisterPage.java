package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class RegisterPage {

    private final String FIRST_NAME_FIELD_CSS = "#create_first";
    private final String LAST_NAME_FIELD_CSS = "#create_last";
    private final String EMAIL_FIELD_CSS = "#create_email";
    private final String PASSWORD_FIELD_CSS = "#password_meter";
    private final String RETYPE_FIELD_CSS = "#create_passwordmatch";
    private final String ERROR_MESSAGE_CSS = "label.error";
    private final String INVALID_MESSAGE_XPATH = "//*[@id='login-wrapper']//div[contains(@class,'alert-error')]";
    private final String CREATE_NEW_ACCOUNT_BUTTON_XPATH = "//button[contains(@class,'btn-beoro-1')]";

    public RegisterPage openPage() {
        open("/register");
        return this;
    }

    public void register(String name, String lastName, String email, String password, String retypePassword) {
        $(FIRST_NAME_FIELD_CSS).setValue(name);
        $(LAST_NAME_FIELD_CSS).setValue(lastName);
        $(EMAIL_FIELD_CSS).setValue(email);
        $(PASSWORD_FIELD_CSS).setValue(password);
        $(RETYPE_FIELD_CSS).setValue(retypePassword).submit();
    }

    public RegisterPage setName(String name) {
        $(FIRST_NAME_FIELD_CSS).setValue(name);
        return this;
    }

    public RegisterPage setLastName(String lastName) {
        $(LAST_NAME_FIELD_CSS).setValue(lastName);
        return this;
    }

    public RegisterPage setEmail(String email) {
        $(EMAIL_FIELD_CSS).setValue(email);
        return this;
    }

    public RegisterPage setPassword(String password) {
        $(PASSWORD_FIELD_CSS).setValue(password);
        return this;
    }

    public RegisterPage setRetypePassword(String retypePassword) {
        $(RETYPE_FIELD_CSS).setValue(retypePassword);
        return this;
    }

    public void clickCreateNewAccount() {
        $x(CREATE_NEW_ACCOUNT_BUTTON_XPATH).click();
    }

    public RegisterPage errorMessage(String error) {
        $(ERROR_MESSAGE_CSS).shouldHave(text(error));
        return this;
    }

    public RegisterPage invalidMessage(String invalid) {
        $x(INVALID_MESSAGE_XPATH).shouldBe(visible).shouldHave(text(invalid));
        return this;
    }

}
