package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Retry;

public class RegisterTest extends BaseTest {

    @Test(description = "Registration: positive case with valid data", retryAnalyzer = Retry.class)
    public void checkPositiveRegistration() {
        registerPage.openPage()
                .register("Ilya", "Test", user, password, password)
                .pageIsOpened();
    }

    @DataProvider(name = "registrationNegativeData")
    public Object[][] registrationNegativeData() {
        return new Object[][]{
                // name, lastName, email, password, retypePassword, expectedError, expectedInvalid
                {"", "", "", "", "", "This field is required.", null},
                {"", "Test", user, "123456QA", "123456QA", "This field is required.", null},
                {"Ilya", "", user, "123456QA", "123456QA", "This field is required.", null},
                {"Ilya", "Test", "", "123456QA", "123456QA", "This field is required.", null},
                {"Ilya", "Test", user, "", "123456QA", "This field is required.", null},
                {"Ilya", "Test", user, "123456QA", "", "This field is required.", null},
                {"Ilya", "Test", "test", "123456QA", "123456QA", "Please enter a valid email address.", null},
                {"Ilya", "Test", user, "123456QA", "123456QA", null,
                        " *Please enter a Password value with at least one number, lower-case letter, and upper-case " +
                                "letter between 7 and 15 characters in length."},
                {"Ilya", "Test", user, password, "132412", null,
                        " The passwords you entered did not match."}
        };
    }

    @Test(dataProvider = "registrationNegativeData",
            description = "Registration: negative cases with missing, invalid or mismatched data",
            retryAnalyzer = Retry.class)
    public void checkNegativeRegistration(String firstName,
                                          String lastName,
                                          String email,
                                          String password,
                                          String retypePassword,
                                          String expectedError,
                                          String expectedInvalid) {
        registerPage.openPage()
                .register(firstName, lastName, email, password, retypePassword);

        if (expectedError != null) {
            registerPage.checkErrorMessage(expectedError);
        }
        if (expectedInvalid != null) {
            registerPage.checkInvalidMessage(expectedInvalid);
        }
    }
}
