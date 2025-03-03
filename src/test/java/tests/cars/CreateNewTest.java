package tests.cars;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.cars.CreateNewCarsPage;
import pages.login.LoginPage;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateNewTest extends BaseTest {

    @DataProvider(name = "CreateCarData")
    public Object[][] createCarData() {
        return new Object[][]{
                //{"Diesel", "KIA", "СИД", "000", "Status: Successfully pushed, code: 201"},
                //{"Diesel", "КИА", "Seed", "9000", "Status: Successfully pushed, code: 201"},
                //{"Gasoline", "Ауди", "A3", "9000", "Status: Successfully pushed, code: 201"},
                //{"Electric", "KIA", "RIO", "1000000", "Status: Successfully pushed, code: 201"},
                //{"Hydrogenic", "BMV", "Модель45", "100000", "Status: Successfully pushed, code: 201"},
                //{"Hydrogenic", "БМВ", "model3", "3456.999", "Status: Successfully pushed, code: 201"},
                //{"PHEV", "KIA", "RIO", "0", "Status: Successfully pushed, code: 201"},
                // {"PHEV", "Шевроле", "Круз", "9000", "Status: Successfully pushed, code: 201"},
                {"CNG", "KIA", "RIO", "20000000", "Status: Successfully pushed, code: 201"},
        };
    }

    @DataProvider(name = "NegativeCreateData")
    public Object[][] negativeCreateData() {
        return new Object[][]{
                //{"NOT_VALID", "KIA", "СИД", "000", "Status: AxiosError: Request failed with status code 400"},
                //{"Diesel", "123456", "Seed", "9000", "Status: AxiosError: Request failed with status code 400"},
                //{"Gasoline", "Ауди", "A3", "-1000", "Status: Invalid request data"},
                //{"", "KIA", "RIO", "1000000", "Status: Invalid request data"},
                //{"Hydrogenic", "", "Модель45", "100000", "Status: Invalid request data"},
                //{"Hydrogenic", "БМВ", "", "3456.999", "Status: Invalid request data"},
                //{"PHEV", "KIA", "RIO", "", "Status: Invalid request data"},
                {"PHEV", "KIA", "RIO", "123456789012345678", "Status: AxiosError: Request failed with status code 500"}
        };
    }

    @Test(testName = "Успешное создание нового автомобиля",
            description = "Успешное создание автомобиля с вводом валидных значений в поля",
            dataProvider = "CreateCarData")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Cars")
    @Story("Create New car")
    @TmsLink("www.jira.com/TK-001")
    public void checkPositiveCreateNewCar(String engineType, String mark, String model, String price, String status) throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewCarsPage.openCreateCarsPage();
        CreateNewCarsPage.createCar(engineType, mark, model, price);
        CreateNewCarsPage.createCarButtonClick();
        assertEquals(CreateNewCarsPage.getStatus(),
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
    public void checkNegativeCreateNewCar(String engineType, String mark, String model, String price, String status) throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewCarsPage.openCreateCarsPage();
        CreateNewCarsPage.createCar(engineType, mark, model, price);
        CreateNewCarsPage.createCarButtonClick();
        assertEquals(CreateNewCarsPage.getStatus(),
                status,
                "В поле Status вернулось некорректное значение");
    }

    @Test(testName = "Отоброажение элементов на странице Create New Car",
            description = "Корректное отображение всех элементов на странице")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Cars")
    @Story("Create New car")
    @TmsLink("www.jira.com/TK-003")
    public void checkElementsOnPageCreateNewCar() {
        LoginPage.open();
        LoginPage.login(user, password);
        CreateNewCarsPage.openCreateCarsPage();
        assertTrue(CreateNewCarsPage.checkElementsOnPage());
    }
}
