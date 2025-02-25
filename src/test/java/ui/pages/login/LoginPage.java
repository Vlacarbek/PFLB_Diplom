package ui.pages.login;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;


public class LoginPage {
    public static WebDriver driver;
    static By emailField = By.xpath("//*[@type =\"email\"]");
    static By passwordField = By.xpath("//*[@type =\"text\"]");
    public static By loginButton = By.cssSelector(".Nav-btn.btn.btn-primary");
    public By logoutButton = By.cssSelector(".Nav-btn.btn.btn-danger");
    public By buttonSectionAlLPOSTForCheck = By.xpath("//*[@data-rr-ui-event-key=\"#/create/all\"]");
    public By buttonStatusForCheck = By.cssSelector(".status.btn.btn-secondary");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие странички авторизации ")
    public static void open() {
        driver.get("http://82.142.167.37:4881/");
    }

    @Step("Вход в систему с логином {user} и паролем {password} ")
    public static void login(String user, String password) {
        driver.findElement(emailField).sendKeys(user);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        try {
            Robot robot = new Robot();
            Thread.sleep(2000); // Задержка для ожидания открытия окна
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Step("Пользователь совершает логаут ")
    public void logout() {
        driver.findElement(logoutButton).click();
    }

    @Step("Проверка авторизации ")
    public String checkAut() {
        driver.findElement(buttonSectionAlLPOSTForCheck).click();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String text = driver.findElement(buttonStatusForCheck).getText();
        return text;
    }

    @Step("Проверка текста ошибки")
    public boolean  checkErrorText(String textError)  {
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