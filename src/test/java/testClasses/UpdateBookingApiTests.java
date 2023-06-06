package testClasses;

import api.AuthApi;
import api.UpdateBookingApi;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UpdateBookingApiTests {


    @Test
    public void updateApiTest() {
        UpdateBookingApi updateBookingApi = new UpdateBookingApi(5074);
        updateBookingApi.setHeader("Cookie", "token=" + this.getToken());
        updateBookingApi.setBody(this.getUpdateRequestMap("Test", "Automation"));
        Response response = updateBookingApi.sendRequest();
        response.then().statusCode(200)
                .and().body("bookingid", Matchers.notNullValue())
                .and().body("booking", Matchers.notNullValue());
    }

    private String getToken() {
        AuthApi authApi = new AuthApi();
        return authApi.getToken();
    }

    private Map<String, Object> getUpdateRequestMap(String firstname, String lastname) {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-11-11");
        bookingDates.put("checkout", "2024-01-01");
        requestBody.put("firstname", firstname);
        requestBody.put("lastname", lastname);
        requestBody.put("totalprice", 1000);
        requestBody.put("depositpaid", true);
        requestBody.put("additionalneeds", "Mineral water");
        requestBody.put("bookingdates", bookingDates);
        return requestBody;
    }
}
