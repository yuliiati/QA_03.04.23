package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiBase {

    final String BASE_URI = "http://phonebook.telran-edu.de:8080/";
    final String API_KEY = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6InRlc3RAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MjEwNjk3ODI5Nn0.GM1wsoRV2QoAsD6wKmIk7N49DDpuCejK4BC9H9YItJvesH5vft8HO2uqTPnGQJwJ5oXKS2OILqP1yoanMnIMkA";

    RequestSpecification specification = new RequestSpecBuilder()   // Сбилдить спецификацию. Завели переменную, чтоб потом использовать
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("Access-Token", API_KEY)
            .build();

    public Response getRequest(String endPoint, Integer responseCode) {
        Response response = RestAssured.given()
                .spec(specification)
                .when()
                .log().all()    // Логииииииииииииииииииииииииииииииии ааа
                .get(endPoint)
                .then().log().all()
                .extract().response();  // Извлекаем ответ
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response getRequestWithParam(String endPoint, Integer responseCode, int id) {    // String paramName,
        Response response = RestAssured.given()
                .spec(specification)
                .when()
                .pathParam("id", id)    // Пам-Парам :D / "id" убрали
                .log().all()
                .get(endPoint)
                .then().log().all()
                .extract().response();  // Извлекаем ответ
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response postRequest(String endPoint, Integer responseCode, Object body) {
        Response response = RestAssured.given()
                .spec(specification)
                .body(body)
                .when()
                .log().all()
                .post(endPoint)
                .then().log().all()
                .extract().response();  // Извлекаем ответ
        response.then().assertThat().statusCode(responseCode);
        return response;
    }
    // Мы как воспитатели, следим, чтобы разработчики ничего не сломали :D
    // Все, на самом деле проще, чем вы думаете
    // Ща, секунду, Error словил :DDDD (c) Leo

    public Response putRequest(String endPoint, Integer responseCode, Object body) {
        Response response = RestAssured.given()
                .spec(specification)
                .body(body)
                .when()
                .log().all()
                .put(endPoint)
                .then().log().all()
                .extract().response();  // Извлекаем ответ
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response deleteRequest(String endPoint, Integer responseCode, int id) {
        Response response = RestAssured.given()
                .spec(specification)
                .when()
                .pathParam("id", id)
                .log().all()
                .delete(endPoint)
                .then().log().all()
                .extract().response();  // Извлекаем ответ
        response.then().assertThat().statusCode(responseCode);
        return response;
    }


}
