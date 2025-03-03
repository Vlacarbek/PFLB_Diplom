package tests.login;
import io.qameta.allure.*;
import pages.login.LoginPage;
import tests.BaseTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test(testName = "Позитивная авторизация",
            description = "Позитивная авторизация и проверка, что разделы стали доступны")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Login")
    @Story("Login User")
    @TmsLink("www.jira.com/Lg-1")
    public void positiveLogin() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        assertEquals(LoginPage.checkAut(), "Status: not pushed");
    }

    @Test(testName = "Негативная авторизация c пустой почтой",
            description = "Негативная авторизация c пустой почтой")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Login")
    @Story("Login User")
    @TmsLink("www.jira.com/Lg-1")
    public void emptyEmail() throws InterruptedException {
        LoginPage.open();
        LoginPage.login("  ", "test123");
        assertTrue(LoginPage.checkErrorText("email cannot be empty"));
    }

    @Test(testName = "Негативная авторизация c некорректной почтой",
            description = "Негативная авторизация c некорректной почтой")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Login")
    @Story("Login User")
    @TmsLink("www.jira.com/Lg-1")
    public void invalidEmail() throws InterruptedException {
        LoginPage.open();
        LoginPage.login("1111", "test123");
        assertTrue(LoginPage.checkErrorText("incorrect Email"));
    }

    @Test(testName = "Негативная авторизация c некорректным паролем",
            description = "Негативная авторизация c некорректным паролем")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Login")
    @Story("Login User")
    @TmsLink("www.jira.com/Lg-1")
    public void invalidPassword() throws InterruptedException {
        LoginPage.open();
        LoginPage.login("test1@test.com", "12");
        assertTrue(LoginPage.checkErrorText("password length must be more than 3 symbols and less than 8 symbols"));
    }
}
