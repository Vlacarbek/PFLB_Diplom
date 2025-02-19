package api;

import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class UsersTest  {

    //Проверка GET запроса с корректными данными
    @Test
    public void GetUser() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get("/user/2271")
            ;}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String responseBody = response.getBody().asString();
        response.then()
                .body("id", equalTo(2271))
                .body("firstName", equalTo("Alina7"))
                .body("secondName", equalTo("Vershinina"))
                .body("age", equalTo(25))
                .body("sex", equalTo("MALE"))
                .body("money", equalTo(350.00F));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    //Проверка POST запроса с корректными данными
    @Test
    public void PostUser() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4879";
        String requestBody = "{"
                + "\"firstName\": \"Alina7\","
                + "\"sex\": \"MALE\","
                + "\"age\": 25,"
                + "\"money\": 350,"
                + "\"id\": 110,"
                + "\"secondName\": \"Vershinina\""
                + "}";
        Response  response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.GetToken())
                .body(requestBody)
                .when()
                .post("/user")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    //Проверка PUT запроса с корректными данными
    @Test
    public void PutUser() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4879";
        String requestBody = "{"
                + "\"firstName\": \"Alina2\","
                + "\"sex\": \"MALE\","
                + "\"age\": 26,"
                + "\"money\": 350,"
                + "\"id\": 110,"
                + "\"secondName\": \"Vershinina2\""
                + "}";
        Response  response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.GetToken())
                .body(requestBody)
                .when()
                .post("/user")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), 201);
    }



}
