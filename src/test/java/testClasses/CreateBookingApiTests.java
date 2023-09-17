package testClasses;

import api.CreateBookingApi;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.BookingDates;
import pojo.CreateBookingRequest;
import pojo.response.CreateBookingResponse;
import utils.RequestUtil;

import java.util.Map;

public class CreateBookingApiTests {


    @Test(description = "Create a booking an check response status code")
    public void createBookingStatusCodeValidation() {
        var requestBody = this.getDefaultRequestMap();
        var createBookingApi = new CreateBookingApi();
        createBookingApi.setBody(requestBody);
        createBookingApi.sendRequest().then().statusCode(200)
                        .and().body("bookingid", Matchers.notNullValue())
                        .and().body("booking", Matchers.notNullValue());
    }

    private Map<Object, Object> getDefaultRequestMap() {
        return RequestUtil.getStringObjectMap("Jim",
                                              "Brown",
                                              "Mineral water",
                                              true,
                                              1000,
                                              "2024-01-01",
                                              "2023-11-11");
    }

    private CreateBookingRequest getRequestPojo() {
        var createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setFirstname("Jerry");
        createBookingRequest.setLastname("Helpert");
        createBookingRequest.setAdditionalFields("Mineral water");
        createBookingRequest.setDepositPaid(true);
        createBookingRequest.setTotalPrice(1000);
        var bookingDatesPojo = new BookingDates();
        bookingDatesPojo.setCheckOut("2024-01-01");
        bookingDatesPojo.setCheckIn("2023-11-11");
        createBookingRequest.setBookingDates(bookingDatesPojo);
        return createBookingRequest;
    }

    @DataProvider(parallel = true)
    public Object[][] bookingDetailsDp() {
        return new Object[][]{
                //firstname, lastname, additional needs, depositPaid, checkout date, checkin date
                {"Jerry", "Helpert", "Minertal water", true, 1000, "2024-01-01", "2023-12-12"},
                {"Gerry", "Swans", "Minertal water", true, 1000, "2024-01-01", "2023-12-12"},
                {"Rebeca", "Brown", "Minertal water", true, 1000, "2024-01-01", "2023-12-12"},
                {"Ronald", "Reagen", "Minertal water", true, 1000, "2024-01-01", "2023-12-12"}
        };
    }

    @Test(description = "Create a booking with help of data provider", dataProvider = "bookingDetailsDp")
    public void createBookingStatusCodeValidationWithDp(
            String firstname, String lastname, String additionalNeeds,
            boolean depositPaid, int amount, String checkout, String checkin) {

        var requestBody = RequestUtil.getStringObjectMap(firstname, lastname, additionalNeeds, depositPaid, amount, checkout, checkin);
        var createBookingApi = new CreateBookingApi();
        createBookingApi.setBody(requestBody);
        var createBookingResponse = createBookingApi.sendRequest();
        createBookingResponse.then()
//                             .log().ifValidationFails(LogDetail.BODY).statusCode(200)
                             .and().body("bookingid", Matchers.notNullValue())
                             .and().body("booking", Matchers.notNullValue())
                             .and().rootPath("booking")
                             .and().body("firstname", Matchers.equalTo(firstname))
                             .and().body("lastname", Matchers.equalTo(lastname))
                             .and().body("additionalneeds", Matchers.equalTo(additionalNeeds))
                             .and().body("depositpaid", Matchers.equalTo(depositPaid))
                             .and().body("totalprice", Matchers.equalTo(amount))
                             .and().appendRootPath("bookingdates")
                             .and().body("checkin", Matchers.equalTo(checkin))
                             .and().body("checkout", Matchers.equalTo(checkout))
                             .and().detachRootPath("booking.bookingdates");

//       Setting list of arguments for validation in body() method.
//        var arguments = List.of(withArg("firstname"), withArg("lastname"), withArg("additionalneeds"));
//        createBookingResponse.then().statusCode(200)
//                             .and().rootPath("booking", arguments)
//                             .and().body(Matchers.notNullValue());


        var firstName = createBookingResponse.then().extract().jsonPath().getString("booking.firstname");
        System.out.println("Firstname: " + firstName);

        var lastName = createBookingResponse.then().extract().jsonPath().getString("booking.lastname");
        System.out.println("Lastname: " + lastName);

        var bookingDatesFromResponse = createBookingResponse.then().extract().jsonPath().getString("booking.bookingdates");
        System.out.println("Booking dates: " + bookingDatesFromResponse);

        var createBookingResponsePojo = createBookingResponse.as(CreateBookingResponse.class);
        System.out.println("Firstname: " + createBookingResponsePojo.getBooking().getFirstName());

    }

}
