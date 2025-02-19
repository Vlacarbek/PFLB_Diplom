package ui.tests.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.tests.BaseTest;
import java.util.List;

public class ReadAllUsersTest extends BaseTest {

    @DataProvider(name = "FirstWordOfColumnName")
    public Object[][] FirstWordOfColumnName() {
        return new Object[][]{
                {"ID"},
                {"First"},
                {"Last"},
                {"Age"},
                {"Sex"},
                {"Money"},
        };
    }

    @Test(testName = "Проверка сортировки", dataProvider = "FirstWordOfColumnName")
    public void sortTest(String FirstWordOfColumnName) {
        readAllUsersPage.open();
        List expectedSortList = readAllUsersPage.getExpectedSortList(FirstWordOfColumnName);
        readAllUsersPage.clickButton(FirstWordOfColumnName);
        List actualSortList = readAllUsersPage.getActualSortList(FirstWordOfColumnName);
        Assert.assertEquals(actualSortList, expectedSortList);
    }

    @Test(testName = "Проверка атрибутного состава ReadAllUsers")
    public void readAllUsersPageAttributeCheck() {
        readAllUsersPage.open();
        Assert.assertTrue(readAllUsersPage.checkAttribute());
    }

    @Test(testName = "Проверка работы кнопки Reload")
    public void reloadButtonTest() {
        readAllUsersPage.open();
        List startList = readAllUsersPage.getActualSortList("ID");
        readAllUsersPage.clickButton("ID");
        readAllUsersPage.clickButton("Reload");
        //Явное ожидание пока пока отработает Reload
        String expectedValue = (String) startList.get(0); //Получаем первый id изначальной таблицы
        wait.until(driver -> {
            WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")); //Получаем первый элемент id таблицы
            return element.getText().equals(expectedValue); //Сравниваем первый id таблицы с первым id  изначальной таблицы
        });
        List reloadedList = readAllUsersPage.getActualSortList("ID");
        Assert.assertEquals(reloadedList, startList);
    }

}
