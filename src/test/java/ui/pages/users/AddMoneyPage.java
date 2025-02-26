package ui.pages.users;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static java.lang.Thread.sleep;

public class AddMoneyPage {
    static WebDriver driver;
    static By idNameField = By.cssSelector("#id_send");
    static By moneyField = By.cssSelector("#money_send");
    static By pushButton = By.cssSelector(".tableButton.btn.btn-primary");
    static By textResultButton = By.cssSelector(".status.btn.btn-secondary");
    static By moneyResult = By.cssSelector(".money.btn.btn-secondary");

    public AddMoneyPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие странички добавление средств  ")
    public static void open() {
        driver.get("http://82.142.167.37:4881/#/update/users/plusMoney");
    }

    @Step("Заполнение формы для добавление средств ")
    public static void fillFields( String id, String money ) throws InterruptedException {
        sleep(2000);
        driver.findElement(idNameField).sendKeys(id);
        driver.findElement(moneyField).clear();
        driver.findElement(moneyField).sendKeys(String.valueOf(money));

    }
    @Step("Клик на кнопку Push ")
    public static void clickButton() throws InterruptedException {
        sleep(1000);
        driver.findElement(pushButton).click();

    }
    @Step("Проверка текста о результатах ")
    public static String checkResultText() throws InterruptedException {
        sleep(2000);
        return driver.findElement(textResultButton).getText();
    }
    @Step("Проверка суммы в результатах ")
    public static String checkResultMoney() throws InterruptedException {
        sleep(2000);
        return driver.findElement(moneyResult).getText();
    }
}
