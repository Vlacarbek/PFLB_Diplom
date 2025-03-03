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

    @Test(testName = "Отображение элементов на странице Buy or Sell car",
            description = "Корректное отображение всех элементов на странице")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Cars")
    @Story("Buy or Sell car")
    @TmsLink("www.jira.com/TK-004")
    public void checkElementsOnPageCreateNewCar() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        BuyOrSellCarPage.openBuyOrSellPage();
        assertTrue(BuyOrSellCarPage.checkElementsOnPage());
    }

    @Test(testName = "Е2Е. Создание пользователя и автомобиля с последующей покупкой автомобиля созданным пользователем.",
            description = "Проверка сквозного сценария с созданием нового пользователя и нового автомобиля и последующая покупка")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Cars")
    @Story("Buy or Sell car")
    @TmsLink("www.jira.com/TK-005")
    public void positiveBuyCar() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewCarsPage.openCreateCarsPage();
        CreateNewCarsPage.createCar("Diesel", "KIA", "RIO", "2000");
        CreateNewCarsPage.createCarButtonClick();
        String carId = CreateNewCarsPage.getCarId();
        CreateNewUsersPage.open();
        CreateNewUsersPage.fillFields("Абрамова", "Анастасия", "25", "25000");
        CreateNewUsersPage.clickButton();
        CreateNewUsersPage.getUserId();
        String userId = CreateNewUsersPage.getUserId();
        BuyOrSellCarPage.openBuyOrSellPage();
        BuyOrSellCarPage.buyCar(carId, userId);
        assertEquals(BuyOrSellCarPage.getStatus(), "Status: Successfully pushed, code: 200");
    }

    @Test(testName = "Негатив.Невозможность покупки автомобиля с недостаточным кол-вом средств.",
            description = "Тест проверяет невозможность покупки автомобиля пользователем, у которого не хватает средств.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Cars")
    @Story("Buy or Sell car")
    @TmsLink("www.jira.com/TK-006")
    public void negativeBuyCar() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewCarsPage.openCreateCarsPage();
        CreateNewCarsPage.createCar("Diesel", "KIA", "RIO", "2000");
        CreateNewCarsPage.createCarButtonClick();
        String carId = CreateNewCarsPage.getCarId();
        CreateNewUsersPage.open();
        CreateNewUsersPage.fillFields("Абрамова", "Анастасия", "25", "1000");
        CreateNewUsersPage.clickButton();
        CreateNewUsersPage.getUserId();
        String userId = CreateNewUsersPage.getUserId();
        BuyOrSellCarPage.openBuyOrSellPage();
        BuyOrSellCarPage.buyCar(carId, userId);
        assertEquals(BuyOrSellCarPage.getStatus(), "Status: AxiosError: Request failed with status code 406");
    }

    @Test(testName = "Негатив.Невозможность покупки автомобиля без ввода USER ID.",
            description = "Тест проверяет невозможность покупки автомобиля с пустым полем USER ID.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Cars")
    @Story("Buy or Sell car")
    @TmsLink("www.jira.com/TK-007")
    public void negativeBuyCarWithoutUserId() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        BuyOrSellCarPage.openBuyOrSellPage();
        BuyOrSellCarPage.buyCar("4578", "");
        assertEquals(BuyOrSellCarPage.getStatus(), "Status: Incorrect input data");
    }

    @Test(testName = "Негатив.Невозможность покупки автомобиля без ввода CAR ID.",
            description = "Тест проверяет невозможность покупки автомобиля с пустым полем CAR ID.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Cars")
    @Story("Buy or Sell car")
    @TmsLink("www.jira.com/TK-007")
    public void negativeBuyCarWithoutCarId() throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        BuyOrSellCarPage.openBuyOrSellPage();
        BuyOrSellCarPage.buyCar("", "7846");
        assertEquals(BuyOrSellCarPage.getStatus(), "Status: Incorrect input data");
    }
}
