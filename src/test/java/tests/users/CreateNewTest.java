package tests.users;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.users.CreateNewUsersPage;
import tests.BaseTest;

public class

CreateNewTest extends BaseTest {

    @Test(testName = "Корректное создание юзера",
            description = "Позитивная проверка на корректное создание юзера")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("CreateUser")
    @Story("Create User")
    @TmsLink("www.jira.com/Cu-1")
    public static void createUserCorrectData() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewUsersPage.open();
        CreateNewUsersPage.fillFields("Alina", "Vershinina", "19", "19");
        CreateNewUsersPage.clickButton();
        Assert.assertEquals(CreateNewUsersPage.checkResultText(), "Status: Successfully pushed, code: 201");
    }

    @Test(testName = "Cоздание юзера с невалидными значениями",
            description = "Негативная проверка на создание юзера с невалидными значениями")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("CreateUser")
    @Story("Create User")
    @TmsLink("www.jira.com/Cu-1")
    public static void createUserInvalidData() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewUsersPage.open();
        CreateNewUsersPage.fillFields("56", "56", "4444444", "354454353543543354454353543543354454353543543");
        CreateNewUsersPage.clickButton();
        Assert.assertEquals(CreateNewUsersPage.checkResultText(), "Status: AxiosError: Request failed with status code 400");
    }

    @Test(testName = "Cоздание юзера с пустыми значениями",
            description = "Негативная проверка на создание юзера с пустыми значениями")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("CreateUser")
    @Story("Create User")
    @TmsLink("www.jira.com/Cu-1")
    public static void createUserEmptyData() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewUsersPage.open();
        CreateNewUsersPage.clickButton();
        Assert.assertEquals(CreateNewUsersPage.checkResultText(), "Status: Invalid request data");
    }
}
