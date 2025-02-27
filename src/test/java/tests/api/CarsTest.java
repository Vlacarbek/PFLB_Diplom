package tests.api;

import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class CarsTest {

    @Test
    //Позитивная проверка GET запроса с существующим пользователем
    public void GetCar() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        RestAssured.defaultParser = Parser.JSON;
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get("/car/54")
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String responseBody = response.getBody().asString();
        response.then()
                .body("id", equalTo(54))
                .body("engineType", equalTo("Diesel"))
                .body("mark", equalTo("Nissan"))
                .body("model", equalTo("Qashqai"))
                .body("price", equalTo(308058.0F));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    //Позитивная проверка GET запроса с несуществующим пользователем
    @Test
    public void GetCarNonExistent() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get("/car/999")
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.length(), 0);
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    //Позитивная проверка POST запроса с корректными данными
    @Test
    public void PostCar() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        String token = LoginTest.GetToken();

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Токен не получен");
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("engineType", "Diesel");
        requestBody.put("id", 798);
        requestBody.put("mark", "Lada");
        requestBody.put("model", "Lada");
        requestBody.put("price", 1000);

        RestAssured.defaultParser = Parser.JSON;

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.GetToken()) // Авторизация
                .body(requestBody.toString())
                .when()
                .post("/car")
                .then()
                .extract().response();
        response.then()
                .body("engineType", equalTo("Diesel"))
                .body("mark", equalTo("Lada"))
                .body("model", equalTo("Lada"))
                .body("price", equalTo(1000));
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    //Негативная проверка POST запроса с не корректными данными
    @Test
    public void PostCarInvalidDate() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        JSONObject requestBody = new JSONObject();
        requestBody.put("engineType", "");
        requestBody.put("id", "");
        requestBody.put("mark", "");
        requestBody.put("model", "");
        requestBody.put("price", 1000);
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.GetToken())
                .body(requestBody.toString())
                .when()
                .post("/car")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    //Проверка PUT запроса с корректными данными
    @Test
    public void PutCar() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        String token = LoginTest.GetToken();

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Токен не получен");
        }

        JSONObject createBody = new JSONObject();
        createBody.put("engineType", "Diesel");
        createBody.put("id", 659);
        createBody.put("mark", "Lada");
        createBody.put("model", "Lada");
        createBody.put("price", 1000);

        RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(createBody.toString())
                .post("/car");

        JSONObject updateBody = new JSONObject();
        updateBody.put("engineType", "Electric");
        updateBody.put("mark", "Tesla");
        updateBody.put("model", "Model S");
        updateBody.put("price", 90000);

        RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("id", 777)  // Используем ID созданного автомобиля
                .body(updateBody.toString())
                .when()
                .put("/car/{id}")  // URL с параметром
                .then()
                .statusCode(201)  // Ожидаемый код для успешного обновления
                .body("engineType", equalTo("Electric"))
                .body("mark", equalTo("Tesla"))
                .body("model", equalTo("Model S"))
                .body("price", equalTo(90000));
    }
    //Позитивная проверка Delete запроса на существующего пользователя
    @Test
    public void DeleteCar() {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        String token = LoginTest.GetToken();

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Токен не получен");
        }

        int carId = 798; // ID машины, которую нужно удалить

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/car/" + carId)
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
    }




}
