package ui.pages.users;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static java.lang.Thread.sleep;

public class CreateNewUsersPage {
    static WebDriver driver;
    static By firstNameField = By.cssSelector("#first_name_send");
    static By lastNameField = By.cssSelector("#last_name_send");
    static By ageField = By.cssSelector("#age_send");
    static By moneyField = By.cssSelector("#money_send");
    static By sexRadioButton = By.id("sex_send");
    static By pushButton = By.cssSelector(".tableButton.btn.btn-primary");
    static By textResultButton = By.cssSelector(".status.btn.btn-secondary");

    public CreateNewUsersPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие странички создания юзера ")
    public static void open() {
        driver.get("http://82.142.167.37:4881/#/create/user");
    }

    @Step("Заполнение формы для создания юзера")
    public static void fillFields(String firstName, String lastName, String age, String money ) throws InterruptedException {
        sleep(1000);
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(ageField).clear();
        driver.findElement(ageField).sendKeys(String.valueOf(age));
        driver.findElement(moneyField).clear();
        driver.findElement(moneyField).sendKeys(String.valueOf(money));
        driver.findElement(sexRadioButton).click();
    }

    @Step("Клик на кнопку Push to api ")
    public static void clickButton() throws InterruptedException {
        sleep(1000);
        driver.findElement(pushButton).click();
    }

    @Step("Проверка текста о результатах ")
    public static String checkResultText() throws InterruptedException {
        sleep(2000);
        return driver.findElement(textResultButton).getText();
    }
}


