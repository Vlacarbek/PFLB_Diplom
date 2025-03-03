package tests.users;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.users.ReadUserWithCarsPage;
import tests.BaseTest;

import java.sql.SQLException;

import static org.testng.AssertJUnit.assertEquals;
import static pages.users.ReadUserWithCarsPage.checkResultText;
import static tests.db.UsersTest.checkUserCount;

public class ReadUserWithCarsTest extends BaseTest {

    @Test(testName = "Позитивная проверка с пользователем с 1 машинами",
            description = "Позитивная проверка с пользователем с 1 машинами. Количество машин в поле  Cars совпадает с количеством строчек в таблице Cars")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("ReadUserWithCars")
    @Story("Read User With Cars")
    @TmsLink("www.jira.com/Car-1")
    public static void readUserWith1Car () throws InterruptedException, SQLException {
        LoginPage.open();
        LoginPage.login(user, password);
        ReadUserWithCarsPage.open();
        ReadUserWithCarsPage.fillFields("2164");
        ReadUserWithCarsPage.clickButton();
        assertEquals(ReadUserWithCarsPage.checkCountLineCars(),ReadUserWithCarsPage.checkCountCars());
        assertEquals(ReadUserWithCarsPage.checkCountCars(), checkUserCount("select * from  ( select * from person  join\n" +
                "car  ON person.id = car.person_id ) AS FinalTable\n" +
                "where person_id = 2164;"));
        assertEquals(checkResultText(), "Status: 200 ok");
    }
    @Test(testName = "Позитивная проверка с пользователем с 2 машинами",
            description = "Позитивная проверка с пользователем с 2 машинами. Количество машин в поле  Cars совпадает с количеством строчек в таблице Cars")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("ReadUserWithCars")
    @Story("Read User With Cars")
    @TmsLink("www.jira.com/Car-1")
    public static void readUserWith2Car () throws InterruptedException, SQLException {
        LoginPage.open();
        LoginPage.login(user, password);
        ReadUserWithCarsPage.open();
        ReadUserWithCarsPage.fillFields("2744");
        ReadUserWithCarsPage.clickButton();
        assertEquals(ReadUserWithCarsPage.checkCountLineCars(),ReadUserWithCarsPage.checkCountCars());
        assertEquals(ReadUserWithCarsPage.checkCountCars(), checkUserCount("select * from  ( select * from person  join\n" +
                "car  ON person.id = car.person_id ) AS FinalTable\n" +
                "where person_id = 2744;"));
        assertEquals(checkResultText(), "Status: 200 ok");
    }

    @Test(testName = "Позитивная проверка с пользователем без машин",
            description = "Позитивная проверка с пользователем без машин")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("ReadUserWithCars")
    @Story("Read User With Cars")
    @TmsLink("www.jira.com/Car-1")
    public static void readUserWithoutCar () throws InterruptedException, SQLException {
        LoginPage.open();
        LoginPage.login(user, password);
        ReadUserWithCarsPage.open();
        ReadUserWithCarsPage.fillFields("2739");
        ReadUserWithCarsPage.clickButton();
        assertEquals(ReadUserWithCarsPage.checkCountLineCars(), 0);
        assertEquals(ReadUserWithCarsPage.checkCountLineCars(), checkUserCount("select * from  ( select * from person  join\n" +
              "car  ON person.id = car.person_id ) AS FinalTable\n" + "where person_id = 2739;"));
        assertEquals(checkResultText(), "Status: 200 ok");
    }

    @Test(testName = "Негативная проверка с несуществующим пользователем",
            description = "Негативная проверка с несуществующим пользователем")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("ReadUserWithCars")
    @Story("Read User With Cars")
    @TmsLink("www.jira.com/Car-1")
    public static void readUserNonExist () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        ReadUserWithCarsPage.open();
        ReadUserWithCarsPage.fillFields("77657657");
        ReadUserWithCarsPage.clickButton();
        assertEquals(checkResultText(), "Status: 204 user not found");
    }

    @Test(testName = "Негативная проверка с невалидными данными",
            description = "Негативная проверка с невалидными данными")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("ReadUserWithCars")
    @Story("Read User With Cars")
    @TmsLink("www.jira.com/Car-1")
    public static void readUserInvalidData () throws InterruptedException {
        LoginPage.open();
        LoginPage.login(user, password);
        ReadUserWithCarsPage.open();
        ReadUserWithCarsPage.fillFields("-1");
        ReadUserWithCarsPage.clickButton();
        assertEquals(checkResultText(), "Status: Invalid input");
    }
}
