package ui.pages.cars;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.tests.BaseTest;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateNewCarsPage extends BaseTest {

    private static final By engineTypeHeader = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[2]");
    private static final By markHeader = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[3]");
    private static final By carIdHeader = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[1]");
    private static final By modelHeader = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[4]");
    private static final By priceHeader = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[5]");
    private static final By engineTypeField = By.xpath("//*[@id=\"car_engine_type_send\"]");
    private static final By markField = By.xpath("//*[@id=\"car_mark_send\"]");
    private static final By modelField = By.xpath("//*[@id=\"car_model_send\"]");
    private static final By priceField = By.xpath("//*[@id=\"car_price_send\"]");
    private static final By carIdField = By.xpath("//*[@id=\"root\"]/div/section/div/table/tbody/tr/td[1]");
    private static final By createButton = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[1]");
    private static final By statusField = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[2]");
    private static final By resultField = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[3]");
    private static final By carsPage = By.xpath("//*[@id=\"basic-nav-dropdown\"]");
    private static final By createNewCarsPage = By.xpath("//*[@id=\"basic-navbar-nav\"]/div/div[2]/div/a[2]");


    public static WebDriver driver;


    public CreateNewCarsPage(WebDriver driver) {
        CreateNewCarsPage.driver = driver;
    }

    @Step("Переход на страницу создания автомобиля")
    public static void openCreateCarsPage() {
        driver.findElement(carsPage).click();
        driver.findElement(createNewCarsPage).click();
    }

    @Step("Создание нового автомобиля Cars")
    public static void createNewCar(String engineType, String mark, String model, String price) {
        driver.findElement(engineTypeField).sendKeys(engineType);
        driver.findElement(markField).sendKeys(mark);
        driver.findElement(modelField).sendKeys(model);
        driver.findElement(priceField).sendKeys(price);
        driver.findElement(createButton).click();
    }

    @Step("Получение статуса операции по созданию автомобиля")
    public static String getStatus() {
        return driver.findElement(statusField).getText();
    }

    @Step("Получение идентификатора созданного автомобиля")
    public  static String getIdNewCar(String text) {
        String regex = ":\\s*(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return null;
        }
    }

    @Step("Проверка наличия на странице заголовка элемента Engine Type")
    public static boolean engineTypeHeaderIsDisplayed() {
        try {
            return driver.findElement(engineTypeHeader).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице заголовка элемента Car ID")
    public static boolean carIdHeaderIsDisplayed() {
        try {
            return driver.findElement(carIdHeader).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице заголовка элемента Mark")
    public static boolean markHeaderIsDisplayed() {
        try {
            return driver.findElement(markHeader).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице заголовка элемента Model")
    public static boolean modelHeaderIsDisplayed() {
        try {
            return driver.findElement(modelHeader).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице заголовка элемента Price")
    public static boolean priceHeaderIsDisplayed() {
        try {
            return driver.findElement(priceHeader).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице поля для ввода значения Engine Type")
    public static boolean engineTypeFieldIsDisplayed() {
        try {
            return driver.findElement(engineTypeField).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице поля Car ID")
    public static boolean carIdFieldIsDisplayed() {
        try {
            return driver.findElement(carIdField).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице поля для ввода значения Mark")
    public static boolean markFieldIsDisplayed() {
        try {
            return driver.findElement(markField).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице поля для ввода значения Model")
    public static boolean modelFieldIsDisplayed() {
        try {
            return driver.findElement(modelField).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице поля для ввода значения Price")
    public static boolean priceFieldIsDisplayed() {
        try {
            return driver.findElement(priceField).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице кнопки CREATE")
    public static boolean createButtonIsDisplayed() {
        try {
            return driver.findElement(createButton).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице поля STATUS")
    public static boolean statusFieldIsDisplayed() {
        try {
            return driver.findElement(statusField).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка наличия на странице поля RESULT")
    public static boolean resultFieldIsDisplayed() {
        try {
            return driver.findElement(resultField).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Нажатие кнопки CREATE")
    public static void createClick() {
        driver.findElement(createButton).click();
    }

    @Step("Получение текст из поля CarId")
    public static String getTextCarId() {
        return driver.findElement(carIdField).getText();
    }



}