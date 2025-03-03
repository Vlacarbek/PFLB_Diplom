package pages.cars;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.BaseTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class CreateNewCarsPage extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(CreateNewCarsPage.class);
    private static final By ENGINE_TYPE_HEADER = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[2]");
    private static final By MARK_HEADER = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[3]");
    private static final By CAR_ID_HEADER = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[1]");
    private static final By MODEL_HEADER = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[4]");
    private static final By PRICE_HEADER = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[5]");
    private static final By ENGINE_TYPE_FIELD = By.id("car_engine_type_send");
    private static final By MARK_FIELD = By.xpath("//*[@id=\"car_mark_send\"]");
    private static final By MODEL_FIELD = By.xpath("//*[@id=\"car_model_send\"]");
    private static final By PRICE_FIELD = By.xpath("//*[@id=\"car_price_send\"]");
    private static final By CAR_ID_FIELD = By.xpath("//*[@id=\"root\"]/div/section/div/table/tbody/tr/td[1]");
    private static final By CREATE_BUTTON = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[1]");
    private static final By STATUS_FIELD = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[2]");
    private static final By RESULT_FIELD = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[3]");
    private static final Duration TIMEOUT = Duration.ofSeconds(10);
    private static final Duration RETRY_DELAY = Duration.ofMillis(500);
    private static final int MAX_RETRIES = 3;
    public static WebDriver driver;

    public CreateNewCarsPage(WebDriver driver) {
        CreateNewCarsPage.driver = driver;
    }

    @Step("Переход на страницу создания автомобиля")
    public static void openCreateCarsPage() {
        log.info("Открытие страницы с созданием автомобилей");
        driver.get("http://82.142.167.37:4881/#/create/cars");
    }

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

    @Step("Заполнение полей Engine Type {engineType}, Mark {mark},Model {model}, Price {price} на форме создания автомобиля")
    public static boolean createCar(String engineType, String mark, String model, String price) throws InterruptedException {
        log.info("Заполнение формы создания автомобилей");
        sleep(2000);
        boolean engineTypeSuccess = enterTextAndVerify(ENGINE_TYPE_FIELD, engineType);
        boolean markSuccess = enterTextAndVerify(MARK_FIELD, mark);
        boolean modelSuccess = enterTextAndVerify(MODEL_FIELD, model);
        boolean priceSuccess = enterTextAndVerify(PRICE_FIELD, price);
        return engineTypeSuccess && markSuccess && modelSuccess && priceSuccess;
    }

    @Step("Нажатие на кнопку PUSH TO API")
    public static void createCarButtonClick() {
        driver.findElement(CREATE_BUTTON).click();
    }

    @Step("Получение статуса операции по созданию автомобиля")
    public static String getStatus() throws InterruptedException {
        sleep(2000);
        return driver.findElement(STATUS_FIELD).getText();
    }

    public static String getIdNewCar(String text) {
        String regex = ":\\s*(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return null;
        }
    }

    @Step("Проверка корреткного отображения элементов на странице Create New Car")
    public static boolean checkElementsOnPage() {
        By[] elementsToCheck = {ENGINE_TYPE_FIELD, MARK_FIELD, MODEL_FIELD, PRICE_FIELD,
                ENGINE_TYPE_HEADER, MARK_HEADER, MODEL_HEADER, PRICE_HEADER, CREATE_BUTTON,
                CAR_ID_FIELD, CAR_ID_HEADER, STATUS_FIELD, RESULT_FIELD};
        for (By element : elementsToCheck) {
            if (driver.findElements(element).isEmpty()) {
                System.out.println(element);
                return false;
            }
        }
        return true;
    }

    public static String getTextFromResult() throws InterruptedException {
        sleep(2000);
        return driver.findElement(RESULT_FIELD).getText();
    }

    @Step("Получение id созданного автомобиля")
    public static String getCarId() throws InterruptedException {
        log.info("Получение идентфикатора созданного автомобиля");
        sleep(2000);
        String result = CreateNewCarsPage.getIdNewCar(getTextFromResult());
        return result;
    }
}
