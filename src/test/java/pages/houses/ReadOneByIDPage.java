package pages.houses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReadOneByIDPage extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(ReadOneByIDPage.class);
    static WebDriver driver;

    private static final By HOUSE_PAGE = By.xpath("//a[text()='Houses']");
    private static final By READ_ONE_BY_ID_PAGE = By.xpath("//a[@href='#/read/house']");
    private static final By SEND_KEYS_FIELD = By.xpath("//input[@type ='number']");
    private static final By READ_BUTTON = By.xpath("//button[@class='tableButton btn btn-primary']");
    private static final By LODGERS_ID = By.xpath("//*[@id='root']/div/section/div/table[2]/tbody/tr/td");

    public ReadOneByIDPage(WebDriver driver) {
        ReadOneByIDPage.driver = driver;
    }

    @Step("Открытие страницы ReadOneByIDPage")
    public static void openReadOneByIDPage() {
        log.info("Opening the ReadOneByIDPage");
        driver.findElement(HOUSE_PAGE).click();
        driver.findElement(READ_ONE_BY_ID_PAGE).click();
    }

    @Step("Ввод в поле house_input числа: {IDNumber} и нажатие кнопки Read")
    public static void clickReadButton(String IDNumber) {
        log.info("Entering the number in the house_input field: {} and clicking the Read button", IDNumber);
        driver.findElement(SEND_KEYS_FIELD).click();
        driver.findElement(SEND_KEYS_FIELD).sendKeys(IDNumber);
        driver.findElement(READ_BUTTON).click();
    }

    @Step("Получение информации по жильцам на странице ReadOneByIDPage")
    public static List<String> lodgersReadOneByIDPage() {
        log.info("Obtaining information on residents on the ReadOneByIDPage page");
        List<String> lodgerslist = new ArrayList<>(driver.findElements(LODGERS_ID))
                .stream()
                .map(WebElement::getText)
                .sorted(Comparator.naturalOrder())
                .toList();
        return lodgerslist;
    }
}
