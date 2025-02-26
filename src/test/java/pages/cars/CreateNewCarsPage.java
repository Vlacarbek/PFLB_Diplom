package pages.cars;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewCarsPage extends BaseTest {

    private static final By engineTypeHeader = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[2]");
    private static final By markHeader = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[3]");
    private static final By carIdHeader = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[1]");
    private static final By modelHeader = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[4]");
    private static final By priceHeader = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[5]");
    public static final int MAX_RETRIES = 3; // Максимальное количество попыток
    private static final By markField = By.xpath("//*[@id=\"car_mark_send\"]");
    private static final By modelField = By.xpath("//*[@id=\"car_model_send\"]");
    private static final By priceField = By.xpath("//*[@id=\"car_price_send\"]");
    private static final By carIdField = By.xpath("//*[@id=\"root\"]/div/section/div/table/tbody/tr/td[1]");
    private static final By createButton = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[1]");
    private static final By statusField = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[2]");
    private static final By resultField = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[3]");
    private static final By carsPage = By.xpath("//*[@id=\"basic-nav-dropdown\"]");
    private static final By createNewCarsPage = By.xpath("//*[@id=\"basic-navbar-nav\"]/div/div[2]/div/a[2]");
    public static final Duration TIMEOUT = Duration.ofSeconds(10);
    public static final Duration RETRY_DELAY = Duration.ofMillis(500); // Задержка между попытками
    private static final By engineTypeField = By.id("car_engine_type_send");

    private static WebDriver driver;

    public CreateNewCarsPage(WebDriver driver) {
        CreateNewCarsPage.driver = driver;
    }

    @Step("Переход на страницу создания автомобиля")
    public static void openCreateCarsPage() {
        driver.get("http://82.142.167.37:4881/#/create/cars");
    }

    @Step("Ввод значения в поле и проверка, что поле заполнено")
    public static boolean enterTextAndVerify(By locator, String text) {
        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            try {
                WebElement element = waitForElementToBeVisible(locator);
                element.clear();
                element.sendKeys(text);
                String actualValue = element.getAttribute("value");

                if (text.equals(actualValue)) {
                    return true;
                } else {
                    pause(RETRY_DELAY);
                }
            } catch (Exception e) {
                attempts++;
                pause(RETRY_DELAY);
            }
        }

        return false;
    }

    @Step("Ожидание появления элемента на странице")
    public static WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Пауза перед повторной попыткой заполнить поле")
    public static void pause(Duration duration) {
        try {
            TimeUnit.MILLISECONDS.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Заполнение полей на форме создания автомобиля")
    public static boolean createCar(String engineType, String mark, String model, String price) {
        boolean engineTypeSuccess = enterTextAndVerify(engineTypeField, engineType);
        boolean markSuccess = enterTextAndVerify(markField, mark);
        boolean modelSuccess = enterTextAndVerify(modelField, model);
        boolean priceSuccess = enterTextAndVerify(priceField, price);
        return engineTypeSuccess && markSuccess && modelSuccess && priceSuccess;
    }

    @Step("Нажатие на кнопку PUSH TO API")
    public static void createCarButtonClick() {
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