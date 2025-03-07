package pages.cars;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.BaseTest;

import static java.lang.Thread.sleep;

public class BuyOrSellCarPage extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(BuyOrSellCarPage.class);
    private static final By USER_ID_HEADER = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[1]");
    private static final By USER_ID_FIELD = By.xpath("//*[@id=\"id_send\"]");
    private static final By CAR_ID_HEADER = By.xpath("//*[@id=\"root\"]/div/section/div/table/thead/tr/th[2]");
    private static final By CAR_ID_FIELD = By.xpath("//*[@id=\"car_send\"]");
    private static final By PUSH_BUTTON = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[1]");
    private static final By STATUS_FIELD = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[2]");
    private static final By BUY_CHECK_BOX = By.xpath("/html/body/div/div/section/div/table/tbody/tr/td[3]/div[1]/input");
    private static final By SELL_CHECK_BOX = By.xpath("/html/body/div/div/section/div/table/tbody/tr/td[3]/div[2]/input");
    public static WebDriver driver;

    public BuyOrSellCarPage(WebDriver driver) {
        BuyOrSellCarPage.driver = driver;
    }

    @Step("Переход на страницу Buy or Sell car")
    public static void openBuyOrSellPage() {
        LOG.info("Открытие страницы продажи/покупки автомобиля");
        driver.get("http://82.142.167.37:4881/#/update/users/buyCar");
    }

    @Step("Проверка корреткного отображения элементов на странице Buy or Sell car")
    public static boolean checkElementsOnPage() {
        By[] elementsToCheck = {USER_ID_HEADER, USER_ID_FIELD, CAR_ID_FIELD, CAR_ID_HEADER,
                PUSH_BUTTON, STATUS_FIELD, BUY_CHECK_BOX, SELL_CHECK_BOX};
        for (By element : elementsToCheck) {
            if (driver.findElements(element).isEmpty()) {
                System.out.println(element);
                return false;
            }
        }
        return true;
    }

    @Step("Ввод значений в поля CarId: {carId} и UserId: {userId}")
    public static void buyCar(String carId, String userId) {
        LOG.info("Заполнение полей на форме продажи/покупки автомобиля");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CAR_ID_FIELD)).sendKeys(carId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USER_ID_FIELD)).sendKeys(userId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUY_CHECK_BOX)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(PUSH_BUTTON)).click();
    }

    @Step("Получение статуса операции и сравнение с ожидаемым кодом")
    public static String getStatus() throws InterruptedException {
        sleep(2000);
        return driver.findElement(STATUS_FIELD).getText();
    }
}
