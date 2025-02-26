package ui.tests.login;
import io.qameta.allure.*;
import ui.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(testName = "Позитивная авторизация",
            description = "Позитивная авторизация и проверка, что разделы стали доступны")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Login")
    @Story("Login User")
    @TmsLink("www.jira.com/Lg-1")
    public void positiveLogin() {
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertEquals(loginPage.checkAut(), "Status: not pushed");
    }

    @Test(testName = "Негативная авторизация c пустой почтой",
            description = "Негативная авторизация c пустой почтой")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Login")
    @Story("Login User")
    @TmsLink("www.jira.com/Lg-1")
    public void emptyEmail() {
        loginPage.open();
        loginPage.login("  ", "test123");
        Assert.assertTrue(loginPage.checkErrorText("email cannot be empty"));
    }

    @Test(testName = "Негативная авторизация c некорректной почтой",
            description = "Негативная авторизация c некорректной почтой")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Login")
    @Story("Login User")
    @TmsLink("www.jira.com/Lg-1")
    public void invalidEmail() {
        loginPage.open();
        loginPage.login("1111", "test123");
        Assert.assertTrue(loginPage.checkErrorText("incorrect Email"));
    }

    @Test(testName = "Негативная авторизация c некорректным паролем",
            description = "Негативная авторизация c некорректным паролем")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Login")
    @Story("Login User")
    @TmsLink("www.jira.com/Lg-1")
    public void invalidPassword() {
        loginPage.open();
        loginPage.login("test1@test.com", "12");
        Assert.assertTrue(loginPage.checkErrorText("password length must be more than 3 symbols and less than 8 symbols"));
    }
}
