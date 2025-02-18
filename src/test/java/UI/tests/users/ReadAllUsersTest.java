package ui.tests.users;

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
    public void login(String FirstWordOfColumnName) {
        readAllUsersPage.open();
        List expectedSortList = readAllUsersPage.getExpectedSortList(FirstWordOfColumnName);
        readAllUsersPage.clickButton(FirstWordOfColumnName);
        List actualSortList = readAllUsersPage.getActualSortList(FirstWordOfColumnName);
        Assert.assertEquals(actualSortList, expectedSortList);
    }
}
