package pages.users;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

@Log4j2
public class ReadUserWithCarsPage {

    private static final Logger log = LoggerFactory.getLogger(ReadUserWithCarsPage.class);
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
        log.info("Открытие странички просмотра пользователей и их машин");
        driver.get("http://82.142.167.37:4881/#/read/userInfo");
    }

    @Step("Поиск пользователей и их машин c {id}")
    public static void fillFields(String id ) throws InterruptedException {
        log.info("Поиск пользователей и их машин c {}", id);
        sleep(1000);
        driver.findElement(ID_FIELD).clear();
        driver.findElement(ID_FIELD).sendKeys(String.valueOf(id));
    }
    @Step("Клик на кнопку Read ")
    public static void clickButton() throws InterruptedException {
        log.info("Клик на кнопку Read");
        sleep(1000);
        driver.findElement(PUSH_BUTTON).click();
    }

    @Step("Проверка текста о результатах ")
    public static String checkResultText() throws InterruptedException {
        log.info("Проверка текста о результатах");
        sleep(3000);
        return driver.findElement(TEXT_RESULT_BUTTON).getText();
    }
    @Step("Проверка количества строк машин ")
    public static int checkCountLineCars() throws InterruptedException {
        log.info("Проверка количества строк машин");
        sleep(3000);
        return driver.findElements(COUNT_LINE_CAR).size();
    }
    @Step("Проверка количества  машин в поле  Cars")
    public static int checkCountCars() throws InterruptedException {
        log.info("Проверка количества  машин в поле  Cars");
        sleep(3000);
        int number = 0;
        try {
             number = Integer.parseInt(driver.findElement(COUNT_CAR).getText());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования: " + e.getMessage());
        }
        return  number;
    }
}
