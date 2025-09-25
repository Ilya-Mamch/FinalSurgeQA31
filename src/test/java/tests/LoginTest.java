package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void checkPositiveLogin() {
        loginPage.openPage()
                .login("ilamamcik35@gmail.com", "TestQAIlya31", false);
        loginPage.clickLogin();
    }

    @Test
    public void checkPositiveLoginWithRemember() {
        loginPage.openPage()
                .login("ilamamcik35@gmail.com", "TestQAIlya31", true);
    }

    @Test
    public void checkLoginWithEmptyFields() {
        loginPage.openPage()
                .login("", "", false);
        loginPage.errorMessages("Please enter your e-mail address.", "Please enter a password.");
    }

    @Test
    public void checkLoginWithEmptyEmail() {
        loginPage.openPage()
                .login("", "TestQAIlya31", false);
        loginPage.errorMessage("Please enter your e-mail address.");
    }

    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.openPage()
                .login("ilamamcik35@gmail.com", "", false);
        loginPage.errorMessage("Please enter a password.");
    }

    @Test
    public void checkLoginWithInvalidEmail() {
        loginPage.openPage()
                .login("test1", "TestQAIlya31", false);
        loginPage.errorMessage("Please enter a valid email address.");
    }

    @Test
    public void checkLoginWithInvalidPassword() {
        loginPage.openPage()
                .login("ilamamcik35@gmail.com", "test1", false);
        loginPage.invalidMessage("Invalid login credentials. Please try again.");
    }

    @Test
    public void checkLoginWithInvalidPasswordAndEmail() {
        loginPage.openPage()
                .login("test1", "test1", false)
                .errorMessage("Please enter a valid email address.");
    }
}
