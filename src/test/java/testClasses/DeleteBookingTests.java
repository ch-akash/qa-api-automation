package testClasses;

import api.AuthApi;
import api.DeleteBookingApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteBookingTests {


    @Test
    public void deleteBooking() {
        DeleteBookingApi deleteBookingApi = new DeleteBookingApi(3693);
        deleteBookingApi.setHeader("Cookie", "token=" + this.getToken());
        Response response = deleteBookingApi.sendRequest();
        response.then().statusCode(201);

    }

    private String getToken() {
        AuthApi authApi = new AuthApi();
        return authApi.getToken();
    }
}
