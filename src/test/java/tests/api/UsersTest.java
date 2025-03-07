package tests.api;

import groovy.json.JsonException;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONObject;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class UsersTest {

    //Метод используемый в тестах, где нужны данные пользователей, которые в системе
    public JSONObject userList() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get("/users");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray usersArray = new JSONArray(response.getBody().asString());
        JSONObject firstUser = usersArray.getJSONObject(0);
        return firstUser;
    }

    @Test(testName = "GET запрос /user с существующим пользователем",
            description = "Позитивная проверка GET запроса с существующим пользователем")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Create users API")
    @Story("Create users API")
    @TmsLink("www.jira.com/C-1")
    public void getUser() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        JSONObject User = userList();
        Object idUser = User.get("id");
        Object firstNameUser = User.get("firstName");
        Object secondNameUser = User.get("secondName");
        Object ageUser = User.get("age");
        Object SexUser = User.get("sex");
        try {
            response = RestAssured.given()
                    .when()
                    .get("/user/" + idUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.then()
                .body("id", equalTo(idUser))
                .body("firstName", equalTo(firstNameUser))
                .body("secondName", equalTo(secondNameUser))
                .body("age", equalTo(ageUser))
                .body("sex", equalTo(SexUser));
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(testName = "GET запрос /user с несуществующим пользователем",
            description = "Позитивная проверка GET запроса с несуществующим пользователем")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Create users API")
    @Story("Create users API")
    @TmsLink("www.jira.com/C-1")
    public void getUserNonExistent() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get("/user/25435271");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String responseBody = response.getBody().asString();
        assertEquals(responseBody.length(), 0);
        assertEquals(response.getStatusCode(), 204);
    }

    @Test(testName = "GET /users запрос  с корректными данными",
            description = "Проверка GET /users запроса  с корректными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Create users API")
    @Story("Create users API")
    @TmsLink("www.jira.com/C-1")
    public void getUsers() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get("/users");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray usersArray = new JSONArray(response.getBody().asString());
        JSONObject firstUser = usersArray.getJSONObject(0);
        Assert.assertNotNull(firstUser.getString("firstName"), "User name should not be null");
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(testName = "POST запрос /user с корректными данными",
            description = "Позитивная проверка POST запроса /user с корректными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Create users API")
    @Story("Create users API")
    @TmsLink("www.jira.com/C-1")
    public void postUser() throws JsonException {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.baseURI = "http://82.142.167.37:4880";
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", "Alina78");
        requestBody.put("sex", "MALE");
        requestBody.put("age", 25);
        requestBody.put("money", 350);
        requestBody.put("id", 5656566);
        requestBody.put("secondName", "Vershinina");
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.getToken())
                .body(requestBody.toString())
                .when()
                .post("/user")
                .then()
                .extract().response();
        response.then()
                .body("firstName", equalTo("Alina78"))
                .body("secondName", equalTo("Vershinina"))
                .body("age", equalTo(25))
                .body("sex", equalTo("MALE"));
        assertEquals(response.getStatusCode(), 201);
    }

    @Test(testName = "POST запрос /user с не корректными данными",
            description = "Негативная проверка POST запроса /user с не корректными данными")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Create users API")
    @Story("Create users API")
    @TmsLink("www.jira.com/C-1")
    public void postUserInvalidDate() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", "");
        requestBody.put("sex", "");
        requestBody.put("age", 0);
        requestBody.put("money", 0);
        requestBody.put("id", 0);
        requestBody.put("secondName", "");
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.getToken())
                .body(requestBody.toString())
                .when()
                .post("/user")
                .then()
                .extract().response();
        assertEquals(response.getStatusCode(), 400);
    }

    //Проверка PUT запроса с корректными данными
    @Flaky
    @Test(testName = "PUT запрос /user с корректными данными",
            description = "Проверка PUT запроса /user с корректными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Create users API")
    @Story("Create users API")
    @TmsLink("www.jira.com/C-1")
    public void putUser() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        JSONObject User = userList();
        Object idUser = User.get("id");
        Object firstNameUser = User.get("firstName");
        Object secondNameUser = User.get("secondName");
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", "Alina");
        requestBody.put("sex", "MALE");
        requestBody.put("age", 37);
        requestBody.put("money", 999);
        requestBody.put("id", idUser);
        requestBody.put("secondName", "Vershinina");
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.getToken())
                .body(requestBody.toString())
                .when()
                .put("/user/" + idUser)
                .then()
                .extract().response();

        assertEquals(response.getStatusCode(), 201,"Если тест упал с 409 вероятно снова проблема с сервером ");
        JSONObject UserAfterUpdate = userList();
        assertNotEquals(firstNameUser, UserAfterUpdate.get("firstName"));
        assertNotEquals(secondNameUser, UserAfterUpdate.get("secondName"));

    }

    @Test(testName = "PUT запрос /user с некорректными данными",
            description = "Проверка PUT запроса /user с некорректными данными")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Create users API")
    @Story("Create users API")
    @TmsLink("www.jira.com/C-1")
    public void putUseInvalidDate() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        JSONObject User = userList();
        Object idUser = User.get("id");
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", "");
        requestBody.put("sex", "");
        requestBody.put("age", 37);
        requestBody.put("money", 999);
        requestBody.put("id", idUser);
        requestBody.put("secondName", "");

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.getToken())
                .body(requestBody.toString())
                .when()
                .put("/user/" + idUser)
                .then()
                .extract().response();
        assertEquals(response.getStatusCode(), 400);
    }

    @Test(testName = "Delete запрос /user  на существующего пользователя",
            description = "Позитивная проверка Delete запроса /user  на существующего пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("PFLB 1.0")
    @Feature("Create users API")
    @Story("Create users API")
    @TmsLink("www.jira.com/C-1")
    public void deleteUser() throws JsonException {
        JSONObject User = userList();
        Object idUser = User.get("id");
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .header("host", "82.142.167.37")
                    .header("Authorization", "Bearer " + LoginTest.getToken())
                    .when()
                    .delete("/user/" + idUser.toString())
                    .then()
                    .extract().response();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(response.getStatusCode(), 204, "Если тест упал с 409 вероятно снова проблема с сервером ");
        JSONObject UserAfterDelete = userList();
        Object idAfterDelete = UserAfterDelete.get("id");
        assertNotEquals(idAfterDelete, idUser);
    }

    @Test(testName = "Delete запрос /user на несуществующего пользователя",
            description = "Негативная проверка Delete запроса на несуществующего пользователя")
    @Severity(SeverityLevel.NORMAL)
    @Epic("PFLB 1.0")
    @Feature("Create users API")
    @Story("Create users API")
    @TmsLink("www.jira.com/C-1")
    public void deleteUserNonExistent() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .header("host", "82.142.167.37")
                    .header("Authorization", "Bearer " + LoginTest.getToken())
                    .when()
                    .delete("/user/76584588")
                    .then()
                    .extract().response();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(response.getStatusCode(), 404);
    }
}
