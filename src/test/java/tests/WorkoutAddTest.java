package tests;

import dto.Workout;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Retry;

public class WorkoutAddTest extends BaseTest {

    Workout workoutPositive = Workout.builder()
            .date("9/27/2025")
            .workoutName("Test")
            .description("Test number one")
            .distance("20")
            .selectFartlek(true)
            .build();

    Workout workoutWithOnlyDate = Workout.builder()
            .date("9/27/2025")
            .build();

    @Test(description = "Workout: positive case with all fields filled", retryAnalyzer = Retry.class)
    public void checkPositiveAddWorkout() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        workoutAddPage.openPage()
                .addWorkout(workoutPositive)
                .pageIsOpened();
    }

    @Test(description = "Workout: positive case with subtype Fartlek", retryAnalyzer = Retry.class)
    public void checkPositiveAddWorkoutWithFartlek() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        workoutAddPage.openPage()
                .addWorkout(workoutPositive)
                .pageIsOpened()
                .checkActivitySubtype("Fartlek");
    }

    @Test(description = "Workout: positive case with only date field", retryAnalyzer = Retry.class)
    public void checkPositiveAddWorkoutWithValidDate() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        workoutAddPage.openPage()
                .addWorkout(workoutWithOnlyDate)
                .pageIsOpened();
    }

    @Test(description = "Workout: select date from calendar and verify in input field", retryAnalyzer = Retry.class)
    public void checkAddWorkoutWithDateInCalendar() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        workoutAddPage.openPage()
                .clickRunButton();
        workoutAddPage.clickCalendarButton()
                .clickDataInCalendar("28")
                .checkDateFieldValue("9/28/2025");
    }

    @Test(description = "Workout: verify Basic Workout option is enabled by default", retryAnalyzer = Retry.class)
    public void chechEnabledBasicWorkout() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        workoutAddPage.openPage()
                .clickRunButton()
                .basicIsEnabled();
    }

    @Test(description = "Workout: switch to Advanced Workout and verify enabled", retryAnalyzer = Retry.class)
    public void checkSwitchingToAdvancedWorkout() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        workoutAddPage.openPage()
                .clickRunButton()
                .clickAdvancedButton()
                .advancedIsEnabled();
    }

    @DataProvider(name = "workoutNegativeData")
    public Object[][] workoutNegativeData() {
        return new Object[][]{
                // date , distance , expectedError
                {"", null, "Please enter a value for Workout Date."},
                {"13/27/2025", null, "Please enter a valid Workout Date."},
                {null, "test", "Please enter a valid Distance."},
                {null, "#", "Please enter a valid Distance."},
                {null, "13km", "Please enter a valid Distance."},
                {null, "-13", "The Distance cannot be less than 0.00."}
        };
    }

    @Test(dataProvider = "workoutNegativeData",
            description = "Workout: negative cases with invalid date or distance",
            retryAnalyzer = Retry.class)
    public void checkNegativeWorkout(String date,
                                     String distance,
                                     String expectedError) {

        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        workoutAddPage.openPage()
                .clickRunButton();
        if (date != null) {
            workoutAddPage.setDate(date);
        }
        if (distance != null) {
            workoutAddPage.setDistance(distance);
        }
        workoutAddPage.clickSaveButton();
        workoutAddPage.checkErrorMessage(expectedError);
    }
}




