package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.RegisterPage;
import pages.WorkoutAddPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    LoginPage loginPage;
    RegisterPage registerPage;
    WorkoutAddPage workoutAddPage;

    @BeforeMethod
    public void setUP() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://log.finalsurge.com";
        Configuration.timeout = 5000;
        Configuration.clickViaJs = true; // все клики JS
        Configuration.headless = false; // запуск браузера без графического интерфейса
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Configuration.browserCapabilities = options;

        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        workoutAddPage = new WorkoutAddPage();
    }

    @AfterMethod
    public void tearDawn() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }
}
