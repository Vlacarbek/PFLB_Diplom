package tests.api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class HousesTest {

//    @Test(testName = "Заселение в дом с валидными данными {UserID} и {HouseID}",
//            description = "Позитивная проверка POST запроса")
//    @Severity(SeverityLevel.CRITICAL)
//    @Epic("PFLB 1.0")
//    @Feature("Settle user in house API")
//    @Story("house-controller")
//    @TmsLink("www.jira.com/C-1")
//    public void settleUser() {
//        RestAssured.baseURI = "http://82.142.167.37:4880";
//        Response response = null;
//        try {
//            response = given()
//                    .header("Content-Type", "application/json")
//                    .header("host", "82.142.167.37")
//                    .header("Authorization", "Bearer " + LoginTest.GetToken())
//                    .when()
//                    .post("/house/2/settle/2")
//                    .then()
//                    .extract().response();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Assert.assertEquals(response.getStatusCode(), 200);
//    }
//
//    @Test(testName = "Выселение из дома с валидными данными {UserID} и {HouseID}",
//            description = "Позитивная проверка POST запроса")
//    @Severity(SeverityLevel.CRITICAL)
//    @Epic("PFLB 1.0")
//    @Feature("Evict user out house API")
//    @Story("house-controller")
//    @TmsLink("www.jira.com/C-1")
//    public void evictUser() {
//        RestAssured.baseURI = "http://82.142.167.37:4880";
//        Response response = null;
//        try {
//            response = given()
//                    .header("Content-Type", "application/json")
//                    .header("host", "82.142.167.37")
//                    .header("Authorization", "Bearer " + LoginTest.GetToken())
//                    .when()
//                    .post("/house/2/evict/2")
//                    .then()
//                    .extract().response();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Assert.assertEquals(response.getStatusCode(), 200);
//    }
//
//    @Test(testName = "GET запрос на существующий дом",
//            description = "Позитивная проверка GET запроса с существующим домом")
//    @Severity(SeverityLevel.CRITICAL)
//    @Epic("PFLB 1.0")
//    @Feature("Create house API")
//    @Story("Create house API")
//    @TmsLink("www.jira.com/C-1")
//    public void getHouse() {
//        RestAssured.baseURI = "http://82.142.167.37:4880";
//        Response response = null;
//        try {
//            response = given()
//                    .contentType(ContentType.JSON)
//                    .when()
//                    .get("/house/39");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        response
//                .then()
//                .body("id", equalTo(39))
//                .body("floorCount", equalTo(1))
//                .body("price", equalTo(0.01F));
//        assertEquals(response.getStatusCode(), 200);
//    }
//
//    @Test(testName = "GET запрос на несуществующий дом",
//            description = "Позитивная проверка GET запроса с не существующим домом")
//    @Severity(SeverityLevel.CRITICAL)
//    @Epic("PFLB 1.0")
//    @Feature("Create house API")
//    @Story("Create house API")
//    @TmsLink("www.jira.com/C-1")
//    public void getHouseNonExistent() {
//        RestAssured.baseURI = "http://82.142.167.37:4880";
//        Response response = null;
//        try {
//            response = given()
//                    .contentType(ContentType.JSON)
//                    .when()
//                    .get("/house/5896");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String responseBody = response.getBody().asString();
//        assertEquals(responseBody.length(), 0);
//        assertEquals(response.getStatusCode(), 204);
//    }
}
