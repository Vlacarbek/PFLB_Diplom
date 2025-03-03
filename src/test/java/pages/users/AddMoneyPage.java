package pages.users;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

@Log4j2
public class AddMoneyPage {
    private static final Logger log = LoggerFactory.getLogger(AddMoneyPage.class);
    private static WebDriver driver;
    private static final By ID_NAME_FIELD = By.cssSelector("#id_send");
    private static final By MONEY_FIELD = By.cssSelector("#money_send");
    private static final By PUSH_BUTTON = By.cssSelector(".tableButton.btn.btn-primary");
    private static final By TEXT_RESULT_BUTTON = By.cssSelector(".status.btn.btn-secondary");
    private static final By MONEY_RESULT = By.cssSelector(".money.btn.btn-secondary");

    public AddMoneyPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие странички добавление средств ")
    public static void open() {
        log.info("Открытие странички добавление средств ");
        driver.get("http://82.142.167.37:4881/#/update/users/plusMoney");
    }

    @Step("Заполнение формы для добавление средств c {id} на сумму {money}")
    public static void fillFields(String id, String money) throws InterruptedException {
        log.info("Заполнение формы для добавление средств c {} на сумму {}", id,money);
        sleep(2000);
        driver.findElement(ID_NAME_FIELD).sendKeys(id);
        driver.findElement(MONEY_FIELD).clear();
        driver.findElement(MONEY_FIELD).sendKeys(String.valueOf(money));
    }

    @Step("Клик на кнопку Push ")
    public static void clickButton() throws InterruptedException {
        log.info("Клик на кнопку Push ");
        sleep(1000);
        driver.findElement(PUSH_BUTTON).click();
    }

    @Step("Проверка текста о результатах ")
    public static String checkResultText() throws InterruptedException {
        log.info("Проверка текста о результатах ");
        sleep(2000);
        return driver.findElement(TEXT_RESULT_BUTTON).getText();
    }

    @Step("Проверка суммы в результатах ")
    public static String checkResultMoney() throws InterruptedException {
        log.info("Проверка суммы в результатах");
        sleep(2000);
        return driver.findElement(MONEY_RESULT).getText();
    }
}
