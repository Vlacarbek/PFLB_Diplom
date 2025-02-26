package ui.pages.users;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static java.lang.Thread.sleep;

public class ReadUserWithCarsPage {

    private static WebDriver driver;
    private static final By ID_FIELD = By.cssSelector("#user_input");
    private static final By PUSH_BUTTON = By.cssSelector(".tableButton.btn.btn-primary");
    private static final By TEXT_RESULT_BUTTON = By.cssSelector(".status.btn.btn-secondary");
    private static final By COUNT_LINE_CAR = By.cssSelector(".tableCars tbody tr");
    private static final By COUNT_CAR = By.cssSelector("tbody > tr > td:nth-child(7)");

    public ReadUserWithCarsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие странички просмотра пользователей и их машин ")
    public static void open() {
        driver.get("http://82.142.167.37:4881/#/read/userInfo");
    }

    @Step("Поиск пользователей и их машин c {id}")
    public static void fillFields(String id ) throws InterruptedException {
        sleep(1000);
        driver.findElement(ID_FIELD).clear();
        driver.findElement(ID_FIELD).sendKeys(String.valueOf(id));
    }
    @Step("Клик на кнопку Read ")
    public static void clickButton() throws InterruptedException {
        sleep(1000);
        driver.findElement(PUSH_BUTTON).click();
    }

    @Step("Проверка текста о результатах ")
    public static String checkResultText() throws InterruptedException {
        sleep(2000);
        return driver.findElement(TEXT_RESULT_BUTTON).getText();
    }
    @Step("Проверка количества строк машин ")
    public static int checkCountLineCars() throws InterruptedException {
        sleep(2000);
        return driver.findElements(COUNT_LINE_CAR).size();
    }
    @Step("Проверка количества  машин в поле  Cars")
    public static int checkCountCars() throws InterruptedException {
        sleep(2000);
        return Integer.valueOf(driver.findElement(COUNT_CAR).getText());
    }
}
