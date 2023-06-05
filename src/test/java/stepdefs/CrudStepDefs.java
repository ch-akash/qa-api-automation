package stepdefs;

import api.DeleteBookingApi;
import api.GetBookingApi;
import api.UpdateBookingApi;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import sharedState.SharedContext;

import java.util.HashMap;
import java.util.Map;

public class CrudStepDefs {

    private SharedContext sharedContext;

    public CrudStepDefs(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
    }

    @When("we {string} the previously created booking")
    public void weThePreviouslyCreatedBooking(String apiType) {

        if (apiType.equals("retrieve")) {
            GetBookingApi getBookingApi = new GetBookingApi(this.sharedContext.bookingId);
            this.sharedContext.response = getBookingApi.sendRequest();

        } else if (apiType.equals("update")) {
            Map<Object, Object> requestBody = this.sharedContext.requestMap;
            requestBody.replace("depositpaid", false);
            requestBody.replace("totalprice", 500);
            UpdateBookingApi updateBookingApi = new UpdateBookingApi(this.sharedContext.bookingId);
            updateBookingApi.setHeader("Cookie", "token=" + this.getToken());
            updateBookingApi.setBody(requestBody);
            this.sharedContext.response = updateBookingApi.sendRequest();

        } else if (apiType.equals("delete")) {
            DeleteBookingApi deleteBookingApi = new DeleteBookingApi(this.sharedContext.bookingId);
            deleteBookingApi.setHeader("Cookie", "token=" + this.getToken());
            this.sharedContext.response = deleteBookingApi.sendRequest();
        }
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
