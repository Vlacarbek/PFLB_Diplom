package tests.cars;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.cars.BuyOrSellCarPage;
import pages.cars.CreateNewCarsPage;
import pages.login.LoginPage;
import pages.users.CreateNewUsersPage;
import tests.BaseTest;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BuyOrSellCarTest extends BaseTest {

//    @Test(testName = "Отоброажение элементов на странице Buy or Sell car",
//            description = "Корректное отображение всех элементов на странице")
//    @Severity(SeverityLevel.NORMAL)
//    @Epic("PFLB 1.0")
//    @Feature("Cars")
//    @Story("Buy or Sell car")
//    @TmsLink("www.jira.com/TK-004")
//    public void checkElementsOnPageCreateNewCar() throws InterruptedException {
//        LoginPage.open();
//        LoginPage.login(user, password);
//        BuyOrSellCarPage.openBuyOrSellPage();
//        assertTrue(BuyOrSellCarPage.checkElementsOnPage());
//    }
//
//    @Test(testName = "Е2Е. Создание пользователя и автомобиля с последующем покупкой автомобиля созданным пользователем.",
//            description = "Проверка сквозного сценария с созданием нового пользователя и нового автомобиля и последующая покупка")
//    @Severity(SeverityLevel.NORMAL)
//    @Epic("PFLB 1.0")
//    @Feature("Cars")
//    @Story("Buy or Sell car")
//    @TmsLink("www.jira.com/TK-005")
//    public void positiveBuyCar() throws InterruptedException {
//        LoginPage.open();
//        LoginPage.login(user, password);
//        CreateNewCarsPage.openCreateCarsPage();
//        CreateNewCarsPage.createCar("Diesel", "KIA", "RIO", "2000");
//        CreateNewCarsPage.createCarButtonClick();
//        String carId = CreateNewCarsPage.getCarId();
//        CreateNewUsersPage.open();
//        CreateNewUsersPage.fillFields("Абрамова", "Анастасия", "25", "25000");
//        CreateNewUsersPage.clickButton();
//        CreateNewUsersPage.getUserId();
//        String userId = CreateNewUsersPage.getUserId();
//        BuyOrSellCarPage.openBuyOrSellPage();
//        BuyOrSellCarPage.buyCar(carId, userId);
//        assertEquals(BuyOrSellCarPage.getStatus(), "Status: Successfully pushed, code: 200");
//    }
}
