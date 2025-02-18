package API;
import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginTest {

    //Корректная авторизация, возвращает Bearer token
    public static  String  GetToken() throws JsonException {
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
        return token;

    }
}
