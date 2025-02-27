package pages.login;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class LoginPage {
    private static WebDriver driver;
    private static final  By EMAIL_FIELD = By.xpath("//*[@type =\"email\"]");
    private static final  By PASSWORD_FIELDS = By.xpath("//*[@type =\"text\"]");
    private static final  By LOGIN_BUTTON = By.cssSelector(".Nav-btn.btn.btn-primary");
    private static final  By BUTTON_SECTION_ALL_POST_FOR_CHECK = By.xpath("//*[@data-rr-ui-event-key=\"#/create/all\"]");
    private static final  By BUTTON_STATUS_FOR_CHECK = By.cssSelector(".status.btn.btn-secondary");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие странички авторизации ")
    public static void open() {
        driver.get("http://82.142.167.37:4881/");
    }

    @Step("Вход в систему с логином {user} и паролем {password} ")
    public static void login(String user, String password) {
        driver.findElement(EMAIL_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELDS).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        try {
            Robot robot = new Robot();
            Thread.sleep(2000); // Задержка для ожидания открытия окна
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Step("Проверка авторизации ")
    public static String checkAut() {
        driver.findElement(BUTTON_SECTION_ALL_POST_FOR_CHECK).click();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return driver.findElement(BUTTON_STATUS_FOR_CHECK).getText();
    }

    @Step("Проверка текста ошибки {textError}")
    public static boolean  checkErrorText(String textError)  {
        String pageSource = null;
        try {
            Thread.sleep(2000);
            pageSource = driver.getPageSource();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageSource.contains(textError);
    }
}