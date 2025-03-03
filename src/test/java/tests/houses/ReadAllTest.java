package tests.houses;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.houses.ReadAllHousesPage;
import pages.login.LoginPage;
import tests.BaseTest;

import static org.testng.Assert.assertFalse;

public class ReadAllTest extends BaseTest {

    @Test(testName = "Проверка наличия пустых полей на странице ReadAllHousesPage")
    @Description("Проверка наличия пустых полей на странице ReadAllHousesPage")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("House")
    @Story("ReadAll")
    @TmsLink("www.jira.com/TK-001")
    public void checkFieldsIsNotEmpty() {
        LoginPage.open();
        LoginPage.login("user", "password");
        ReadAllHousesPage.openReadAllHousesPage();
        assertFalse(ReadAllHousesPage.fieldsNotNull(), "Имеются пустые поля на странице");
    }

    @Test(testName = "Проверка кнопки Reload на странице ReadAllHousesPage")
    @Description("Проверка кнопки Reload на странице ReadAllHousesPage")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("House")
    @Story("ReadAll")
    @TmsLink("www.jira.com/TK-001")
    public void checkReloadButton() {
        LoginPage.open();
        LoginPage.login("user", "password");
        ReadAllHousesPage.openReadAllHousesPage();
        assertFalse(ReadAllHousesPage.buttonIsWorked());
    }
}
