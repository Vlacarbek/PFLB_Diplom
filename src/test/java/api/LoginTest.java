package api;
import groovy.json.JsonException;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class LoginTest {
    static String requestBody;
    static Response response;
    static String token;
    public static String user = System.getProperty("user", PropertyReader.getProperty("user"));
    public static String password = System.getProperty("password", PropertyReader.getProperty("password"));

    //Корректная авторизация, возвращает Bearer tokens
    public static String GetToken() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        requestBody = "{"
                + "\"username\" : \"" + user + "\","
                + "\"password\": \"" + password + "\""
                + "}";
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract().response();
        token = response.jsonPath().getString("access_token");
        return token;
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
                + "\"username\" : \"" + user + "\","
                + "\"password\": \"" + password + "\""
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
        Assert.assertEquals(token.length(), 203);
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
        String requestBody2 = "{"
                + "\"username\": ,"
                + "\"password\": "
                + "}";
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .body(requestBody2)
                .when()
                .post("/login")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),403);
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
        String requestBody2 = "{"
                + "\"username\": 323213,"
                + "\"password\": 323213"
                + "}";
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .body(requestBody2)
                .when()
                .post("/login")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),403);

    }
}

