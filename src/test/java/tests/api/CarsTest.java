package tests.api;

import groovy.json.JsonException;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class CarsTest {

    //Метод используемый в тестах, где нужны данные пользователей, которые в системе
    public JSONObject carList() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get("/car"); // Убедитесь, что путь /car правильный, а не /сar (кириллическая "с")
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при выполнении запроса к /car");
        }

        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        if (response.getStatusCode() != 200 || responseBody.isEmpty()) {
            throw new RuntimeException("Ошибка: сервер вернул " + response.getStatusCode() + " с пустым телом.");
        }

        if (responseBody.startsWith("[")) {
            // Если это массив, парсим как JSONArray
            JSONArray carsArray = new JSONArray(responseBody);
            if (carsArray.length() == 0) {
                throw new RuntimeException("Ошибка: сервер вернул пустой массив машин.");
            }
            return carsArray.getJSONObject(0); // Берем первый объект
        } else if (responseBody.startsWith("{")) {
            // Если это объект, сразу парсим как JSONObject
            return new JSONObject(responseBody);
        } else {
            throw new RuntimeException("Ошибка: сервер вернул некорректный JSON: " + responseBody);
        }
    }

    @Test(testName = "GET запрос /car с существующим пользователем",
            description = "Позитивная проверка GET запроса с существующим автомобилем")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Create cars API")
    @Story("Create cars API")
    @TmsLink("www.jira.com/C-2")
    public void getCar() throws JsonException {
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

    @Test(testName = "GET запрос /car с несуществующим автомобилем",
            description = "Позитивная проверка GET запроса с несуществующим автомобилем")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Create cars API")
    @Story("Create cars API")
    @TmsLink("www.jira.com/C-2")
    public void getCarNonExistent() throws JsonException {
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

    @Test(testName = "POST запрос /car с корректными данными",
            description = "Позитивная проверка POST запроса /car с корректными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Create cars API")
    @Story("Create cars API")
    @TmsLink("www.jira.com/C-2")
    public void postCar() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";

        JSONObject requestBody = new JSONObject();
        requestBody.put("engineType", "Diesel");

        requestBody.put("mark", "Lada");
        requestBody.put("model", "Lada");
        requestBody.put("price", 1000);

        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.getToken())
                .body(requestBody.toString())
                .when()
                .post("/car")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("engineType", equalTo("Diesel"))
                .body("mark", equalTo("Lada"))
                .body("model", equalTo("Lada"))
                .body("price", equalTo(1000))
                .extract().response();
    }

    @Test(testName = "POST запрос /car с не корректными данными",
            description = "Негативная проверка POST запроса /car с не корректными данными")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Create cars API")
    @Story("Create cars API")
    @TmsLink("www.jira.com/C-2")
    public void postCarInvalidDate() throws JsonException {
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
                .header("Authorization", "Bearer " + LoginTest.getToken())
                .body(requestBody.toString())
                .when()
                .post("/car")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    //Проверка PUT запроса с корректными данными
    @Flaky
    @Test(testName = "PUT запрос /сar с корректными данными",
            description = "Проверка PUT запроса /сar с корректными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Create сars API")
    @Story("Create сars API")
    @TmsLink("www.jira.com/C-1")
    public void putCar() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";

        // Создаём тело запроса с обновлёнными данными
        JSONObject requestBody = new JSONObject();
        requestBody.put("engineType", "Electric");
        requestBody.put("mark", "Volga");
        requestBody.put("model", "NewModel");
        requestBody.put("price", 2000);

        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.getToken())
                .body(requestBody.toString())
                .when()
                .put("/car/1") // Указываем ID автомобиля для обновления
                .then()
                .log().all()
                .assertThat()
                .statusCode(202) // Ожидаемый статус при успешном обновлении
                .body("engineType", equalTo("Electric"))
                .body("mark", equalTo("Volga"))
                .body("model", equalTo("NewModel"))
                .body("price", equalTo(2000))
                .extract().response();
    }
    @Test(testName = "PUT запрос /сar с некорректными данными",
            description = "Проверка PUT запроса /сar с некорректными данными")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Create сars API")
    @Story("Create сars API")
    @TmsLink("www.jira.com/C-2")
    public void putCarInvalidDate() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";

        // Создаём тело с ошибками:
        JSONObject invalidBody = new JSONObject();
        invalidBody.put("engineType", "INVALID_ENGINE_TYPE");
        invalidBody.put("price", "NOT_A_NUMBER");
        invalidBody.put("model", "");

        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.getToken())
                .body(invalidBody.toString())
                .when()
                .put("/car/1")  // Предполагаем, что автомобиль с ID=1 существует
                .then()
                .log().all()
                .assertThat()
                .statusCode(400)  // Ожидаем Bad Request (или 422 Unprocessable Entity)
                .extract().response();
    }


    @Test(testName = "Delete запрос /car  на существующего пользователя",
            description = "Позитивная проверка Delete запроса /car  на существующего пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Create cars API")
    @Story("Create cars API")
    @TmsLink("www.jira.com/C-2")
    public void deleteUser() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";

        RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer " + LoginTest.getToken())
                .header("host", "82.142.167.37")
                .when()
                .delete("/car/62")
                .then()
                .log().all()
                .assertThat()
                .statusCode(204);  // или 204, если ожидается отсутствие тела в ответе
    }
    @Test
    public void deleteCarInvalid() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";

        Response response = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer " + LoginTest.getToken())
                .header("host", "82.142.167.37")
                .when()
                .delete("/user/" + 1871)
                .then()
                .log().all()
                .assertThat()
                .statusCode(404)
                .extract().response();
    }


}
