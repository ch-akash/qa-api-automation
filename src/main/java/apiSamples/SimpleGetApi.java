package apiSamples;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import pojo.GetBookingIdsResponse;

import java.io.File;

public class SimpleGetApi {

    public static void main(String[] args) {
        RequestSpecification requestSpecification = RestAssured.given();
        File schema = new File("src/test/resources/schema/getbookingidsSchema.json");
        ValidatableResponse validatableResponse = requestSpecification//preconditions
                                                                      .accept(ContentType.JSON)
                                                                      .filter(new RequestLoggingFilter())
                                                                      .and().baseUri("https://restful-booker.herokuapp.com")
                                                                      .and().basePath("/booking")
                                                                      //action start here
                                                                      .when().get()
                                                                      .then().statusCode(200)
                                                                      .and().body("bookingid", Matchers.notNullValue())
                                                                      .and().body(JsonSchemaValidator.matchesJsonSchema(schema));

        GetBookingIdsResponse[] responsePojo = validatableResponse.extract().as(GetBookingIdsResponse[].class);
        System.out.println(responsePojo[1].getBookingId());

        //We have printed request and responses
        //We have done assertions and validations of status codes, body fields using jsonpath
        //We have validated Jsonschema
        //We have NOT used any other Java library except RestAssured.
        //We will parse response using RestAssured's method as()

    }
}
