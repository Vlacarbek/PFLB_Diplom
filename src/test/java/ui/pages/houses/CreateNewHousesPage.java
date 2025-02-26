package ui.pages.houses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.tests.BaseTest;

public class CreateNewHousesPage extends BaseTest {
    private static final By housePage = By.xpath("//a[@href='#' and text()='Houses']");
    private static final By createNewHousePage = By.xpath("//*[@id='basic-navbar-nav']/div/div[3]/div/a[3]");
    private static final By ReadOneByIdPage = By.xpath("//*[@id='basic-navbar-nav']/div/div[3]/div/a[2]");
    private static final By searchIdField = By.xpath("//*[@id='house_input']");
    private static final By searchButton = By.xpath("//*[@id='root']/div/section/div/div/div/button[2]");
    private static final By floorsField = By.xpath("//*[@id='floor_send']");
    private static final By priceField = By.xpath("//*[@id='price_send']");
    private static final By parkingWarmCoveredField = By.xpath("//*[@id='parking_first_send']");
    private static final By parkingWarmField = By.xpath("//*[@id='parking_second_send']");
    private static final By parkingCoveredField = By.xpath("//*[@id='parking_third_send']");
    private static final By parkingColdField = By.xpath("//*[@id='parking_fourth_send']");
    private static final By pushButton = By.xpath("//*[@id='root']/div/section/div/div/button[1]");
    private static final By statusButton = By.xpath("//*[@id='root']/div/section/div/div/button[2]");
    private static final By idField = By.xpath("//*[@id='root']/div/section/div/table[1]/tbody/tr/td[1]");
    private static final By newId = By.xpath("//*[@id='root']/div/section/div/div/button[3]");
    static WebDriver driver;

    public CreateNewHousesPage(WebDriver driver) {
        ui.pages.houses.CreateNewHousesPage.driver = driver;
    }

    @Step("Переход на страницу создания дома")
    public static void openCreateHousesPage() {
        driver.findElement(housePage).click();
        driver.findElement(createNewHousePage).click();
    }

    @Step("Создание нового дома")
    public static void createNewHouse(String floors, String price, String parkWarmCovered, String parkWarm,
                                      String parkCovered, String parkCold) {
        driver.findElement(floorsField).sendKeys(floors);
        driver.findElement(priceField).sendKeys(price);
        driver.findElement(parkingWarmCoveredField).sendKeys(parkWarmCovered);
        driver.findElement(parkingWarmField).sendKeys(parkWarm);
        driver.findElement(parkingCoveredField).sendKeys(parkCovered);
        driver.findElement(parkingColdField).sendKeys(parkCold);
        driver.findElement(pushButton).click();
    }

    @Step("Получение статуса операции по созданию дома")
    public static String getStatus() {
        return driver.findElement(statusButton).getText();
    }

    @Step("Получение статуса операции по поиску дома")
    public static String getIdRead() {
        return driver.findElement(idField).getText();
    }

    @Step ("Нахождение созданого дома по id в общем списке домов")
    public static String findHouseById () {
        String id = driver.findElement(newId).getText();
        driver.findElement(housePage).click();
        driver.findElement(ReadOneByIdPage).click();
        String modifiedId = id.substring(14);
        driver.findElement(searchIdField).sendKeys(modifiedId);
        driver.findElement(searchButton).click();
        return modifiedId;
    }
}
