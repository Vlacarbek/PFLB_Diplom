package tests.houses;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.houses.ReadAllHousesPage;
import pages.houses.ReadOneByIDPage;
import pages.login.LoginPage;
import tests.BaseTest;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ReadOneByIDTest extends BaseTest {

    @Test(testName = "Сверка данных страниц ReadOneByIDPage и ReadAllHousesPage")
    @Description("Сверка данных страниц ReadAllHousesPage" +
            " и ReadOneByIDPage, после поиска по ID на странице ReadOneByIDPage")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("House")
    @Story("ReadOneByID")
    @TmsLink("www.jira.com/TK-001")
    public void checkingTheDataWithTheReadAllPage() {
        LoginPage.open();
        LoginPage.login("user", "password");
        ReadOneByIDPage.openReadOneByIDPage();
        ReadOneByIDPage.clickReadButton("1");
        List<String> lodgersReadOneByIDPageSaved = ReadOneByIDPage.lodgersReadOneByIDPage();
        ReadAllHousesPage.openReadAllHousesPage();
        assertEquals(lodgersReadOneByIDPageSaved,
                ReadAllHousesPage.lodgersReadAllHousePage(),
                "данные не совпадают");
    }
}
