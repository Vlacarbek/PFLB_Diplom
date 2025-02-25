package ui.tests.users;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.login.LoginPage;
import ui.pages.users.AddMoneyPage;
import ui.pages.users.CreateNewUsersPage;
import ui.tests.BaseTest;

public class AddMoneyTest  extends BaseTest {

    //Позитивный тест корректное отправление средств
    @Test
    public static void addMoneyCorrectDate () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        AddMoneyPage.open();
        AddMoneyPage.fillFields("11","1");
        AddMoneyPage.clickButton();
        String BeforeAddMoneyResult = AddMoneyPage.checkResultMoney();
        AddMoneyPage.clickButton();
        String AfterAddMoneyResult = AddMoneyPage.checkResultMoney();
        Assert.assertEquals( Integer.parseInt(AfterAddMoneyResult) - Integer.parseInt(BeforeAddMoneyResult)  ,1);
        Assert.assertEquals(AddMoneyPage.checkResultText(),"Status: Successfully pushed, code: 200");

    }
    //Негативный тест отправление средств несуществующему пользователю
    @Test
    public static void addMoneyNonExistUser () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        AddMoneyPage.open();
        AddMoneyPage.fillFields("54454355434","1");
        AddMoneyPage.clickButton();
        Assert.assertEquals(AddMoneyPage.checkResultText(),"Status: AxiosError: Request failed with status code 404");
    }
    //Негативный тест отправление средств с некорректными данными
    @Test
    public static void addMoneyInvalidDate () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        AddMoneyPage.open();
        AddMoneyPage.fillFields("1","-1");
        AddMoneyPage.clickButton();
        Assert.assertEquals(AddMoneyPage.checkResultText(),"Status: Incorrect input data");

    }
    //Негативный тест отправление средств с пустыми данными
    @Test
    public static void addMoneyEmptyDate () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        AddMoneyPage.open();
        AddMoneyPage.fillFields("","");
        AddMoneyPage.clickButton();
        Assert.assertEquals(AddMoneyPage.checkResultText(),"Status: Incorrect input data");
    }

}