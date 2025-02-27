package ui.pages.cars;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static ui.pages.users.ReadAllUsersPage.format;

public class ReadAllCarsPage {

    static WebDriver driver;
    static By reloadButton = By.xpath("//button[text()='Reload']");
    static By sortByIdButton = By.xpath("//button[contains(text(), 'ID')]");
    static By sortByEngineTypeButton = By.xpath("(//button)[5]");
    static By sortByMarkButton = By.xpath("//button[contains(text(), 'Mark')]");
    static By sortByModelButton = By.xpath("//button[contains(text(), 'Model')]");
    static By sortByPriceButton = By.xpath("//button[contains(text(), 'Price')]");
    static String buttonPattern = "//button[contains((text()), '%s')]";
    static String listOfEntitiesPattern = "//tbody/tr/td[%s]";


    public ReadAllCarsPage(WebDriver driver) {
        ReadAllCarsPage.driver = driver;
    }

    @Step("Открытие страницы ReadAllСarsPage")
    public static void open() {
        driver.get("http://82.142.167.37:4881/#/read/cars");
    }


    @Step("Нажатие кнопки {buttonName}")
    public static void clickButtonWithWait(String columnName) {
        clickButtonWithRetry(columnName, 5, 2); // По умолчанию 5 попыток, 2 сек задержка
    }

    // Новый метод с retry-логикой
    public static void clickButtonWithRetry(String columnName, int maxRetries, int retryDelayMs) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(2));
        String xpath = String.format("//button[contains(text(), '%s')]", columnName);

        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                wait.until(ExpectedConditions.elementToBeClickable(button)).click();
                return;
            } catch (TimeoutException | ElementClickInterceptedException e) {
                System.out.println("Попытка " + (attempt + 1) + ": Кнопка не кликабельна, ждем " + retryDelayMs + " мс...");
                try {
                    Thread.sleep(retryDelayMs);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Ожидание прервано", ie);
                }
            }
            attempt++;
        }
        throw new RuntimeException("Кнопка '" + columnName + "' не найдена или неактивна после " + maxRetries + " попыток.");
    }



    @Step("Получить ожидаемо отсортированный список значений поля {ButtonName}")
    public static List<String> getExpectedSortList(String sortFieldName) {
        List<String> values = new ArrayList<>(getActualSortList(sortFieldName));

        if (Arrays.asList("ID", "EngineType", "Mark").contains(sortFieldName)) {
            values.sort(Comparator.comparingDouble(value -> {
                try {
                    return Double.parseDouble(value);
                } catch (NumberFormatException e) {
                    return Double.MAX_VALUE; // В случае ошибки парсинга отправляем в конец списка
                }
            }));
        } else {
            values.sort(String::compareTo);
        }

        return values;
    }

    @Step("Получить фактически отсортированный список значений поля {ButtonName}")
    public static List<String> getActualSortList(String sortFieldName) {
        return driver.findElements(By.xpath(String.format(listOfEntitiesPattern, format(sortFieldName)))).stream().map(element -> element.getText().trim()) // Убираем возможные лишние пробелы
                .collect(Collectors.toList());
    }

    //Метод маппит названия столбцов в номера элементов в массивах (для поиска в DOM)
    private static final Map<String, String> COLUMN_MAP = Map.of("ID", "1", "EngineType", "2", "Mark", "3", "Model", "4", "Price", "5");

    public static String format(String buttonName) {
        return COLUMN_MAP.getOrDefault(buttonName, "0");
    }

    @Step("Проверка атрибутного состава")
    public static boolean checkAttribute() {
        List<By> elementsToCheck = List.of(reloadButton, sortByIdButton, sortByEngineTypeButton, sortByMarkButton, sortByModelButton, sortByPriceButton);
        return elementsToCheck.stream().allMatch(element -> {
            boolean isPresent = !driver.findElements(element).isEmpty();
            if (!isPresent) {
                System.out.println("Элемент не найден: " + element);
            }
            return isPresent;
        });
    }

}
