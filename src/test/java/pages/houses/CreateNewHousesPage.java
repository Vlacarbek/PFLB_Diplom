package pages.houses;

import groovy.util.logging.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.BaseTest;

@Log4j2
public class CreateNewHousesPage {

    private static final By HOUSE_PAGE = By.xpath("//a[@href='#' and text()='Houses']");
    private static final By CREATE_NEW_HOUSE_PAGE = By.xpath("//*[@id='basic-navbar-nav']/div/div[3]/div/a[3]");
    private static final By READ_ONE_BY_ID_PAGE = By.xpath("//*[@id='basic-navbar-nav']/div/div[3]/div/a[2]");
    private static final By SEARCH_ID_FIELD = By.xpath("//*[@id='house_input']");
    private static final By SEARCH_BUTTON = By.xpath("//*[@id='root']/div/section/div/div/div/button[2]");
    private static final By FLOORS_FIELD = By.xpath("//*[@id='floor_send']");
    private static final By PRICE_FIELD = By.xpath("//*[@id='price_send']");
    private static final By PARKING_WARM_COVERED_FIELD = By.xpath("//*[@id='parking_first_send']");
    private static final By PARKING_WARM_FIELD = By.xpath("//*[@id='parking_second_send']");
    private static final By PARKING_COVERED_FIELD = By.xpath("//*[@id='parking_third_send']");
    private static final By PARKING_COLD_FIELD = By.xpath("//*[@id='parking_fourth_send']");
    private static final By PUSH_BUTTON = By.xpath("//*[@id='root']/div/section/div/div/button[1]");
    private static final By STATUS_BUTTON = By.xpath("//*[@id='root']/div/section/div/div/button[2]");
    private static final By ID_FIELD = By.xpath("//*[@id='root']/div/section/div/table[1]/tbody/tr/td[1]");
    private static final By NEW_ID = By.xpath("//*[@id='root']/div/section/div/div/button[3]");
    private static final Logger log = LoggerFactory.getLogger(CreateNewHousesPage.class);
    static WebDriver driver;

    public CreateNewHousesPage(WebDriver driver) {
        pages.houses.CreateNewHousesPage.driver = driver;
    }

    @Step("Переход на страницу создания дома")
    public static void openCreateHousesPage() {
        log.info("Переход на страницу создания дома");
        driver.findElement(HOUSE_PAGE).click();
        driver.findElement(CREATE_NEW_HOUSE_PAGE).click();
    }

    @Step("Создание нового дома с данными '{floors}', '{price}', '{parkWarmCovered}', '{parkWarm}', '{parkCovered}', '{parkCold}'")
    public static void createNewHouse(String floors, String price, String parkWarmCovered, String parkWarm,
                                      String parkCovered, String parkCold) {
        log.info("Создание нового дома с данными '{}', '{}', '{}', '{}','{}', '{}'",
                floors,price,parkWarmCovered,parkWarm,parkCovered,parkCold);
        driver.findElement(FLOORS_FIELD).sendKeys(floors);
        driver.findElement(PRICE_FIELD).sendKeys(price);
        driver.findElement(PARKING_WARM_COVERED_FIELD).sendKeys(parkWarmCovered);
        driver.findElement(PARKING_WARM_FIELD).sendKeys(parkWarm);
        driver.findElement(PARKING_COVERED_FIELD).sendKeys(parkCovered);
        driver.findElement(PARKING_COLD_FIELD).sendKeys(parkCold);
        driver.findElement(PUSH_BUTTON).click();
    }

    @Step("Получение статуса операции по созданию дома")
    public static String getStatus() {
        log.info("Получение статуса операции по созданию дома");
        return driver.findElement(STATUS_BUTTON).getText();
    }

    @Step("Получение статуса операции по поиску дома")
    public static String getIdRead() {
        log.info("Получение статуса операции по поиску дома");
        return driver.findElement(ID_FIELD).getText();
    }

    @Step("Нахождение созданого дома по id в общем списке домов")
    public static String findHouseById() {
        log.info("Нахождение созданого дома по id в общем списке домов");
        String id = driver.findElement(NEW_ID).getText();
        driver.findElement(HOUSE_PAGE).click();
        driver.findElement(READ_ONE_BY_ID_PAGE).click();
        String modifiedId = id.substring(14);
        driver.findElement(SEARCH_ID_FIELD).sendKeys(modifiedId);
        driver.findElement(SEARCH_BUTTON).click();
        return modifiedId;
    }
}
