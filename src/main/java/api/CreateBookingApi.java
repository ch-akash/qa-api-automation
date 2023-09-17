package api;

import http.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class CreateBookingApi extends BaseApi {
    public CreateBookingApi() {
        super("/booking", Method.POST);
        super.setContentType(ContentType.JSON);
        super.logResponse();
    }
}
