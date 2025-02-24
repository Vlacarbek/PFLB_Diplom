package api;

import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONObject;
import static org.hamcrest.core.IsEqual.equalTo;

public class UsersTest {

    //Метод используемый в тестах, где нужны данные пользователей ,которые в системе
    public JSONObject UserList() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get("/users")
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray usersArray = new JSONArray(response.getBody().asString());
        JSONObject firstUser = usersArray.getJSONObject(0);
        return firstUser;
    }

    //Позитивная проверка GET запроса с существующим пользователем
    @Test
    public void GetUser() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        JSONObject User = UserList();
        Object idUser = User.get("id");
        Object firstNameUser = User.get("firstName");
        Object secondNameUser = User.get("secondName");
        Object ageUser = User.get("age");
        Object SexUser = User.get("sex");
        try {
            response = RestAssured.given()
                    .when()
                    .get("/user/" + idUser)
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String responseBody = response.getBody().asString();
        response.then()
                .body("id", equalTo(idUser))
                .body("firstName", equalTo(firstNameUser))
                .body("secondName", equalTo(secondNameUser))
                .body("age", equalTo(ageUser))
                .body("sex", equalTo(SexUser));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    //Позитивная проверка GET запроса с несуществующим пользователем
    @Test
    public void GetUserNonExistent() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get("/user/25435271")
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.length(), 0);
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    //Проверка GET запроса с корректными данными
    @Test
    public void GetUsers() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get("/users")
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray usersArray = new JSONArray(response.getBody().asString());
        JSONObject firstUser = usersArray.getJSONObject(0);
        Assert.assertNotNull(firstUser.getString("firstName"), "User name should not be null");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    //Позитивная проверка POST запроса с корректными данными
    @Test
    public void PostUser() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", "Alina7");
        requestBody.put("sex", "MALE");
        requestBody.put("age", 25);
        requestBody.put("money", 350);
        requestBody.put("id", 65475757);
        requestBody.put("secondName", "Vershinina");
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + LoginTest.GetToken())
                .body(requestBody.toString())
                .when()
                .post("/user")
                .then()
                .extract().response();
        response.then()
                .body("firstName", equalTo("Alina7"))
                .body("secondName", equalTo("Vershinina"))
                .body("age", equalTo(25))
                .body("sex", equalTo("MALE"));
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    //Негативная проверка POST запроса с не корректными данными
    @Test
    public void PostUserInvalidDate() throws JsonException {
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
                .header("Authorization", "Bearer " + LoginTest.GetToken())
                .body(requestBody.toString())
                .when()
                .post("/user")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    //Проверка PUT запроса с корректными данными
    @Test
    public void PutUser() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        JSONObject User = UserList();
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
                .header("Authorization", "Bearer " + LoginTest.GetToken())
                .body(requestBody.toString())
                .when()
                .put("/user/" + idUser)
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 201,"Если тест упал с 409 вероятно снова проблема с сервером ");
        JSONObject UserAfterUpdate = UserList();
        Assert.assertNotEquals(firstNameUser, UserAfterUpdate.get("firstName"));
        Assert.assertNotEquals(secondNameUser, UserAfterUpdate.get("secondName"));

    }

    //Проверка PUT запроса с некорректными данными
    @Test
    public void PutUseInvalidDate() throws JsonException {
        RestAssured.baseURI = "http://82.142.167.37:4880";
        JSONObject User = UserList();
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
                .header("Authorization", "Bearer " + LoginTest.GetToken())
                .body(requestBody.toString())
                .when()
                .put("/user/" + idUser)
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 400);
    }

    //Позитивная проверка Delete запроса на существующего пользователя
    @Test
    public void DeleteUser() throws JsonException {
        JSONObject User = UserList();
        Object idUser = User.get("id");
        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .header("host", "82.142.167.37")
                    .header("Authorization", "Bearer " + LoginTest.GetToken())
                    .when()
                    .delete("/user/" + idUser.toString())
                    .then()
                    .extract().response();
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(response.getStatusCode(), 204, "Если тест упал с 409 вероятно снова проблема с сервером ");
        JSONObject UserAfterDelete = UserList();
        Object idAfterDelete = UserAfterDelete.get("id");
        Assert.assertNotEquals(idAfterDelete, idUser);
    }

    //Негативная проверка Delete запроса на несуществующего пользователя
    @Test
    public void DeleteUserNonExistent() throws JsonException {

        RestAssured.baseURI = "http://82.142.167.37:4880";
        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .header("host", "82.142.167.37")
                    .header("Authorization", "Bearer " + LoginTest.GetToken())
                    .when()
                    .delete("/user/76584588")
                    .then()
                    .extract().response();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
