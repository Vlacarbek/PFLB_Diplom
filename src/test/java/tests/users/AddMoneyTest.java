package tests.users;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.users.AddMoneyPage;
import tests.BaseTest;
import static org.testng.Assert.assertEquals;

public class AddMoneyTest extends BaseTest {

//    @Test(testName = "Позитивное отправление средств",
//            description = "Позитивный тест корректное отправление средств")
//    @Severity(SeverityLevel.CRITICAL)
//    @Epic("PFLB 1.0")
//    @Feature("Money")
//    @Story("Add Money")
//    @TmsLink("www.jira.com/M-1")
//    public static void addMoneyCorrectDate() throws InterruptedException {
//        LoginPage.open();
//        LoginPage.login(user, password);
//        AddMoneyPage.open();
//        AddMoneyPage.fillFields("11", "1");
//        AddMoneyPage.clickButton();
//        String BeforeAddMoneyResult = AddMoneyPage.checkResultMoney();
//        AddMoneyPage.clickButton();
//        String AfterAddMoneyResult = AddMoneyPage.checkResultMoney();
//        assertEquals(Integer.parseInt(AfterAddMoneyResult) - Integer.parseInt(BeforeAddMoneyResult), 1);
//        assertEquals(AddMoneyPage.checkResultText(), "Status: Successfully pushed, code: 200");
//    }
//
//    @Test(testName = "Отправление средств несуществующему пользователю",
//            description = "Негативный тест отправление средств несуществующему пользователю")
//    @Severity(SeverityLevel.NORMAL)
//    @Epic("PFLB 1.0")
//    @Feature("Money")
//    @Story("Add Money")
//    @TmsLink("www.jira.com/M-1")
//    public static void addMoneyNonExistUser() throws InterruptedException {
//        LoginPage.open();
//        LoginPage.login(user, password);
//        AddMoneyPage.open();
//        AddMoneyPage.fillFields("54454355434", "1");
//        AddMoneyPage.clickButton();
//        assertEquals(AddMoneyPage.checkResultText(), "Status: AxiosError: Request failed with status code 404");
//    }
//
//    @Test(testName = "Отправление средств с некорректными данными",
//            description = "Негативный тест отправление средств с некорректными данными")
//    @Severity(SeverityLevel.NORMAL)
//    @Epic("PFLB 1.0")
//    @Feature("Money")
//    @Story("Add Money")
//    @TmsLink("www.jira.com/M-1")
//    public static void addMoneyInvalidDate() throws InterruptedException {
//        LoginPage.open();
//        LoginPage.login(user, password);
//        AddMoneyPage.open();
//        AddMoneyPage.fillFields("1", "-1");
//        AddMoneyPage.clickButton();
//        assertEquals(AddMoneyPage.checkResultText(), "Status: Incorrect input data");
//    }
//
//    @Test(testName = "Отправление средств с пустыми данными",
//            description = "Негативный тест отправление средств с пустыми данными")
//    @Severity(SeverityLevel.NORMAL)
//    @Epic("PFLB 1.0")
//    @Feature("Money")
//    @Story("Add Money")
//    @TmsLink("www.jira.com/M-1")
//    public static void addMoneyEmptyDate() throws InterruptedException {
//        LoginPage.open();
//        LoginPage.login(user, password);
//        AddMoneyPage.open();
//        AddMoneyPage.fillFields("", "");
//        AddMoneyPage.clickButton();
//        assertEquals(AddMoneyPage.checkResultText(), "Status: Incorrect input data");
//    }
}