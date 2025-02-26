package pages.users;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static java.lang.Thread.sleep;

public class ReadUserWithCarsPage {

    static WebDriver driver;
    static By idField = By.cssSelector("#user_input");
    static By pushButton = By.cssSelector(".tableButton.btn.btn-primary");
    static By textResultButton = By.cssSelector(".status.btn.btn-secondary");
    static By countLineCar = By.cssSelector(".tableCars tbody tr");
    static By countCar = By.cssSelector("tbody > tr > td:nth-child(7)");

    public ReadUserWithCarsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие странички просмотра пользователей и их машин ")
    public static void open() {
        driver.get("http://82.142.167.37:4881/#/read/userInfo");
    }

    @Step("Поиск пользователей и их машин")
    public static void fillFields(String id ) throws InterruptedException {
        sleep(1000);
        driver.findElement(idField).clear();
        driver.findElement(idField).sendKeys(String.valueOf(id));
    }
    @Step("Клик на кнопку Read ")
    public static void clickButton() throws InterruptedException {
        sleep(1000);
        driver.findElement(pushButton).click();
    }

    @Step("Проверка текста о результатах ")
    public static String checkResultText() throws InterruptedException {
        sleep(2000);
        return driver.findElement(textResultButton).getText();
    }
    @Step("Проверка количества строк машин ")
    public static int checkCountLineCars() throws InterruptedException {
        sleep(2000);
        return driver.findElements(countLineCar).size();
    }
    @Step("Проверка количества  машин в поле  Cars")
    public static int checkCountCars() throws InterruptedException {
        sleep(2000);
        return Integer.valueOf(driver.findElement(countCar).getText());
    }
}
