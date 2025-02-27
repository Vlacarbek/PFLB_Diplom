package pages.users;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.cars.CreateNewCarsPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static By userId = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[3]");

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

    @Step("Клик на кнопку Push to tests.api ")
    public static void clickButton() throws InterruptedException {
        sleep(1000);
        driver.findElement(pushButton).click();
    }

    @Step("Проверка текста о результатах ")
    public static String checkResultText() throws InterruptedException {
        sleep(2000);
        return driver.findElement(textResultButton).getText();
    }

    @Step("Получение id созданного пользователя")
    public static String getTextFromResult() throws InterruptedException {
        sleep(2000);
        return driver.findElement(userId).getText();
    }

    @Step("Получение идентификатора созданного пользователя из текста результата")
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

    public static String getUserId() throws InterruptedException {
        String result = CreateNewUsersPage.getIdNewCar(getTextFromResult());
        return result;
    }
}
