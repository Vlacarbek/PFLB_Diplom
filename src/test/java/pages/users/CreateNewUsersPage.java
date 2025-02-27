package pages.users;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.cars.CreateNewCarsPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class CreateNewUsersPage {

    private static WebDriver driver;
    private static final  By FIRST_NAME_FIELD = By.cssSelector("#first_name_send");
    private static final  By LAST_NAME_FIELD = By.cssSelector("#last_name_send");
    private static final  By AGE_FIELD = By.cssSelector("#age_send");
    private static final  By MONEY_FIELD = By.cssSelector("#money_send");
    private static final  By SEX_RADIO_BUTTON = By.id("sex_send");
    private static final  By PUSH_BUTTON = By.cssSelector(".tableButton.btn.btn-primary");
    private static final  By TEXT_RESULT_BUTTON = By.cssSelector(".status.btn.btn-secondary");


    public CreateNewUsersPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие странички создания юзера ")
    public static void open() {
        driver.get("http://82.142.167.37:4881/#/create/user");
    }

    @Step("Заполнение формы для создания юзера c данными {firstName},{lastName},{age},{money} ")
    public static void fillFields(String firstName, String lastName, String age, String money ) throws InterruptedException {
        sleep(1000);
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(AGE_FIELD).clear();
        driver.findElement(AGE_FIELD).sendKeys(String.valueOf(age));
        driver.findElement(MONEY_FIELD).clear();
        driver.findElement(MONEY_FIELD).sendKeys(String.valueOf(money));
        driver.findElement(SEX_RADIO_BUTTON).click();
    }

    @Step("Клик на кнопку Push to api ")
    public static void clickButton() throws InterruptedException {
        sleep(1000);
        driver.findElement(PUSH_BUTTON).click();
    }

    @Step("Проверка текста о результатах ")
    public static String checkResultText() throws InterruptedException {
        sleep(2000);
        return driver.findElement(TEXT_RESULT_BUTTON).getText();
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
