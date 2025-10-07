package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import utils.AllureUtils;
import utils.PropertyReader;
import utils.TestListener;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {

    LoginPage loginPage;
    RegisterPage registerPage;
    WorkoutAddPage workoutAddPage;
    ActivitiesPage activitiesPage;
    CalendarPage calendarPage;
    WorkoutDetailsPage workoutDetailsPage;
    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

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
        activitiesPage = new ActivitiesPage();
        calendarPage = new CalendarPage();
        workoutDetailsPage = new WorkoutDetailsPage();
    }

    @AfterMethod
    public void tearDawn(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            AllureUtils.takeScreenshot();
        }
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }
}
