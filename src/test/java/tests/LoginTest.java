package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Retry;


public class LoginTest extends BaseTest {

    @Test(description = "Login: positive without remember", retryAnalyzer = Retry.class)
    public void checkPositiveLogin() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin()
                .pageIsOpened();
    }

    @Test(description = "Login: positive with remember", retryAnalyzer = Retry.class)
    public void checkPositiveLoginWithRemember() {
        loginPage.openPage()
                .login(user, password, true)
                .clickLogin()
                .pageIsOpened();
    }

    @DataProvider(name = "loginNegativeData")
    public Object[][] loginNegativeData() {
        return new Object[][]{
                // name, password, remember, expectedError1, expectedError2, expectedInvalid
                {"", "", false, "Please enter your e-mail address.", "Please enter a password.", null},
                {"", password, false, "Please enter your e-mail address.", null, null},
                {user, "", false, "Please enter a password.", null, null},
                {"test1", password, false, "Please enter a valid email address.", null, null},
                {user, "test1", false, null, null, "Invalid login credentials. Please try again."},
                {"test1", "test1", false, "Please enter a valid email address.", null, null}
        };
    }

    @Test(dataProvider = "loginNegativeData", description = "Login: negative cases", retryAnalyzer = Retry.class)
    public void checkNegativeLogin(String name,
                                   String password,
                                   boolean remember,
                                   String expectedError1,
                                   String expectedError2,
                                   String expectedInvalid) {
        loginPage.openPage()
                .login(name, password, remember)
                .clickLogin();
        if (expectedError1 != null && expectedError2 != null) {
            loginPage.checkErrorMessages(expectedError1, expectedError2);
        } else if (expectedError1 != null) {
            loginPage.errorMessage(expectedError1);
        } else if (expectedInvalid != null) {
            loginPage.checkInvalidMessage(expectedInvalid);
        }
    }
}

