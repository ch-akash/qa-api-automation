package apiSamples;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class SimpleDeleteApi {

    public static void main(String[] args) {
        String token = getToken();
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON)
                            .header("Cookie", "token=" + token)
                            .and().filters(new ResponseLoggingFilter(), new RequestLoggingFilter())
                            .and().baseUri("https://restful-booker.herokuapp.com")
                            .and().basePath("/booking/{id}")
                            .and().pathParams("id", 3739)
                            .when().delete();

    }

    public static String getToken() {
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

        String token = response.then().statusCode(200)
                               .and().extract().jsonPath().getString("token");
        return token;


    }
}
