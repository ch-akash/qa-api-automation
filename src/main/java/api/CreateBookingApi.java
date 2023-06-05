package api;

import http.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

import java.util.HashMap;
import java.util.Map;

public class CreateBookingApi extends BaseApi {
    public CreateBookingApi() {
        super("/booking", Method.POST);
        super.setContentType(ContentType.JSON);
        super.logResponse();
    }

    public Map<String, Object> getRequest() {
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
}
