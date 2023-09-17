package stepdefs;

import api.DeleteBookingApi;
import api.GetBookingApi;
import api.UpdateBookingApi;
import io.cucumber.java.en.When;
import sharedState.SharedContext;
import utils.AuthenticationUtil;

public class CrudStepDefs {

    private SharedContext sharedContext;

    public CrudStepDefs(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
    }

    @When("we {string} the previously created booking")
    public void weThePreviouslyCreatedBooking(String apiType) {
        switch (apiType) {
            case "retrieve" -> {
                var getBookingApi = new GetBookingApi(this.sharedContext.bookingId);
                this.sharedContext.response = getBookingApi.sendRequest();
            }
            case "update" -> {
                var requestBody = this.sharedContext.requestMap;
                requestBody.replace("depositpaid", false);
                requestBody.replace("totalprice", 500);
                var updateBookingApi = new UpdateBookingApi(this.sharedContext.bookingId);
                updateBookingApi.setHeader("Cookie", "token=" + this.getToken());
                updateBookingApi.setBody(requestBody);
                this.sharedContext.response = updateBookingApi.sendRequest();
            }
            case "delete" -> {
                var deleteBookingApi = new DeleteBookingApi(this.sharedContext.bookingId);
                deleteBookingApi.setHeader("Cookie", "token=" + this.getToken());
                this.sharedContext.response = deleteBookingApi.sendRequest();
            }
        }
    }

    private String getToken() {
        return AuthenticationUtil.getToken();
    }
}
