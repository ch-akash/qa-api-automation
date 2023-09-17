package testClasses;

import api.DeleteBookingApi;
import org.testng.annotations.Test;
import utils.AuthenticationUtil;

public class DeleteBookingTests {


    @Test
    public void deleteBooking() {
        //Sample hardcoded booking id. It needs to be replaced with a new value when the test is executed
        var deleteBookingApi = new DeleteBookingApi(3693);
        deleteBookingApi.setHeader("Cookie", "token=" + this.getToken());
        deleteBookingApi.sendRequest().then().statusCode(201);

    }

    private String getToken() {
        return AuthenticationUtil.getToken();
    }
}
