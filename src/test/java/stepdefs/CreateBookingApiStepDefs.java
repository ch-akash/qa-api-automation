package stepdefs;

import api.CreateBookingApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import sharedState.SharedContext;
import utils.RequestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateBookingApiStepDefs {


    private HashMap<Object, Object> requestBody;
    private Response createBookingResponse;
    private final SharedContext sharedContext;

    public CreateBookingApiStepDefs(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
    }

    @Given("we have a booking request")
    public void weHaveABookingRequest(List<Map<String, Object>> bookingRequestList) {
        var requestMap = bookingRequestList.get(0);
        requestBody = RequestUtil.getStringObjectMap(requestMap.get("firstname"),
                                                     requestMap.get("lastname"),
                                                     requestMap.get("additionalneeds"),
                                                     requestMap.get("depositpaid"),
                                                     requestMap.get("totalprice"),
                                                     requestMap.get("checkout"),
                                                     requestMap.get("checkin"));
        this.sharedContext.requestMap = requestBody;

    }

    @When("we send the request to create booking api")
    public void weSendTheRequestToCreateBookingApi() {
        var createBookingApi = new CreateBookingApi();
        createBookingApi.setBody(requestBody);
        createBookingResponse = createBookingApi.sendRequest();
        this.sharedContext.response = createBookingResponse;
    }

    @Then("HTTP response status code should be {int}")
    public void httpResponseStatusCodeShouldBe(int statusCode) {
        this.sharedContext.response.then().statusCode(statusCode);
    }

    @When("store the booking from create response")
    public void storeTheBookingFromCreateResponse() {
        this.sharedContext.bookingId = createBookingResponse.then().extract().jsonPath().getInt("bookingid");
    }

    @And("validate that response has bookingid")
    public void validateThatResponseHasBookingId() {
        this.sharedContext.response.then().body("bookingid", Matchers.notNullValue())
                                   .and().body("booking", Matchers.notNullValue());
    }
}
