package ui.pages.users;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReadAllUsersPage {

    WebDriver driver;

    //    By reloadButton = By.xpath("//button[text()='Reload']");
//    By sortByIdButton = By.xpath("//button[contains((text()), 'ID')]");
//    By sortByFirstNameButton = By.xpath("//button[contains((text()), 'First')]");
//    By sortByLastNameButton = By.xpath("//button[contains((text()), 'Last')]");
//    By sortByAgeButton = By.xpath("//button[contains((text()), 'Age')]");
//    By sortBySexButton = By.xpath("//button[contains((text()), 'Sex')]");
//    By sortByMoneyButton = By.xpath("//button[contains((text()), 'Money')]");
    String buttonPattern = "//button[contains((text()), '%s')]";
    String listOfEntitiesPattern = "//tbody/tr/td[%s]";


    public ReadAllUsersPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы ReadAllUsersPage")
    public void open() {
        driver.get("http://82.142.167.37:4881/#/read/users");
    }

    @Step("Нажатие кнопки {buttonName}")
    public void clickButton(String buttonName) {
        driver.findElement(By.xpath(String.format(buttonPattern, buttonName))).click();
    }

    @Step("Получить ожидаемо отсортированный список значений поля {ButtonName}")
    public List getExpectedSortList(String sortFieldName) {
        List<String> values = getActualSortList(sortFieldName);
        if (sortFieldName == "ID" || sortFieldName == "Age" || sortFieldName == "Money") {
            values.sort(Comparator.comparingDouble(Double::parseDouble));
        }
        else Collections.sort(values);
        return values;
    }

    @Step("Получить фактически отсортированный список значений поля {ButtonName}")
    public List getActualSortList(String sortFieldName) {
        String numberOfElement = format(sortFieldName);
        List<WebElement> elements = driver.findElements(By.xpath(String.format(listOfEntitiesPattern, numberOfElement)));
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
            default -> "0"; // Возвращает 0, если символ не является римской цифрой
        };
    }


}

