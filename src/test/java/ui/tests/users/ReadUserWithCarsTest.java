package ui.tests.users;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.login.LoginPage;
import ui.pages.users.CreateNewUsersPage;
import ui.pages.users.ReadUserWithCarsPage;
import ui.tests.BaseTest;

import static ui.pages.users.ReadUserWithCarsPage.checkResultText;
import static ui.tests.BaseTest.password;
import static ui.tests.BaseTest.user;


public class ReadUserWithCarsTest extends BaseTest
{
    //Позитивная проверка с пользователем с 1 машинами. Количество машин в поле  Cars совпадает с количеством строчек в таблице Cars
    @Test
    public static void readUserWith1Car () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        ReadUserWithCarsPage.open();
        ReadUserWithCarsPage.fillFields("1937");
        Assert.assertEquals(ReadUserWithCarsPage.checkCountLineCars(),ReadUserWithCarsPage.checkCountCars());
        Assert.assertEquals(checkResultText(), "Status: 200 ok");
    }
    //Позитивная проверка с пользователем с 2 машинами. Количество машин в поле  Cars совпадает с количеством строчек в таблице Cars
    @Test
    public static void
    readUserWith2Car () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        ReadUserWithCarsPage.open();
        ReadUserWithCarsPage.fillFields("1930");
        ReadUserWithCarsPage.clickButton();
        Assert.assertEquals(ReadUserWithCarsPage.checkCountLineCars(),ReadUserWithCarsPage.checkCountCars());
        Assert.assertEquals(checkResultText(), "Status: 200 ok");

    }

    //Позитивная проверка с пользователем без машин
    @Test
    public static void readUserWithoutCar () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        ReadUserWithCarsPage.open();
        ReadUserWithCarsPage.fillFields("1934");
        ReadUserWithCarsPage.clickButton();
        Assert.assertEquals(ReadUserWithCarsPage.checkCountLineCars(),0);
        Assert.assertEquals(checkResultText(), "Status: 200 ok");
    }
    //Негативная проверка с несуществующим пользователем
    @Test
    public static void
    readUserNonExist () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        ReadUserWithCarsPage.open();
        ReadUserWithCarsPage.fillFields("77657657");
        ReadUserWithCarsPage.clickButton();
        Assert.assertEquals(checkResultText(), "Status: 204 user not found");
    }

    //Негативная проверка с невалидными данными
    @Test


    public static void readUserInvalidData () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        ReadUserWithCarsPage.open();
        ReadUserWithCarsPage.fillFields("-1");
        ReadUserWithCarsPage.clickButton();
        Assert.assertEquals(checkResultText(), "Status: Invalid input");

    }

}
