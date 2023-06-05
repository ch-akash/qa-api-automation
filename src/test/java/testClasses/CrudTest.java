package testClasses;

import api.CreateBookingApi;
import api.DeleteBookingApi;
import api.GetBookingApi;
import api.UpdateBookingApi;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CrudTest {


    private int bookingId;

    @Test(description = "Create a booking")
    public void createBooking() {
        Map<String, Object> requestBody = this.getDefaultRequestMap();
        CreateBookingApi createBookingApi = new CreateBookingApi();
        createBookingApi.setBody(requestBody);
        Response createBookingResponse = createBookingApi.sendRequest();
        bookingId = createBookingResponse.then().statusCode(200)
                                         .and().body("bookingid", Matchers.notNullValue())
                                         .and().body("booking", Matchers.notNullValue())
                                         .extract().jsonPath().getInt("bookingid");
    }


    @Test(description = "Retrieve the booking", dependsOnMethods = "createBooking")
    public void retrieveBooking() {
        GetBookingApi getBookingApi = new GetBookingApi(this.bookingId);
        Response retrieveBookingResponse = getBookingApi.sendRequest();
        retrieveBookingResponse.then().statusCode(200)
                               .and().body("bookingdates", Matchers.notNullValue());

    }

    @Test(description = "Update the booking", dependsOnMethods = "retrieveBooking")
    public void updateBooking() {
        Map<String, Object> requestBody = this.getDefaultRequestMap();
        requestBody.replace("depositpaid", false);
        requestBody.replace("totalprice", 500);
        UpdateBookingApi updateBookingApi = new UpdateBookingApi(this.bookingId);
        updateBookingApi.setHeader("Cookie", "token=" + this.getToken());
        updateBookingApi.setBody(requestBody);
        Response response = updateBookingApi.sendRequest();
        response.then().statusCode(200)
                .and().body("bookingdates", Matchers.notNullValue());
    }

    @Test(description = "Delete the booking", dependsOnMethods = "updateBooking")
    public void deleteBooking() {
        DeleteBookingApi deleteBookingApi = new DeleteBookingApi(this.bookingId);
        deleteBookingApi.setHeader("Cookie", "token=" + this.getToken());
        Response response = deleteBookingApi.sendRequest();
        response.then().statusCode(201);
    }

    private Map<String, Object> getDefaultRequestMap() {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-11-11");
        bookingDates.put("checkout", "2024-01-01");
        requestBody.put("firstname", "Jim");
        requestBody.put("lastname", "Harper");
        requestBody.put("totalprice", 1000);
        requestBody.put("depositpaid", true);
        requestBody.put("additionalneeds", "Mineral water");
        requestBody.put("bookingdates", bookingDates);
        return requestBody;
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


