package tests.api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class HousesTest {

    @Test(testName = "Заселение в дом с валидными данными {UserID} и {HouseID}",
            description = "Позитивная проверка POST запроса")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Settle user in house API")
    @Story("house-controller")
    @TmsLink("www.jira.com/C-1")
    public void settleUser() {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = given()
                    .header("Content-Type", "application/json")
                    .header("host", "82.142.167.37")
                    .header("Authorization", "Bearer " + LoginTest.GetToken())
                    .when()
                    .post("/house/2/settle/2")
                    .then()
                    .extract().response();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(testName = "Выселение из дома с валидными данными {UserID} и {HouseID}",
            description = "Позитивная проверка POST запроса")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Evict user out house API")
    @Story("house-controller")
    @TmsLink("www.jira.com/C-1")
    public void evictUser() {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = given()
                    .header("Content-Type", "application/json")
                    .header("host", "82.142.167.37")
                    .header("Authorization", "Bearer " + LoginTest.GetToken())
                    .when()
                    .post("/house/2/evict/2")
                    .then()
                    .extract().response();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
