package pages.houses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ReadAllHousesPage extends BaseTest {

    static WebDriver driver;

    private static final By HOUSE_PAGE = By.xpath("//a[text()='Houses']");
    private static final By READ_ALL = By.xpath("//a[@href='#/read/houses']");
    private static final By RELOAD_BUTTON = By.xpath("//button[text()='Reload']");
    private static final By LODGERS_ID_READ_ALL = By.xpath("//*[@id='root']/div/section/div/table/tbody/tr[1]/td[5]/table/tbody/tr/td");

    public ReadAllHousesPage(WebDriver driver) {
        ReadAllHousesPage.driver = driver;
    }

    @Step("Открытие страницы ReadAllHousesPage")
    public static void openReadAllHousesPage() {
        driver.findElement(HOUSE_PAGE).click();
        driver.findElement(READ_ALL).click();
    }

    @Step("Поиск пустых полей на странице ReadAllHousesPage")
    public static Boolean fieldsNotNull() {
        ArrayList<WebElement> allField = new ArrayList<>(driver.findElements(By.xpath("//td")));
        List<String> stringField = new ArrayList<>();
        for (WebElement field : allField) {
            stringField.add(field.getText());
        }
        return stringField.stream().anyMatch(Objects::isNull);
    }

    @Step("Проверка кнопки Reload на странице ReadAllHousesPage: меняется ли сортировка")
    public static Boolean buttonIsWorked() {
        ArrayList<WebElement> allFieldFirstEqual = new ArrayList<>(driver.findElements(By.xpath("//td")));
        List<String> stringFieldFirst = new ArrayList<>();
        for (WebElement field : allFieldFirstEqual) {
            stringFieldFirst.add(field.getText());
        }
        driver.findElement(RELOAD_BUTTON).click();
        ArrayList<WebElement> allFieldSecondEqual = new ArrayList<>(driver.findElements(By.xpath("//td")));
        List<String> stringFieldSecond = new ArrayList<>();
        for (WebElement field : allFieldSecondEqual) {
            stringFieldSecond.add(field.getText());
        }
        System.out.println(stringFieldFirst.equals(stringFieldSecond));
        return stringFieldFirst.equals(stringFieldSecond);
    }

    @Step("Получение информации по жильцам на странице ReadAllHousesPage")
    public static List<String> lodgersReadAllHousePage() {
        List<String> lodgerslistReadAllPage = new ArrayList<>(driver.findElements(LODGERS_ID_READ_ALL))
                .stream()
                .map(WebElement::getText)
                .sorted(Comparator.naturalOrder())
                .toList();
        return lodgerslistReadAllPage;
    }
}

