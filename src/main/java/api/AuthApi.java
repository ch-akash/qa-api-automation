package api;

import http.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import utils.PropertyConfiguration;

import java.util.HashMap;
import java.util.Map;

public class AuthApi extends BaseApi {

    public AuthApi() {
        super("/auth", Method.POST);
        super.setContentType(ContentType.JSON);
        super.logResponse();
    }

    public String getToken() {
        Map<String, Object> request = new HashMap<>();
        request.put("username", PropertyConfiguration.getConfig().username());
        request.put("password", PropertyConfiguration.getConfig().password());
        super.setBody(request);
        Response response = super.sendRequest();
        return response.then().statusCode(200)
                       .and().extract().jsonPath().getString("token");
    }
}
