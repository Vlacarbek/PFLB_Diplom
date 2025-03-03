package pages.users;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReadAllUsersPage {

    WebDriver driver;

    private static final By RELOAD_BUTTON = By.xpath("//button[text()='Reload']");
    private static final By SORT_BY_ID_BUTTON = By.xpath("//button[contains((text()), 'ID')]");
    private static final By SORT_BY_FIRST_NAME_BUTTON = By.xpath("//button[contains((text()), 'First')]");
    private static final By SORT_BY_LAST_NAME_BUTTON = By.xpath("//button[contains((text()), 'Last')]");
    private static final By SORT_BY_AGE_BUTTON = By.xpath("//button[contains((text()), 'Age')]");
    private static final By SORT_BY_SEX_BUTTON = By.xpath("//button[contains((text()), 'Sex')]");
    private static final By SORT_BY_MONEY_BUTTON = By.xpath("//button[contains((text()), 'Money')]");
    private static final By ID_COLUMN_HEADER = By.xpath("//tr/th[text()=' ID:']");
    private static final By FIRST_NAME_COLUMN_HEADER = By.xpath("//tr/th[text()=' First\u00A0name:']");
    private static final By LAST_NAME_COLUMN_HEADER = By.xpath("//tr/th[text()=' Last\u00A0name:']");
    private static final By AGE_COLUMN_HEADER = By.xpath("//tr/th[text()=' Age:']");
    private static final By SEX_COLUMN_HEADER = By.xpath("//tr/th[text()=' Sex:']");
    private static final By MONEY_COLUMN_HEADER = By.xpath("//tr/th[text()=' Money:']");
    private static final By TABLE = By.xpath("//tbody");
    private static final String BUTTON_PATTERN = "//button[contains((text()), '%s')]";
    private static final String LIST_OF_ENTITIES_PATTERN = "//tbody/tr/td[%s]";


    public ReadAllUsersPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы ReadAllUsersPage")
    public void open() {
        driver.get("http://82.142.167.37:4881/#/read/users");
    }

    @Step("Нажатие кнопки {buttonName}")
    public void clickButton(String buttonName) {
        driver.findElement(By.xpath(String.format(BUTTON_PATTERN, buttonName))).click();
    }

    @Step("Получить ожидаемо отсортированный список значений поля {ButtonName}")
    public List getExpectedSortList(String sortFieldName) {
        List<String> values = getActualSortList(sortFieldName);
        if (sortFieldName == "ID" || sortFieldName == "Age" || sortFieldName == "Money") {
            values.sort(Comparator.comparingDouble(Double::parseDouble));
        } else Collections.sort(values);
        return values;
    }

    @Step("Получить фактически отсортированный список значений поля {ButtonName}")
    public List getActualSortList(String sortFieldName) {
        String numberOfElement = format(sortFieldName);
        List<WebElement> elements = driver.findElements(By.xpath(String.format(LIST_OF_ENTITIES_PATTERN, numberOfElement)));
        List<String> values = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        return values;
    }

    //Метод маппит названия столбцов в номера элементов в массивах (для поиска в DOM)
    public static String format(String buttonName) {
        return switch (buttonName) {
            case "ID" -> "1";
            case "First" -> "2";
            case "Last" -> "3";
            case "Age" -> "4";
            case "Sex" -> "5";
            case "Money" -> "6";
            default -> "0";
        };
    }

    @Step("Проверка атрибутного состава")
    public boolean checkAttribute() {
        By[] elementsToCheck = {RELOAD_BUTTON, SORT_BY_ID_BUTTON, SORT_BY_FIRST_NAME_BUTTON, SORT_BY_LAST_NAME_BUTTON,
                SORT_BY_AGE_BUTTON, SORT_BY_SEX_BUTTON, SORT_BY_MONEY_BUTTON, ID_COLUMN_HEADER, FIRST_NAME_COLUMN_HEADER,
                LAST_NAME_COLUMN_HEADER, AGE_COLUMN_HEADER, SEX_COLUMN_HEADER, MONEY_COLUMN_HEADER, TABLE};
        for (By element : elementsToCheck) {
            if (driver.findElements(element).isEmpty()) {
                System.out.println(element);
                return false; // Если хотя бы один элемент отсутствует, возвращаем false
            }
        }
        return true; // Все элементы присутствуют
    }

}

