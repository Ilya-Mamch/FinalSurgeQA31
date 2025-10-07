package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
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
    @Parameters({"browser"})
    public void setUP(@Optional("chrome") String browser) {
        Configuration.browser = browser;
        Configuration.baseUrl = "https://log.finalsurge.com";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            if (Configuration.headless) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--window-size=1920,1080");
            Configuration.browserCapabilities = options;
        } else if ("firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
            if (Configuration.headless) {
                options.addArguments("--headless");
            }
            Configuration.browserCapabilities = options;
        }

        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        workoutAddPage = new WorkoutAddPage();
        activitiesPage = new ActivitiesPage();
        calendarPage = new CalendarPage();
        workoutDetailsPage = new WorkoutDetailsPage();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            AllureUtils.takeScreenshot();
        }
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }
}
