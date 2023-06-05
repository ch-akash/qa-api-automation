package api;

import http.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class UpdateBookingApi extends BaseApi {
    public UpdateBookingApi(Object bookingId) {
        super("/booking/{id}", Method.PUT);
        super.setPathParams("id", bookingId);
        super.setContentType(ContentType.JSON);
        super.logResponse();
    }
}
