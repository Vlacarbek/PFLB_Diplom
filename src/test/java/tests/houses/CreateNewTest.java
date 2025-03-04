package tests.houses;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.houses.CreateNewHousesPage;
import pages.login.LoginPage;
import tests.BaseTest;

public class CreateNewTest extends BaseTest {

    @DataProvider(name = "CreateHouseData")
    public Object[][] createHouseData() {
        return new Object[][]{
                {"1", "2", "3", "4", "5", "6", "Status: Successfully pushed, code: 201"},
                {"9", "008", "1000", "4", "8", "6", "Status: Successfully pushed, code: 201"},
                {"03", "07", "001000", "4000", "80", "06", "Status: Successfully pushed, code: 201"},
        };
    }

    @DataProvider(name = "NegativeHouseData")
    public Object[][] negativeCreateHouseData() {
        return new Object[][]{
                {"0", "0", "0", "0", "0", "0", "Status: Invalid input data"},
                {"", "", "", "", "", "", "Status: Invalid input data"},
                {"-1", "-9", "-0", "-7", "-", "-9", "Status: Invalid input data"},
                {"тест", "1", "2", "3", "4", "5", "Status: Invalid input data"},
                {"1", "test", "2", "3", "4", "5", "Status: Invalid input data"},
                {"1", "2", "@", "!", "?", "6", "Status: Invalid input data"},
        };
    }

    @Test(testName = "Успешное создание нового дома с валидными данными для {floors}, {price}, {parkWarmCovered}, " +
            "{parkWarm}, {parkCovered}, {parkCold}",
            description = "Необходимо проверить возможность успешного создания нового дома на странице Create New",
            dataProvider = "CreateHouseData")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("House")
    @Story("Create New House")
    @TmsLink("www.jira.com/TK-001")
    public void checkPositiveCreateNewHouse(String floors, String price, String parkWarmCovered, String parkWarm, String parkCovered, String parkCold, String status) {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewHousesPage.openCreateHousesPage();
        CreateNewHousesPage.createNewHouse(floors, price, parkWarmCovered, parkWarm, parkCovered, parkCold);
        softAssert.assertEquals(CreateNewHousesPage.getStatus(),
                status,
                "Статус не равен 201");
    }

    @Test(testName = "Невозможность создания дома с невалидными данными для {floors}, {price}, {parkWarmCovered}, " +
            "{parkWarm}, {parkCovered}, {parkCold}",
            description = "Необходимо проверить невозможность создания нового дома с невалидными данными на странице Create New",
            dataProvider = "NegativeHouseData")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("House")
    @Story("Create New House")
    @TmsLink("www.jira.com/TK-002")
    public void checkNegativeCreateNewHouse(String floors, String price, String parkWarmCovered, String parkWarm, String parkCovered, String parkCold, String status) {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewHousesPage.openCreateHousesPage();
        CreateNewHousesPage.createNewHouse(floors, price, parkWarmCovered, parkWarm, parkCovered, parkCold);
        softAssert.assertEquals(CreateNewHousesPage.getStatus(),
                status,
                "В поле Status вернулось некорректное значение");
    }

    @Test(testName = "Успешное отображение созданого дома в общем списке домов с валидными данными для {floors}, " +
            "{price}, {parkWarmCovered}, {parkWarm}, {parkCovered}, {parkCold}",
            description = "Необходимо проверить что новый дом можно найти по id в общем списке домов")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("House")
    @Story("Create New House")
    @TmsLink("www.jira.com/TK-003")
    public void checkCreateNewHouse() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewHousesPage.openCreateHousesPage();
        CreateNewHousesPage.createNewHouse("1", "2", "3", "4", "5", "6");
        Thread.sleep(2000);
        softAssert.assertEquals(CreateNewHousesPage.getStatus(),
                "Status: Successfully pushed, code: 201",
                "В поле Status вернулось некорректное значение");
        String modifiedId = CreateNewHousesPage.findHouseById();
        Thread.sleep(4000);
        softAssert.assertEquals(CreateNewHousesPage.getIdRead(),
                modifiedId,
                "Найден дом с некорректным значением ID");
        softAssert.assertAll();
    }
}



