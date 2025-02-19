package api;
import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    static String requestBody;
    static Response response;
    static String token;

    //Корректная авторизация, возвращает Bearer tokens
    public static String GetToken() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4879";
         requestBody = "{"
                + "\"username\": \"user@pflb.ru\","
                + "\"password\": \"user\""
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

    //Проверяем, что авторизация возвращает токен и он равен 203 символам
    @Test
    public static  void   CorrectLogin() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4879";
        String requestBody = "{"
                + "\"username\": \"user@pflb.ru\","
                + "\"password\": \"user\""
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
        //Проверяем авторизацию с пустыми данными
        @Test
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

    //Проверяем авторизацию с не валидными данными
    @Test
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

