package tests.api;
import groovy.json.JsonException;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.PropertyReader;
import static org.testng.Assert.assertEquals;

public class LoginTest {
    private static  String REQUEST_BODY;
    private static  Response RESPONSE;
    private static  String TOKEN;
    private static  String  USER = System.getProperty("user", PropertyReader.getProperty("user"));
    private static  String PASSWORD = System.getProperty("password", PropertyReader.getProperty("password"));

    //Корректная авторизация, возвращает Bearer tokens
    public static String GetToken() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        REQUEST_BODY = "{"
                + "\"username\" : \"" + USER + "\","
                + "\"password\": \"" + PASSWORD + "\""
                + "}";
        RESPONSE = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .body(REQUEST_BODY)
                .when()
                .post("/login")
                .then()
                .extract().response();
        TOKEN = RESPONSE.jsonPath().getString("access_token");
        return TOKEN;
    }

    @Test(testName = "Авторизация возвращает токен и он равен 203 символам",
            description = "Проверяем, что авторизация возвращает токен и он равен 203 символам")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Login  API")
    @Story("Login API")
    @TmsLink("www.jira.com/C-1")
    public static  void   CorrectLogin() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4879";
        String requestBody = "{"
                + "\"username\" : \"" + USER + "\","
                + "\"password\": \"" + PASSWORD + "\""
                + "}";
        Response  response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract().response();
        String  token = response.jsonPath().getString("access_token");
        assertEquals(token.length(), 203);
    }

    @Test(testName = "Авторизация с пустыми данными",
            description = "Проверяем авторизацию с пустыми данными")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Login  API")
    @Story("Login API")
    @TmsLink("www.jira.com/C-1")
    public  void  EmptyLogin () throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4879";
        String requestBody = "{"
                + "\"username\": ,"
                + "\"password\": "
                + "}";
        RESPONSE = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract().response();
        assertEquals(RESPONSE.getStatusCode(),403);
    }

    @Test(testName = "Авторизация с не валидными данными",
            description = "Проверяем авторизацию с не валидными данными")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Login  API")
    @Story("Login API")
    @TmsLink("www.jira.com/C-1")
    public  void  InvalidLogin () throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4879";
        String requestBody = "{"
                + "\"username\": 323213,"
                + "\"password\": 323213"
                + "}";
        RESPONSE = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract().response();
        assertEquals(RESPONSE.getStatusCode(),403);
    }
}