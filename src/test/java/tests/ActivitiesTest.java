package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Retry;

public class ActivitiesTest extends BaseTest {

    @Test(description = "Activities: positive creation with background color and white text",
            retryAnalyzer = Retry.class)
    public void checkPositiveAddActivities() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        activitiesPage.openPage()
                .setType("Test12")
                .setBackgroundColor("#FFFF00")
                .clickWhiteButton()
                .clickSaveButton()
                .checkActivityCreated("Test12");
    }

    @Test(description = "Activities: positive creation with valid hex background color", retryAnalyzer = Retry.class)
    public void checkInputValidHexCode() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        activitiesPage.openPage()
                .setType("Test_Ilya")
                .setBackgroundColor("#FFFF01")
                .clickSaveButton()
                .checkActivityCreated("Test_Ilya")
                .expandActivity("Test_Ilya")
                .checkActivityLabelColor("Test_Ilya", "#FFFF01");
    }

    @Test(description = "Activities: positive creation with white text color", retryAnalyzer = Retry.class)
    public void checkAddActivitiesWithWhiteText() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        activitiesPage.openPage()
                .setType("test003")
                .clickWhiteButton()
                .clickSaveButton()
                .checkTextColorText("test003", "White");
    }

    @Test(description = "Activities: positive creation with black text color", retryAnalyzer = Retry.class)
    public void checkAddActivitiesWithBlackText() {
        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        activitiesPage.openPage()
                .setType("test3")
                .clickBlackButton()
                .clickSaveButton()
                .checkTextColorText("test3", "Black");
    }

    @DataProvider(name = "activitiesNegativeData")
    public Object[][] activitiesNegativeData() {
        return new Object[][]{
                // type | backgroundColor | expectedError | expectedInvalid
                {null, null, "This field is required.", null},
                {"#", null, null,
                        "The Activity Type Name must contain alphanumeric characters."},
                {"012345678901234567890123456789012345678901234567890123456789012345678901234567890123" +
                        "4567890123456789012345678901234567890123456789012345678901234567890123456789012345678" +
                        "9012345678901234567890123456789012345678901234567890123456789012345678901234567890123" +
                        "4567890123456789012345678901234567890123456789012345678901234567890123456789012345678" +
                        "9012345678901234567890123456789012345678901234567890123456789012345678901234567890123" +
                        "4567890123456789012345678901234567890123456789012345678901234567890123456789012345678" +
                        "90123456789012345678901234567890123456789012345678901234567890123456789012345678901234" +
                        "56789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                        "1234567890123456789012345678901234567890",
                        null, null,
                        "The Activity Type Name cannot be more than 200 characters."},
                {"Run", null, null,
                        "The Activity Type Name you are trying to add already exists."},
                {"test1", "a", null,
                        "Please enter a valid Calendar Background Color."}
        };
    }

    @Test(dataProvider = "activitiesNegativeData",
            description = "Activities: negative cases with invalid type or background color",
            retryAnalyzer = Retry.class)
    public void checkNegativeActivities(String type,
                                        String backgroundColor,
                                        String expectedError,
                                        String expectedInvalid) {

        loginPage.openPage()
                .login(user, password, false)
                .clickLogin();
        activitiesPage.openPage();
        if (type != null) {
            activitiesPage.setType(type);
        }
        if (backgroundColor != null) {
            activitiesPage.setBackgroundColor(backgroundColor);
        }
        activitiesPage.clickSaveButton();
        if (expectedError != null) {
            activitiesPage.checkErrorMessage(expectedError);
        }
        if (expectedInvalid != null) {
            activitiesPage.checkInvalidMessage(expectedInvalid);
        }
    }
}
