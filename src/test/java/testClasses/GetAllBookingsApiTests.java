package testClasses;

import api.GetAllBookingsApi;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static utils.GlobalsConstants.SCHEMA_FOLDER_PATH;

public class GetAllBookingsApiTests {

    private GetAllBookingsApi getAllBookingsApi;

    @BeforeClass
    public void setup() {
        this.getAllBookingsApi = new GetAllBookingsApi();
    }

    @Test
    public void getAllBookingsApiTest() {
        this.getAllBookingsApi.sendRequest().then().statusCode(200)
                              .and().body("bookingid", Matchers.notNullValue());
    }

    @Test(description = "Validating response schema for getAllBookings Api")
    void jsonSchemaValidation() {
        this.getAllBookingsApi.sendRequest()
                              .then().body(JsonSchemaValidator
                                                   .matchesJsonSchema(new File(SCHEMA_FOLDER_PATH + File.separator + "getbookingidsSchema.json")));

    }
}
