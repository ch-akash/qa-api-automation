package apiSamples;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class SimplePutApi {

    public static void main(String[] args) {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-11-11");
        bookingDates.put("checkout", "2024-01-01");
        requestBody.put("firstname", "Jim");
        requestBody.put("lastname", "Conrad");
        requestBody.put("totalprice", 1000);
        requestBody.put("depositpaid", true);
        requestBody.put("additionalneeds", "Mineral water");
        requestBody.put("bookingdates", bookingDates);

        String token = getToken();

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON)
                            .and().header("Cookie", "token=" + token)
                            .and().filters(new ResponseLoggingFilter(), new RequestLoggingFilter())
                            .and().baseUri("https://restful-booker.herokuapp.com")
                            .and().basePath("/booking/{id}")
                            .and().pathParams("id", 59)
                            .and().body(requestBody)
                            .when().put();

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
