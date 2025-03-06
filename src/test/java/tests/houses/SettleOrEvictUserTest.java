package tests.houses;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.houses.SettleOrEvictUserPage;
import pages.login.LoginPage;
import tests.BaseTest;


public class SettleOrEvictUserTest extends BaseTest {

//    @DataProvider(name = "PositiveIdData")
//    public Object[][] PositiveIdData() {
//        return new Object[][]{
//                {"1", "2"},
//                {"9", "008"},
//                {"03", "07"},
//        };
//    }
//
//    @DataProvider(name = "NegativeIdData")
//    public Object[][] NegativeIdData() {
//        return new Object[][]{
//                {"0", "0"},
//                {"", ""},
//                {"1", "test"},
//                {"{test", "1"},
//                {"-1", "-9"},
//                {"тест", "test"},
//                {"@", "!"},
//        };
//    }
//
//    @Test(testName = "Успешное заселение в дом с валдиными {UserID} и {HouseID}",
//            description = "Необходимо проверить возможность заселения в дом",
//            dataProvider = "PositiveIdData")
//    @Severity(SeverityLevel.NORMAL)
//    @Epic("PFLB 1.0")
//    @Feature("House")
//    @Story("Settle or evict")
//    @TmsLink("www.jira.com/TK-001")
//    public void checkPositiveSettleInHouse(String UserID, String HouseID) {
//        LoginPage.open();
//        LoginPage.login(user, password);
//        SettleOrEvictUserPage.openSettleOrEvictPage();
//        SettleOrEvictUserPage.settleInHouse(UserID, HouseID);
//        softAssert.assertEquals(SettleOrEvictUserPage.getStatus(),
//                "Status: Successfully pushed, code: 200",
//                "Статус не равен 200");
//    }
//
//    @Test(testName = "Заселение в дом с невалидными данными {UserID} и {HouseID}",
//            description = "Необходимо проверить возможность заселения в дом с невалидными данными",
//            dataProvider = "NegativeIdData")
//    @Severity(SeverityLevel.NORMAL)
//    @Epic("PFLB 1.0")
//    @Feature("House")
//    @Story("Settle or evict")
//    @TmsLink("www.jira.com/TK-002")
//    public void checkNegativeSettleInHouse(String UserID, String HouseID) {
//        LoginPage.open();
//        LoginPage.login(user, password);
//        SettleOrEvictUserPage.openSettleOrEvictPage();
//        SettleOrEvictUserPage.settleInHouse(UserID, HouseID);
//        softAssert.assertEquals(SettleOrEvictUserPage.getStatus(),
//                "Status: Invalid input data",
//                "Статус не равен Invalid input data");
//    }
//
//    @Test(testName = "Успешное выселение из дома с валдиными {UserID} и {HouseID}",
//            description = "Необходимо проверить возможность выселения из дома",
//            dataProvider = "PositiveIdData")
//    @Severity(SeverityLevel.NORMAL)
//    @Epic("PFLB 1.0")
//    @Feature("House")
//    @Story("Settle or evict")
//    @TmsLink("www.jira.com/TK-003")
//    public void checkPositiveEvictOutHouse(String UserID, String HouseID) {
//        LoginPage.open();
//        LoginPage.login(user, password);
//        SettleOrEvictUserPage.openSettleOrEvictPage();
//        SettleOrEvictUserPage.evictOutHouse(UserID, HouseID);
//        softAssert.assertEquals(SettleOrEvictUserPage.getStatus(),
//                "Status: Successfully pushed, code: 200",
//                "Статус не равен 200");
//    }
//
//    @Test(testName = "Выселение из дома с невалидными данными {UserID} и {HouseID}",
//            description = "Необходимо проверить возможность выселения из дома с невалидными данными",
//            dataProvider = "NegativeIdData")
//    @Severity(SeverityLevel.NORMAL)
//    @Epic("PFLB 1.0")
//    @Feature("House")
//    @Story("Settle or evict")
//    @TmsLink("www.jira.com/TK-004")
//    public void checkNegativeEvictOutHouse(String UserID, String HouseID) {
//        LoginPage.open();
//        LoginPage.login(user, password);
//        SettleOrEvictUserPage.openSettleOrEvictPage();
//        SettleOrEvictUserPage.evictOutHouse(UserID, HouseID);
//        softAssert.assertEquals(SettleOrEvictUserPage.getStatus(),
//                "Status: Invalid input data",
//                "Статус не равен Invalid input data");
//    }
//
//    @Test(testName = "Заселение {UserID} в {HouseID} с проверкой в общей таблицей {HouseID}",
//            description = "Необходимо проверить в {HouseID} успешно ли заселение")
//    @Severity(SeverityLevel.NORMAL)
//    @Epic("PFLB 1.0")
//    @Feature("House")
//    @Story("Settle or evict")
//    @TmsLink("www.jira.com/TK-005")
//    public void check() throws InterruptedException {
//        String userId = "79";
//        String houseId = "1";
//        LoginPage.open();
//        LoginPage.login(user, password);
//        SettleOrEvictUserPage.openSettleOrEvictPage();
//        SettleOrEvictUserPage.settleInHouse(userId, houseId);
//        Thread.sleep(2000);
//        softAssert.assertEquals(SettleOrEvictUserPage.getStatus(),
//                "Status: Successfully pushed, code: 200",
//                "Статус не равен 200");
//        SettleOrEvictUserPage.openReadOneByIdPage();
//        SettleOrEvictUserPage.searchHouseById(houseId);
//        softAssert.assertEquals(SettleOrEvictUserPage.getStatusSearch(),
//                "Status: 200 ok",
//                "Статус не равен 200");
//        softAssert.assertEquals(SettleOrEvictUserPage.checkSettleUser(userId),
//                "ID найден в списке",
//                "ID не найден");
//        softAssert.assertAll();
//    }
}
