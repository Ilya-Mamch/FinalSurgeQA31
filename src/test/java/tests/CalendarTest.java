package tests;

import org.testng.annotations.Test;
import utils.Retry;

public class CalendarTest extends BaseTest {

    @Test(description = "Calendar: Quick Add тренировки", retryAnalyzer = Retry.class)
    public void checkQuickAddWorkout() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        calendarPage.clickQuickAddButton()
                .setDate("9/13/2025")
                .setNameWorkout("Testing12")
                .selectActivityType("Run")
                .clickSaveButton()
                .checkWorkoutAdded("Testing12");
    }

    @Test(description = "Calendar: переход по кнопке Full Add", retryAnalyzer = Retry.class)
    public void checkFullAddButton() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        calendarPage.clickFullAddButton();
        workoutAddPage.pageIsOpened();
    }

    @Test(description = "Calendar: проверка включенной кнопки Month", retryAnalyzer = Retry.class)
    public void checkEnabledMonth() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        calendarPage.openPage()
                .checkEnabledMonth();
    }

    @Test(description = "Calendar: переключение на Week view", retryAnalyzer = Retry.class)
    public void checkEnabledWeekButton() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        calendarPage.clickWeekButton()
                .checkEnabledWeekButton();
    }

    @Test(description = "Calendar: открытие контекстного меню дня", retryAnalyzer = Retry.class)
    public void checkOpenContextMenu() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        calendarPage.openContextMenu(27, 9, 2025)
                .checkVisibleContextMenu();
    }

    @Test(description = "Calendar: удаление тренировки через контекстное меню", retryAnalyzer = Retry.class)
    public void checkDeleteWorkoutFromContextMenu() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        calendarPage.openPage()
                .deleteWorkout("Test01", 1, 10, 2025);
    }

    @Test(description = "Calendar: перенос тренировки drag-and-drop", retryAnalyzer = Retry.class)
    public void checkMoveWorkout() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        calendarPage.moveWorkout("Test01", 26, 10, 2025);
    }

    @Test(description = "Calendar: копирование тренировки через контекстное меню", retryAnalyzer = Retry.class)
    public void checkCopyWorkout() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        calendarPage.openPage()
                .copyWorkout("Test01", 26, 10, 2025,
                        5, 10, 2025);
    }

    @Test(description = "Calendar: переход на следующий месяц", retryAnalyzer = Retry.class)
    public void checkSwitchToNextMonth() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        calendarPage.goToNextMonth()
                .checkMonthTitle("November 2025");
    }
}
