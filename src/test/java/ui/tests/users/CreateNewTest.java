package ui.tests.users;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.login.LoginPage;
import ui.pages.users.CreateNewUsersPage;
import ui.tests.BaseTest;


public class

CreateNewTest extends BaseTest {

    // Позитивная проверка на корректное создание юзера
    @Test
    public static void createUserCorrectData () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewUsersPage.open();
        CreateNewUsersPage.fillFields("Alina","Vershinina","19", "19");
        CreateNewUsersPage.clickButton();
        Assert.assertEquals(CreateNewUsersPage.checkResultText(),"Status: Successfully pushed, code: 201");

    }
    // Негативная проверка на создание юзера с пустыми значениями
    @Test
    public static void createUserInvalidData () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewUsersPage.open();
        CreateNewUsersPage.fillFields("56","56","4444444", "354454353543543354454353543543354454353543543");
        CreateNewUsersPage.clickButton();
        Assert.assertEquals(CreateNewUsersPage.checkResultText(),"Status: AxiosError: Request failed with status code 400");
    }
    //Негативная проверка на создание юзера с невалидными значениями
    @Test
    public static void createUserEmptyData () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewUsersPage.open();
        CreateNewUsersPage.clickButton();
        Assert.assertEquals(CreateNewUsersPage.checkResultText(),"Status: Invalid request data");
    }


}
