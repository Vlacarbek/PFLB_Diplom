package ui.tests.login;

import ui.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class
LoginTest extends BaseTest {

    //Позитивная авторизация и проверка, что разделы стали доступны
    @Test
    public void positiveLogin() {
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertEquals(loginPage.checkAut(), "Status: not pushed");
    }

    //Негативная авторизация c пустой почтой
    @Test
    public void emptyEmail()  {
        loginPage.open();
        loginPage.login("  ", "test123");
        Assert.assertTrue(loginPage.checkErrorText("email cannot be empty"));
    }

    //Негативная авторизация c некорректной почтой
    @Test
    public void invalidEmail()  {
        loginPage.open();
        loginPage.login("1111", "test123");
        Assert.assertTrue(loginPage.checkErrorText("incorrect Email"));
    }
    //Негативная авторизация c некорректным паролем
    @Test
    public void invalidPassword()  {
        loginPage.open();
        loginPage.login("test1@test.com", "12");
        Assert.assertTrue(loginPage.checkErrorText("password length must be more than 3 symbols and less than 8 symbols"));
    }
}