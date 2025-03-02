package pages.users;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static java.lang.Thread.sleep;

public class BuyOrSellCarUsersPage {
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
        driver.get("http://82.142.167.37:4881/#/update/users/buyCar");
    }

    @Step("Заполнение формы для покупки или продажи машины c {user id} и {car id}")
    public static void fillFields(String  userId, String carId) throws InterruptedException {
        sleep(2000);
        driver.findElement(ID_USER_FIELD).sendKeys(userId);
        driver.findElement(ID_CAR_FIELD).sendKeys(String.valueOf(carId));
    }

    @Step("Клик на кнопку Push ")
    public static void clickButton() throws InterruptedException {
        sleep(1000);
        driver.findElement(PUSH_BUTTON).click();
    }

    @Step("Проверка текста о результатах ")
    public static String checkResultText() throws InterruptedException {
        sleep(2000);
        return driver.findElement(TEXT_RESULT_BUTTON).getText();
    }

    @Step("Клик на радио боттон для покупки")
    public static void clickBuy() throws InterruptedException {
        sleep(2000);
        driver.findElement(RADIO_BUY).click();
    }
    @Step("Клик на радио боттон для продажи")
    public static void clickSell() throws InterruptedException {
        sleep(2000);
        driver.findElement(RADIO_SELL).click();
    }
}
