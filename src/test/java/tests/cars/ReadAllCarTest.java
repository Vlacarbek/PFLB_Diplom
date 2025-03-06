package tests.cars;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.List;


public class ReadAllCarTest extends BaseTest {

//    @DataProvider(name = "ColumnNames")
//    public Object[][] columnNames() {
//        return new Object[][]{
//                {"ID"},
//                {"Mark"},
//                {"Model"},
//                {"Price"}
//        };
//    }
//
//    @Test(testName = "Проверка сортировки", dataProvider = "ColumnNames")
//    @Severity(SeverityLevel.CRITICAL)
//    @Epic("PFLB 1.0")
//    @Feature("ReadAllCarTest")
//    @Story("Read All Car Test")
//    @TmsLink("www.jira.com/Car-2")
//    public void sortTest(String columnName) {
//        readAllCarsPage.open();
//        List<String> expectedSortList = readAllCarsPage.getExpectedSortList(columnName);
//        readAllCarsPage.clickButton(columnName);
//        List<String> actualSortList = readAllCarsPage.getActualSortList(columnName);
//        Assert.assertEquals(actualSortList, expectedSortList);
//    }
//
//    @Test(testName = "Проверка атрибутного состава ReadAllCars")
//    @Severity(SeverityLevel.CRITICAL)
//    @Epic("PFLB 1.0")
//    @Feature("ReadAllCarTest")
//    @Story("Read All Car Test")
//    @TmsLink("www.jira.com/Car-2")
//    public void readAllCarsPageAttributeCheck() {
//        readAllCarsPage.open();
//        Assert.assertTrue(readAllCarsPage.checkAttributePresence());
//    }
//
//    @Test(testName = "Проверка работы кнопки Reload")
//    @Severity(SeverityLevel.BLOCKER)
//    @Epic("PFLB 1.0")
//    @Feature("ReadAllCarTest")
//    @Story("Read All Car Test")
//    @TmsLink("www.jira.com/Car-2")
//    public void reloadButtonTest() {
//        readAllCarsPage.open();
//        List<String> startList = readAllCarsPage.getActualSortList("ID");
//
//        readAllCarsPage.clickButton("ID");
//        readAllCarsPage.clickButton("Reload");
//
//        String expectedFirstId = startList.get(0);
//        wait.until(driver ->
//                driver.findElement(By.xpath("//tbody/tr[1]/td[1]"))
//                        .getText().equals(expectedFirstId)
//        );
//
//        List<String> reloadedList = readAllCarsPage.getActualSortList("ID");
//        Assert.assertEquals(reloadedList, startList);
//    }
}
