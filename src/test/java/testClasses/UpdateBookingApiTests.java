package testClasses;

import api.UpdateBookingApi;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.AuthenticationUtil;
import utils.RequestUtil;

import java.util.HashMap;

public class UpdateBookingApiTests {


    @Test
    public void updateApiTest() {
        //Sample hardcoded booking id. It needs to be replaced with a new value when the test is executed
        var updateBookingApi = new UpdateBookingApi(5074);
        updateBookingApi.setHeader("Cookie", "token=" + AuthenticationUtil.getToken());
        updateBookingApi.setBody(this.getUpdateRequestMap("Test", "Automation"));
        updateBookingApi.sendRequest().then().statusCode(200)
                        .and().body("bookingid", Matchers.notNullValue())
                        .and().body("booking", Matchers.notNullValue());
    }

    private HashMap<Object, Object> getUpdateRequestMap(String firstName, String lastName) {
        return RequestUtil.getStringObjectMap(firstName,
                                              lastName,
                                              "Mineral water",
                                              true,
                                              1000,
                                              "2024-01-01",
                                              "2023-11-11");
    }
}
