package ui.tests.cars;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.pages.cars.CreateNewCarsPage;
import ui.pages.login.LoginPage;
import ui.tests.BaseTest;


public class CreateNewTest extends BaseTest {

    @DataProvider(name = "CreateCarData")
    public Object[][] createCarData() {
        return new Object[][]{
                {"Diesel", "KIA", "СИД", "000", "Status: Successfully pushed, code: 201"},
                {"Diesel", "КИА", "Seed", "9000", "Status: Successfully pushed, code: 201"},
                {"Gasoline", "Ауди", "A3", "9000", "Status: Successfully pushed, code: 201"},
                {"Electric", "KIA", "RIO", "1000000", "Status: Successfully pushed, code: 201"},
                {"Hydrogenic", "BMV", "Модель45", "100000", "Status: Successfully pushed, code: 201"},
                {"Hydrogenic", "БМВ", "model3", "3456.999", "Status: Successfully pushed, code: 201"},
                {"PHEV", "KIA", "RIO", "0", "Status: Successfully pushed, code: 201"},
                {"PHEV", "Шевроле", "Круз", "9000", "Status: Successfully pushed, code: 201"},
                {"CNG", "KIA", "RIO", "20000000", "Status: Successfully pushed, code: 201"},
        };
    }

    @DataProvider(name = "NegativeCreateData")
    public Object[][] negativeCreateData() {
        return new Object[][]{
                {"NOT_VALID", "KIA", "СИД", "000", "Status: AxiosError: Request failed with status code 400"},
                {"Diesel", "123456", "Seed", "9000", "Status: AxiosError: Request failed with status code 400"},
                {"Gasoline", "Ауди", "A3", "-1000", "Status: Invalid request data"},
                {"", "KIA", "RIO", "1000000", "Status: Invalid request data"},
                {"Hydrogenic", "", "Модель45", "100000", "Status: Invalid request data"},
                {"Hydrogenic", "БМВ", "", "3456.999", "Status: Invalid request data"},
                {"PHEV", "KIA", "RIO", "", "Status: Invalid request data"},
                {"PHEV", "KIA", "RIO", "123456789012345678", "Status: AxiosError: Request failed with status code 500"}
        };
    }

    @Test(testName = "Успешное создание нового автомобиля",
            description = "Учспешное создание автомобиля с вводом валидных значений в поля",
            dataProvider = "CreateCarData")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Cars")
    @Story("Create New car")
    @TmsLink("www.jira.com/TK-001")
    public void checkPositiveCreateNewCar(String engineType, String mark, String model, String price, String status) {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewCarsPage.openCreateCarsPage();
        CreateNewCarsPage.createCar(engineType, mark, model, price);
        CreateNewCarsPage.createCarButtonClick();
        softAssert.assertEquals(CreateNewCarsPage.getStatus(),
                status,
                "Статус не равен 201");
    }

    @Test(testName = "Невозможность создания автомобиля с невалидными данными",
            description = "Невозможность создания автомобиля  при вводе невалидных значений",
            dataProvider = "NegativeCreateData")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Cars")
    @Story("Create New car")
    @TmsLink("www.jira.com/TK-002")
    public void checkNegativeCreateNewCar(String engineType, String mark, String model, String price, String status) {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewCarsPage.openCreateCarsPage();
        CreateNewCarsPage.createCar(engineType, mark, model, price);
        CreateNewCarsPage.createCarButtonClick();
        softAssert.assertEquals(CreateNewCarsPage.getStatus(),
                status,
                "В поле Status вернулось некорректное значение");
        System.out.println(status);

    }

//    @Test
//    public void getId() {
//
//        String result = CreateNewCarsPage.getIdNewCar("New car ID: 408");
//        System.out.println(result);
//    }
}
