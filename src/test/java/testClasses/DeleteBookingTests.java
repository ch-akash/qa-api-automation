package testClasses;

import api.DeleteBookingApi;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DeleteBookingTests {


    @Test
    public void deleteBooking() {
        DeleteBookingApi deleteBookingApi = new DeleteBookingApi(3693);
        deleteBookingApi.setHeader("Cookie", "token=" + this.getToken());
        Response response = deleteBookingApi.sendRequest();
        response.then().statusCode(201);

    }

    private String getToken() {
        Map<String, Object> request = new HashMap<>();
        request.put("username", "admin");
        request.put("password", "password123");
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.contentType(ContentType.JSON)
                                                .filters(new ResponseLoggingFilter(), new RequestLoggingFilter())
                                                .and().baseUri("https://restful-booker.herokuapp.com")
                                                .and().basePath("/auth")
                                                .and().body(request)
                                                .when().post();

        return response.then().statusCode(200)
                       .and().extract().jsonPath().getString("token");


    }
}
