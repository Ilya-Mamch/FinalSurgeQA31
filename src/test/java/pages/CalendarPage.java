package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class CalendarPage {

    private final String MONTH_BUTTON_XPATH = "//span[contains(@class,'fc-button-month')]";
    private final String SIX_WEEK_BUTTON_XPATH = "//span[text()='6 weeks']";
    private final String WEEK_BUTTON_XPATH = "//span[@class='fc-button-content' and normalize-space()='week']";
    private final String LAST_MONTH_BUTTON_XPATH = "//span[contains(@class,'fc-button-prev')]//i";
    private final String NEXT_MONTH_BUTTON_XPATH = "//span[contains(@class,'fc-button-next')]//i";
    private final String QUICK_ADD_BUTTON_CSS = "#QuickAddToggle";
    private final String FULL_ADD_BUTTON_CSS = "#FullAddBtn";
    private final String DAY_CELL_XPATH = "//td[.//a[@data-day='%s' and @data-month='%s' and @data-year='%s']]";
    private final String DROPDOWN_TOGGLE_CSS = ".calendar-add .dropdown-toggle";
    private final String DROPDOWN_MENU_CSS = ".calendar-add .dropdown-menu";
    private final String QUICK_ADD_CONTEXT_BUTTON_CSS = ".quick-add";
    private final String FULL_ADD_CONTEXT_BUTTON_CSS = ".full-add";
    private final String UPLOAD_DATA_CONTEXT_BUTTON_CSS = ".quick-upload";
    private final String DAILY_VITALS_CONTEXT_BUTTON_CSS = ".vitals-enter";
    private final String LIBRARY_CONTEXT_BUTTON_CSS = ".library-add";
    private final String COPY_DAY_CONTEXT_BUTTON_CSS = ".copy-day";
    private final String MOVE_DAY_CONTEXT_BUTTON_CSS = ".move-day";
    private final String DELETE_CONTEXT_BUTTON_CSS = ".quick-delete";
    private final String COPY_MOVE_DELETE_CONTEXT_BUTTON_CSS = ".copy-week";
    private final String WORKOUT_TITLE_CSS = ".fc-event-activity-title";
    private final String WORKOUT_CONTAINER_CSS = ".fc-event";
    private final String WELCOME_MESSAGE_CSS = ".user-info";
    private final String DATA_FIELD_CSS = "#WorkoutDate";
    private final String SAVE_BUTTON_CSS = "#saveButton";
    private final String SELECT_ACTIVITY_TYPE_CSS = "#ActivityType";
    private final String WORKOUT_NAME_FIELD_CSS = "#Name";
    private final String CONTEXT_MENU_CSS = ".dropdown-menu.pull-right";
    private final String MONTH_HEADER_CSS = "#dpMonth";

    private SelenideElement currentCell; // хранит выбранную ячейку

    @Step("Открытие страницы Calendar")
    public CalendarPage openPage() {
        log.info("Opening Calendar page");
        open("/Calendar");
        return this;
    }

    @Step("Установка даты тренировки: {date}")
    public CalendarPage setDate(String date) {
        log.info("Setting workout date: {}", date);
        $(DATA_FIELD_CSS).setValue(date);
        return this;
    }

    @Step("Нажатие кнопки 'Save'")
    public CalendarPage clickSaveButton() {
        log.info("Clicking Save button");
        $(SAVE_BUTTON_CSS).click();
        return this;
    }

    @Step("Открытие контекстного меню по дате {day}/{month}/{year}")
    public CalendarPage openContextMenu(int day, int month, int year) {
        String cellXpath = String.format(DAY_CELL_XPATH, day, month, year);
        log.info("Opening context menu for date: {}/{}/{}", day, month, year);
        currentCell = $x(cellXpath).scrollIntoView(true); //Находим ячейку, скроллим к ней и сохраняем в currentCell
        currentCell.hover(); // Наводим курсор на ячейку
        currentCell.$(DROPDOWN_TOGGLE_CSS).click(); //Внутри ячейки кликаем по кнопке-иконке "+"
        currentCell.$(DROPDOWN_MENU_CSS).shouldBe(visible); //Ждём, пока контекстное меню станет видимым
        return this;
    }

    @Step("Выбор типа активности: {type}")
    public CalendarPage selectActivityType(String type) {
        log.info("Selecting activity type '{}'", type);
        $(SELECT_ACTIVITY_TYPE_CSS).selectOptionContainingText(type);
        return this;
    }

    @Step("Ввод названия тренировки: {name}")
    public CalendarPage setNameWorkout(String name) {
        log.info("Setting workout name: {}", name);
        $(WORKOUT_NAME_FIELD_CSS).setValue(name);
        return this;
    }

    @Step("Проверка, что тренировка '{workoutTitle}' добавлена в календарь")
    public CalendarPage checkWorkoutAdded(String workoutTitle) {
        log.info("Checking workout '{}' is visible in calendar", workoutTitle);
        $$(WORKOUT_TITLE_CSS).findBy(text(workoutTitle)).shouldBe(visible);
        return this;
    }

    @Step("Проверка отображения контекстного меню")
    public CalendarPage checkVisibleContextMenu() {
        log.info("Checking that context menu is visible");
        currentCell.$(DROPDOWN_MENU_CSS).shouldBe(visible);
        return this;
    }

    @Step("Нажатие кнопки 'Quick Add'")
    public CalendarPage clickQuickAddButton() {
        log.info("Clicking Quick Add button");
        $(QUICK_ADD_BUTTON_CSS).click();
        return this;
    }

    @Step("Проверка включенной кнопки Month")
    public CalendarPage checkEnabledMonth() {
        log.info("Checking that 'Month' button is enabled");
        $x(MONTH_BUTTON_XPATH).shouldBe(enabled);
        return this;
    }

    @Step("Нажатие кнопки '6 weeks'")
    public CalendarPage clickWeekButton() {
        log.info("Clicking '6 weeks' button");
        $x(WEEK_BUTTON_XPATH).click();
        return this;
    }

    @Step("Проверка включенной кнопки '6 weeks'")
    public CalendarPage checkEnabledWeekButton() {
        log.info("Checking that '6 weeks' button is enabled");
        $x(WEEK_BUTTON_XPATH).shouldBe(enabled);
        return this;
    }

    @Step("Проверка наличия опции Quick Add")
    public CalendarPage checkQuickAdd() {
        log.info("Checking Quick Add option is visible");
        currentCell.$(QUICK_ADD_CONTEXT_BUTTON_CSS).shouldBe(visible);
        return this;
    }

    @Step("Проверка наличия опции FullAdd")
    public CalendarPage checkFullAdd() {
        log.info("Checking Full Add option is visible");
        currentCell.$(FULL_ADD_CONTEXT_BUTTON_CSS).shouldBe(visible);
        return this;
    }

    @Step("Проверка наличия опции Upload Data")
    public CalendarPage checkUploadData() {
        log.info("Checking Upload Data option is visible");
        currentCell.$(UPLOAD_DATA_CONTEXT_BUTTON_CSS).shouldBe(visible);
        return this;
    }

    @Step("Проверка наличия опции Daily Vitals")
    public CalendarPage checkDailyVitals() {
        log.info("Checking Daily Vitals option is visible");
        currentCell.$(DAILY_VITALS_CONTEXT_BUTTON_CSS).shouldBe(visible);
        return this;
    }

    @Step("Проверка наличия опции Library")
    public CalendarPage checkLibrary() {
        log.info("Checking Library option is visible");
        currentCell.$(LIBRARY_CONTEXT_BUTTON_CSS).shouldBe(visible);
        return this;
    }

    @Step("Проверка наличия опции Copy day")
    public CalendarPage checkCopyDay() {
        log.info("Checking Copy Day option is visible");
        currentCell.$(COPY_DAY_CONTEXT_BUTTON_CSS).shouldBe(visible);
        return this;
    }

    @Step("Нажатие кнопки 'Full Add'")
    public CalendarPage clickFullAddButton() {
        log.info("Clicking Full Add button");
        $(FULL_ADD_BUTTON_CSS).click();
        return this;
    }

    @Step("Проверка наличия опции Move day")
    public CalendarPage checkMoveDay() {
        log.info("Checking Move Day option is visible");
        currentCell.$(MOVE_DAY_CONTEXT_BUTTON_CSS).shouldBe(visible);
        return this;
    }

    @Step("Проверка наличия опции Copy/Move/Delete")
    public CalendarPage checkCopyMoveDelete() {
        log.info("Checking Copy/Move/Delete option is visible");
        currentCell.$(COPY_MOVE_DELETE_CONTEXT_BUTTON_CSS).shouldBe(visible);
        return this;
    }

    @Step("Перенос тренировки '{workoutTitle}' на дату {day}/{month}/{year}")
    public CalendarPage moveWorkout(String workoutTitle, int day, int month, int year) {
        log.info("Moving workout '{}' to date: {}/{}/{}", workoutTitle, day, month, year);
        SelenideElement workout = $$(WORKOUT_TITLE_CSS)//находим тренировку по названию, которую будем перемещать
                .findBy(text(workoutTitle))
                .closest(WORKOUT_CONTAINER_CSS);
        String targetXpath = String.format(DAY_CELL_XPATH, day, month, year);   //находим ячейку в которую перемещаем
        SelenideElement targetCell = $x(targetXpath);
        actions().dragAndDrop(workout, targetCell).perform(); //перетаскиваем тренировку
        targetCell.$$(WORKOUT_TITLE_CSS) //проверяем, что тренировка появилась в новой ячейке
                .findBy(text(workoutTitle))
                .shouldBe(visible);
        return this;
    }

    @Step("Проверка, что страницы Calendar открыта}")
    public CalendarPage pageIsOpened() {
        log.info("Checking that Calendar page is opened");
        $(WELCOME_MESSAGE_CSS).shouldBe(visible);
        return this;
    }

    @Step("Удаление тренировки '{workoutTitle}' на дату {day}/{month}/{year}")
    public CalendarPage deleteWorkout(String workoutTitle, int day, int month, int year) {
        log.info("Deleting workout '{}' from {}/{}/{}", workoutTitle, day, month, year);
        String cellXpath = String.format(DAY_CELL_XPATH, day, month, year); //Формируем XPath для ячейки календаря
        SelenideElement cell = $x(cellXpath); //Находим ячейку календаря по XPath
        SelenideElement workout = cell.$$(WORKOUT_TITLE_CSS) //Внутри ячейки ищем тренировку по названию и проверяем
                .findBy(text(workoutTitle))
                .shouldBe(visible);
        workout.click();
        SelenideElement menu = $$(".dropdown-menu.pull-right").filter(visible).first();
        menu.$(".quick-delete").shouldBe(visible).click();
        confirmBootboxModal(); //Подтверждаем Bootbox
        cell.$$(WORKOUT_TITLE_CSS).findBy(text(workoutTitle)).shouldNot(exist); //Проверяем что тренировка исчезла
        return this;
    }

    @Step("Подтверждение модального окна Bootbox")
    public CalendarPage confirmBootboxModal() {
        log.info("Confirming Bootbox modal by clicking OK");
        SelenideElement modal = $(".bootbox.modal.fade.in").shouldBe(visible);
        modal.$("a.btn.btn-primary").shouldBe(visible).click();
        return this;
    }

    @Step("Копирование тренировки '{workoutTitle}' с {day}/{month}/{year} на {targetDay}/{targetMonth}/{targetYear}")
    public CalendarPage copyWorkout(String workoutTitle,
                                    int day, int month, int year,
                                    int targetDay, int targetMonth, int targetYear) {
        log.info("Copying workout '{}' from {}/{}/{} to {}/{}/{}",
                workoutTitle, day, month, year, targetDay, targetMonth, targetYear);
        String sourceXpath = String.format(DAY_CELL_XPATH, day, month, year); //исходная ячейка
        SelenideElement cell = $x(sourceXpath); //Находим элемент ячейки календаря по XPath
        cell.$$(WORKOUT_TITLE_CSS).findBy(text(workoutTitle)).shouldBe(visible).click(); //Кликаем по тренировке
        // В открывшемся меню находим видимый dropdown и кликаем по пункту "Copy"
        $$(".dropdown-menu.pull-right").filter(visible).first()
                .$(".quick-copy").shouldBe(visible).click();
        $("#QuickAdd").shouldBe(visible);  //Проверяем, что открылось модальное окно "QuickAdd"
        LocalDate newDate = LocalDate.of(targetYear, targetMonth, targetDay); //Создаём объект LocalDate для новой даты
        String formattedDate = newDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")); //Форматируем дату в строку
        $(DATA_FIELD_CSS).setValue(formattedDate);
        $(SAVE_BUTTON_CSS).click();
        String targetXpath = String.format(DAY_CELL_XPATH, targetDay, targetMonth, targetYear);
        $x(targetXpath).$$(WORKOUT_TITLE_CSS).findBy(text(workoutTitle)).shouldBe(visible);
        return this;
    }

    @Step("Переход на предыдущий месяц")
    public CalendarPage goToPreviousMonth() {
        log.info("Going to previous month");
        $x(LAST_MONTH_BUTTON_XPATH).shouldBe(visible).click();
        return this;
    }

    @Step("Переход на следующий месяц")
    public CalendarPage goToNextMonth() {
        log.info("Going to next month");
        $x(NEXT_MONTH_BUTTON_XPATH).shouldBe(visible).click();
        return this;
    }

    @Step("Проверка, что отображается месяц {expectedMonth}")
    public CalendarPage checkMonthTitle(String expectedMonth) {
        log.info("Checking that month title is '{}'", expectedMonth);
        $(MONTH_HEADER_CSS).shouldHave(text(expectedMonth));
        return this;
    }
}