package pages.houses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class SettleOrEvictUserPage extends BaseTest {
    private static final By HOUSE_PAGE = By.xpath("//a[@href='#' and text()='Houses']");
    private static final By LIST_ID = By.xpath("//table[@class='tableLodgers table table-striped table-bordered table-hover']/tbody/tr");
    private static final By SETTLE_OR_EVICT_PAGE = By.xpath("//*[@id='basic-navbar-nav']/div/div[3]/div/a[4]");
    private static final By STATUS_ID = By.xpath("//*[@id='root']/div/section/div/div/button[2]");
    private static final By STATUS_SEARCH_ID = By.xpath("//*[@id='root']/div/section/div/div/div/button[3]");
    private static final By READ_ONE_BY_ID_PAGE = By.xpath("//*[@id='basic-navbar-nav']/div/div[3]/div/a[2]");
    private static final By HOUSE_SEARCH_ID_FIELD = By.xpath("//*[@id='house_input']");
    private static final By HOUSE_ID_FIELD = By.xpath("//*[@id='house_send']");
    private static final By USER_ID_FIELD = By.xpath("//*[@id='id_send']");
    private static final By SETTLE_BUTTON = By.xpath("//input[@name='settleOrEvict' and @value='settle']");
    private static final By EVICT_BUTTON = By.xpath("//input[@name='settleOrEvict' and @value='evict']");
    private static final By PUSH_BUTTON = By.xpath("//*[@id='root']/div/section/div/div/button[1]");
    private static final By READ_BUTTON = By.xpath("//*[@id='root']/div/section/div/div/div/button[2]");
    static WebDriver driver;

    public SettleOrEvictUserPage(WebDriver driver) {
        pages.houses.SettleOrEvictUserPage.driver = driver;
    }

    @Step("Переход на страницу Settle or evict user")
    public static void openSettleOrEvictPage() {
        driver.findElement(HOUSE_PAGE).click();
        driver.findElement(SETTLE_OR_EVICT_PAGE).click();
    }

    @Step("Получение статуса операции по заселения или выселения в дома")
    public static String getStatus() {
        return driver.findElement(STATUS_ID).getText();
    }

    @Step("Получение статуса операции по поиску дома")
    public static String getStatusSearch() {
        return driver.findElement(STATUS_SEARCH_ID).getText();
    }

    @Step("Заселение в дом")
    public static void settleInHouse(String UserID, String HouseID) {
        driver.findElement(USER_ID_FIELD).sendKeys(UserID);
        driver.findElement(HOUSE_ID_FIELD).sendKeys(HouseID);
        driver.findElement(SETTLE_BUTTON).click();
        driver.findElement(PUSH_BUTTON).click();
    }

    @Step("Выселение из дома")
    public static void evictOutHouse(String UserID, String HouseID) {
        driver.findElement(USER_ID_FIELD).sendKeys(UserID);
        driver.findElement(HOUSE_ID_FIELD).sendKeys(HouseID);
        driver.findElement(EVICT_BUTTON).click();
        driver.findElement(PUSH_BUTTON).click();
    }

    @Step("Переход на страницу Read one by ID")
    public static void openReadOneByIdPage() {
        driver.findElement(HOUSE_PAGE).click();
        driver.findElement(READ_ONE_BY_ID_PAGE).click();
    }

    @Step("Поиск дома по {HouseID} на странице Read one by ID")
    public static void searchHouseById(String HouseID) {
        driver.findElement(HOUSE_SEARCH_ID_FIELD).sendKeys(HouseID);
        driver.findElement(READ_BUTTON).click();
    }

    @Step("Поиск заселеного {UserID} в таблице дома")
    public static String checkSettleUser(String UserID) {
        List<WebElement> rows = driver.findElements(LIST_ID);
        ArrayList<String> ids = new ArrayList<>();
        for (WebElement row : rows) {
            WebElement idCell = row.findElement(By.xpath("./td[1]"));
            String id = idCell.getText();
            ids.add(id);
        }
        if (ids.contains(UserID)) {
            return "ID найден в списке";
        } else {
            return "ID не найден";
        }
    }
}
