package api;

import http.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import utils.PropertyConfiguration;

import java.util.HashMap;

public class AuthApi extends BaseApi {

    public AuthApi() {
        super("/auth", Method.POST);
        super.setContentType(ContentType.JSON);
        super.logResponse();
    }

    public String getToken() {
        var request = new HashMap<>();
        request.put("username", PropertyConfiguration.getConfig().username());
        request.put("password", PropertyConfiguration.getConfig().password());
        super.setBody(request);
        return super.sendRequest().then().statusCode(200)
                    .and().extract().jsonPath().getString("token");
    }
}
