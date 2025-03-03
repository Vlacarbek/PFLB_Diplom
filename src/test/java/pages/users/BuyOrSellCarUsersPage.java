package pages.users;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

@Log4j2
public class BuyOrSellCarUsersPage {
    private static final Logger log = LoggerFactory.getLogger(BuyOrSellCarUsersPage.class);
    private static WebDriver driver;
    private static final By ID_USER_FIELD = By.cssSelector("#id_send");
    private static final By ID_CAR_FIELD = By.cssSelector("#car_send");
    private static final By PUSH_BUTTON = By.cssSelector(".tableButton.btn.btn-primary");
    private static final By TEXT_RESULT_BUTTON = By.cssSelector(".status.btn.btn-secondary");
    private static final By  RADIO_BUY = By.cssSelector("[value='buyCar']");
    private static final By  RADIO_SELL = By.cssSelector("[value='sellCar']");

    public BuyOrSellCarUsersPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открытие странички для  покупки или продажи  ")
    public static void open() {
        log.info("Открытие странички для  покупки или продажи");
        driver.get("http://82.142.167.37:4881/#/update/users/buyCar");
    }

    @Step("Заполнение формы для покупки или продажи машины c {user id} и {car id}")
    public static void fillFields(String  userId, String carId) throws InterruptedException {
        log.info("Заполнение формы для покупки или продажи машины c {} и {}",userId ,carId);
        sleep(2000);
        driver.findElement(ID_USER_FIELD).sendKeys(userId);
        driver.findElement(ID_CAR_FIELD).sendKeys(String.valueOf(carId));
    }

    @Step("Клик на кнопку Push ")
    public static void clickButton() throws InterruptedException {
        log.info("Клик на кнопку Push");
        sleep(1000);
        driver.findElement(PUSH_BUTTON).click();
    }

    @Step("Проверка текста о результатах ")
    public static String checkResultText() throws InterruptedException {
        log.info("Проверка текста о результатах");
        sleep(2000);
        return driver.findElement(TEXT_RESULT_BUTTON).getText();
    }

    @Step("Клик на радио боттон для покупки")
    public static void clickBuy() throws InterruptedException {
        log.info("Клик на радио боттон для покупки");
        sleep(2000);
        driver.findElement(RADIO_BUY).click();
    }
    @Step("Клик на радио боттон для продажи")
    public static void clickSell() throws InterruptedException {
        log.info("Клик на радио боттон для продажи");
        sleep(2000);
        driver.findElement(RADIO_SELL).click();
    }
}
