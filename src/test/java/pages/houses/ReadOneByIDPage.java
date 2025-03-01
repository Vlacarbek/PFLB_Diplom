package pages.houses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReadOneByIDPage extends BaseTest {

    static WebDriver driver;

    public static By readOneByIDPage = By.xpath("//a[@href='#/read/house']");
    public static By sendKeysField = By.xpath("//input[@type ='number']");
    public static By readButton = By.xpath("//button[@class='tableButton btn btn-primary']");
    public static By lodgersID = By.xpath("//*[@id='root']/div/section/div/table[2]/tbody/tr/td");

    public ReadOneByIDPage(WebDriver driver) {
        ReadOneByIDPage.driver = driver;
    }

    @Step("Открытие страницы ReadOneByIDPage")
    public static void openReadOneByIDPage() {
        driver.findElement(ReadAllHousesPage.housePage).click();
        driver.findElement(readOneByIDPage).click();
    }

    @Step("Ввод числа в поле house_input числа: {IDNumber} и нажатие кнопки Read")
    public static void clickReadButton(String IDNumber) {
        driver.findElement(sendKeysField).click();
        driver.findElement(sendKeysField).sendKeys(IDNumber);
        driver.findElement(readButton).click();
    }

    @Step("Получение информации по жильцам на странице ReadOneByIDPage")
    public static List<String> lodgersReadOneByIDPage() {
        List<String> lodgerslist = new ArrayList<>(driver.findElements(lodgersID))
                .stream()
                .map(WebElement::getText)
                .sorted(Comparator.naturalOrder())
                .toList();
        return lodgerslist;
    }
}
