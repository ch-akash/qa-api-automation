package testClasses;

import api.CreateBookingApi;
import api.DeleteBookingApi;
import api.GetBookingApi;
import api.UpdateBookingApi;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.AuthenticationUtil;
import utils.RequestUtil;

import java.util.Map;

public class CrudTest {


    private int bookingId;

    @Test(description = "Create a booking")
    public void createBooking() {
        var requestBody = this.getDefaultRequestMap();
        var createBookingApi = new CreateBookingApi();
        createBookingApi.setBody(requestBody);
        this.bookingId = createBookingApi.sendRequest().then().statusCode(200)
                                         .and().body("bookingid", Matchers.notNullValue())
                                         .and().body("booking", Matchers.notNullValue())
                                         .extract().jsonPath().getInt("bookingid");
    }


    @Test(description = "Retrieve the booking", dependsOnMethods = "createBooking")
    public void retrieveBooking() {
        var getBookingApi = new GetBookingApi(this.bookingId);
        getBookingApi.sendRequest()
                     .then().statusCode(200)
                     .and().body("bookingdates", Matchers.notNullValue());

    }

    @Test(description = "Update the booking", dependsOnMethods = "retrieveBooking")
    public void updateBooking() {
        var requestBody = this.getDefaultRequestMap();
        requestBody.replace("depositpaid", false);
        requestBody.replace("totalprice", 500);
        var updateBookingApi = new UpdateBookingApi(this.bookingId);
        updateBookingApi.setHeader("Cookie", "token=" + this.getToken());
        updateBookingApi.setBody(requestBody);
        updateBookingApi.sendRequest().then().statusCode(200)
                        .and().body("bookingdates", Matchers.notNullValue());
    }

    @Test(description = "Delete the booking", dependsOnMethods = "updateBooking")
    public void deleteBooking() {
        var deleteBookingApi = new DeleteBookingApi(this.bookingId);
        deleteBookingApi.setHeader("Cookie", "token=" + this.getToken());
        deleteBookingApi.sendRequest().then().statusCode(201);
    }

    private Map<Object, Object> getDefaultRequestMap() {
        return RequestUtil.getStringObjectMap("Jim",
                                              "Harper",
                                              "Mineral water",
                                              true,
                                              1000,
                                              "2024-01-01",
                                              "2023-11-11");
    }

    private String getToken() {
        return AuthenticationUtil.getToken();
    }
}


