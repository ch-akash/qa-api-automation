package testClasses;

import api.GetAllBookingsApi;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;

public class GetAllBookingsApiTests {

    @Test
    public void getAllBookingsApiTest() {
        GetAllBookingsApi getAllBookingsApi = new GetAllBookingsApi();
        Response response = getAllBookingsApi.sendRequest();
        response.then().statusCode(200)
                .and().body("bookingid", Matchers.notNullValue());
    }

    @Test(description = "Validating response schema for getAllBookings Api")
    void jsonSchemaValidation() {
        GetAllBookingsApi getAllBookingsApi = new GetAllBookingsApi();
        File schema = new File("src/test/resources/schema/getbookingidsSchema.json");
        getAllBookingsApi.sendRequest()
                         .then().body(JsonSchemaValidator.matchesJsonSchema(schema));

    }
}
