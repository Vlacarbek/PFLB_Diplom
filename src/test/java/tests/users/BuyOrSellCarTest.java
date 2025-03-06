package tests.users;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.users.BuyOrSellCarUsersPage;
import tests.BaseTest;
import static org.testng.Assert.assertEquals;


public class BuyOrSellCarTest extends BaseTest {

    @Test(priority = 1, testName = "Успешная покупка машины",
            description = "Успешная покупка машины")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("BuySellCar")
    @Story("BuySell Car")
    @TmsLink("www.jira.com/M-1")
    public static void  positiveBuy() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        BuyOrSellCarUsersPage.open();
        BuyOrSellCarUsersPage.fillFields("2164","6");
        BuyOrSellCarUsersPage.clickBuy();
        BuyOrSellCarUsersPage.clickButton();
        assertEquals(BuyOrSellCarUsersPage.checkResultText(),"Status: Successfully pushed, code: 200");
    }
    @Test(priority = 2,testName = "Успешная продажа машины",
            description = "Успешная продажа машины")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("BuySellCar")
    @Story("BuySell Car")
    @TmsLink("www.jira.com/M-1")
    public static void  positiveSell() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        BuyOrSellCarUsersPage.open();
        BuyOrSellCarUsersPage.fillFields("2164","6");
        BuyOrSellCarUsersPage.clickSell();
        BuyOrSellCarUsersPage.clickButton();
        assertEquals(BuyOrSellCarUsersPage.checkResultText(),"Status: Successfully pushed, code: 200");
    }

    @Test(priority = 3, testName = "Продажа машины , у юзера не хватает средств ",
            description = "Продажа машины , у юзера не хватает средств ")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("BuySellCar")
    @Story("BuySell Car")
    @TmsLink("www.jira.com/M-1")
    public static void  sellWhenUserNoMoney () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        BuyOrSellCarUsersPage.open();
        BuyOrSellCarUsersPage.fillFields("218","6");
        BuyOrSellCarUsersPage.clickBuy();
        BuyOrSellCarUsersPage.clickButton();
        assertEquals(BuyOrSellCarUsersPage.checkResultText(),"Status: AxiosError: Request failed with status code 406");
    }

    @Test(priority = 4, testName = "Продажа машины с невалидными данными ",
            description = "Продажа машины с невалидными данными ")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("BuySellCar")
    @Story("BuySell Car")
    @TmsLink("www.jira.com/M-1")
    public static void  sellWithInvalidDate () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        BuyOrSellCarUsersPage.open();
        BuyOrSellCarUsersPage.fillFields("-1","-1");
        BuyOrSellCarUsersPage.clickSell();
        BuyOrSellCarUsersPage.clickButton();
        assertEquals(BuyOrSellCarUsersPage.checkResultText(),"Status: Incorrect input data");
    }
    @Test(priority = 5, testName = "Продажа машины с пустыми данными ",
            description = "Продажа машины с пустыми данными ")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("BuySellCar")
    @Story("BuySell Car")
    @TmsLink("www.jira.com/M-1")
    public static void  sellWithEmptyDate () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        BuyOrSellCarUsersPage.open();
        BuyOrSellCarUsersPage.fillFields(" "," ");
        BuyOrSellCarUsersPage.clickBuy();
        BuyOrSellCarUsersPage.clickButton();
        System.out.println(BuyOrSellCarUsersPage.checkResultText());
        assertEquals(BuyOrSellCarUsersPage.checkResultText(),"Status: Incorrect input data");
    }
}
