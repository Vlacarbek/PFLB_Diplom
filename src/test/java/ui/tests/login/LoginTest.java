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
}



