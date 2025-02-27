package ui.tests.cars;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.pages.cars.ReadAllCarsPage;
import ui.tests.BaseTest;

import java.util.List;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class ReadAllTest extends BaseTest {

    @DataProvider(name = "SortColumnNames")
    public Object[][] provideSortColumnNames() {
        return Stream.of("ID", "Engine Type", "Mark", "Model", "Price")
                .map(column -> new Object[]{column})
                .toArray(Object[][]::new);
    }

    @Test(testName = "Проверка сортировки", dataProvider = "SortColumnNames")
    public void sortTest(String columnName) {
        ui.pages.cars.ReadAllCarsPage.open();
        List expectedSortList = ui.pages.cars.ReadAllCarsPage.getExpectedSortList(columnName);
        ui.pages.cars.ReadAllCarsPage.clickButtonWithWait(columnName);
        List actualSortList = ui.pages.cars.ReadAllCarsPage.getActualSortList(columnName);
        assertArrayEquals(actualSortList, expectedSortList);
    }

    private void assertArrayEquals(List actualSortList, List expectedSortList) {
    }
    @Test(testName = "Проверка атрибутного состава ReadAllCarsPage")
    public void readAllCarsPageAttributeCheck() {
        ui.pages.cars.ReadAllCarsPage.open();
        assertTrue(ui.pages.cars.ReadAllCarsPage.checkAttribute());
    }

    @Test(testName = "Проверка работы кнопки Reload")
    public void reloadButtonTest() {
        ui.pages.cars.ReadAllCarsPage.open();
        List startList = ui.pages.cars.ReadAllCarsPage.getActualSortList("ID");
        ui.pages.cars.ReadAllCarsPage.clickButtonWithWait("ID");
        ui.pages.cars.ReadAllCarsPage.clickButtonWithWait("Reload");

        String expectedValue = (String) startList.get(0); // Получаем первый ID изначальной таблицы
        wait.until(driver -> {
            WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")); // Получаем первый элемент ID таблицы
            return element.getText().equals(expectedValue); // Сравниваем первый ID таблицы с первым ID изначальной таблицы
        });

        List reloadedList = ui.pages.cars.ReadAllCarsPage.getActualSortList("ID");
        assertEquals(reloadedList, startList);
    }
}
