package pages.cars;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class ReadAllCarsPage {
    private static WebDriver driver;
    private static final By RELOAD_BUTTON = By.xpath("//button[text()='Reload']");
    private static final By ID_BUTTON = By.xpath("//button[contains(text(), 'ID')]");
    private static final By MARK_BUTTON = By.xpath("//button[contains(text(), 'Mark')]");
    private static final By MODEL_BUTTON = By.xpath("//button[contains(text(), 'Model')]");
    private static final By PRICE_BUTTON = By.xpath("//button[contains(text(), 'Price')]");
    private static final By TABLE = By.xpath("//tbody");
    private static final String BUTTON_PATTERN = "//button[contains((text()), '%s')]";

    public ReadAllCarsPage(WebDriver driver) {
        ReadAllCarsPage.driver = driver;
    }

    @Step("Открытие страницы ReadAllСarsPage")
    public static void open() {
        driver.get("http://82.142.167.37:4881/#/read/cars");
    }

    @Step("Нажатие кнопки {buttonName}")
    public void clickButton(String buttonName) {
        driver.findElement(By.xpath(String.format(BUTTON_PATTERN, buttonName))).click();
    }
    @Step("Получить ожидаемо отсортированный список значений поля {sortFieldName}")
    public List<String> getExpectedSortList(String sortFieldName) {
        List<String> values = getActualSortList(sortFieldName);

        if (sortFieldName.equals("ID") || sortFieldName.equals("Price")) {
            values.sort(Comparator.comparingDouble(Double::parseDouble));
        } else {
            Collections.sort(values);
        }
        return values;
    }
    @Step("Получить фактически отсортированный список значений поля {sortFieldName}")
    public List<String> getActualSortList(String sortFieldName) {
        String columnIndex = format(sortFieldName);
        List<WebElement> elements = driver.findElements(
                By.xpath(String.format("//tbody/tr/td[%s]", columnIndex))
        );
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private static String format(String fieldName) {
        return switch (fieldName) {
            case "ID" -> "1";
            case "Mark" -> "3";
            case "Model" -> "4";
            case "Price" -> "5";
            default -> throw new IllegalArgumentException("Unknown field: " + fieldName);
        };
    }

    @Step("Проверка атрибутного состава")
    public boolean checkAttributePresence() {
        By[] elementsToCheck = {
                RELOAD_BUTTON,
                ID_BUTTON,
                MARK_BUTTON,
                MODEL_BUTTON,
                PRICE_BUTTON,
                TABLE
        };
        for (By element : elementsToCheck) {
            List<WebElement> elements = driver.findElements(element);
            if (elements.isEmpty()) {
                System.out.println("Элемент не найден: " + element);
                return false;
            }
        }
        return true;
    }
}
