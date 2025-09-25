package tests;

import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegisterTest  extends BaseTest{

    @Test
    public void checkPositiveRegistration() {
        registerPage.openPage();
        registerPage.register("Ilya", "Test", "ilamamcik35@gmail.com",
                "TestQAIlya31", "TestQAIlya31");
    }

    @Test
    public void checkRegistrationWithEmptyField() {
        registerPage.openPage();
        registerPage.register("", "", "",
                "", "");
        registerPage.errorMessage("This field is required.");
    }

    @Test
    public void checkRegistrationWithEmptyFirstName() {
        registerPage.openPage();
        registerPage.register("", "Test", "ilamamcik35@gmail.com",
                "123456QA", "123456QA");
        registerPage.errorMessage("This field is required.");
    }

    @Test
    public void checkRegistrationWithEmptyLastName() {
        registerPage.openPage();
        registerPage.register("Ilya", "", "ilamamcik35@gmail.com",
                "123456QA", "123456QA");
        registerPage.errorMessage("This field is required.");
    }

    @Test
    public void checkRegistrationWithEmptyEmail() {
        registerPage.openPage();
        registerPage.register("Ilya", "Test", "",
                "123456QA", "123456QA");
        registerPage.errorMessage("This field is required.");
    }

    @Test
    public void checkRegistrationWithEmptyPassword() {
        registerPage.openPage();
        registerPage.register("Ilya", "Test", "ilamamcik35@gmail.com",
                "", "123456QA");
        registerPage.errorMessage("This field is required.");
    }

    @Test
    public void checkRegistrationWithEmptyRetypePassword() {
        registerPage.openPage();
        registerPage.register("Ilya", "Test", "ilamamcik35@gmail.com",
                "123456QA", "");
        registerPage.errorMessage("This field is required.");
    }

    @Test
    public void checkRegistrationWithInvalidEmail() {
        registerPage.openPage();
        registerPage.register("Ilya", "Test", "ilamamcik35gmail.com",
                "123456QA", "123456QA");
        registerPage.errorMessage("Please enter a valid email address.");
    }

    @Test
    public void checkRegistrationWithInvalidPassword() {
        registerPage.openPage();
        registerPage.register("Ilya", "Test", "ilamamcik@35gmail.com",
                "123456QA", "123456QA");
        registerPage.invalidMessage(" *Please enter a Password value with at least one number, lower-case letter," +
                " and upper-case letter between 7 and 15 characters in length.");
    }

    @Test
    public void checkRegistrationWithInvalidRetypePassword() {
        registerPage.openPage();
        registerPage.register("Ilya", "Test", "ilamamcik@35gmail.com",
                "TestQAIlya31", "TestQAIlya312");
        registerPage.invalidMessage(" The passwords you entered did not match.");
    }
}
