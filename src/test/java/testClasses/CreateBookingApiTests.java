package testClasses;

import api.CreateBookingApi;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.BookingDates;
import pojo.CreateBookingRequest;
import pojo.response.CreateBookingResponse;

import java.util.HashMap;
import java.util.Map;

public class CreateBookingApiTests {


    @Test(description = "Create a booking an check response status code")
    public void createBookingStatusCodeValidation() {
        Map<String, Object> requestBody = this.getDefaultRequestMap();
        CreateBookingApi createBookingApi = new CreateBookingApi();
        createBookingApi.setBody(requestBody);
        Response createBookingResponse = createBookingApi.sendRequest();
        createBookingResponse.then().statusCode(200)
                             .and().body("bookingid", Matchers.notNullValue())
                             .and().body("booking", Matchers.notNullValue());
    }

    private Map<String, Object> getDefaultRequestMap() {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-11-11");
        bookingDates.put("checkout", "2024-01-01");
        requestBody.put("firstname", "Jim");
        requestBody.put("lastname", "Harper");
        requestBody.put("totalprice", 1000);
        requestBody.put("depositpaid", true);
        requestBody.put("additionalneeds", "Mineral water");
        requestBody.put("bookingdates", bookingDates);
        return requestBody;
    }

    private CreateBookingRequest getRequestPojo() {
        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setFirstname("Jerry");
        createBookingRequest.setLastname("Helpert");
        createBookingRequest.setAdditionalfields("Mineral water");
        createBookingRequest.setDepositpaid(true);
        createBookingRequest.setTotalprice(1000);
        BookingDates bookingDatesPojo = new BookingDates();
        bookingDatesPojo.setCheckout("2024-01-01");
        bookingDatesPojo.setCheckin("2023-11-11");
        createBookingRequest.setBookingdates(bookingDatesPojo);
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
    public void createBookingStatusCodeValidationWithDp(String firstname, String lastname, String additionalNeeds,
                                                        boolean depositPaid, int amount, String checkout, String checkin) {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        requestBody.put("firstname", firstname);
        requestBody.put("lastname", lastname);
        requestBody.put("totalprice", amount);
        requestBody.put("depositpaid", depositPaid);
        requestBody.put("additionalneeds", additionalNeeds);
        requestBody.put("bookingdates", bookingDates);
        CreateBookingApi createBookingApi = new CreateBookingApi();
        createBookingApi.setBody(requestBody);
        Response createBookingResponse = createBookingApi.sendRequest();
        createBookingResponse.then().statusCode(200)
                             .and().body("bookingid", Matchers.notNullValue())
                             .and().body("booking", Matchers.notNullValue())
                             .and().body("booking.firstname", Matchers.equalTo(firstname))
                             .and().body("booking.lastname", Matchers.equalTo(lastname))
                             .and().body("booking.additionalneeds", Matchers.equalTo(additionalNeeds))
                             .and().body("booking.depositpaid", Matchers.equalTo(depositPaid))
                             .and().body("booking.totalprice", Matchers.equalTo(amount))
                             .and().body("booking.bookingdates.checkin", Matchers.equalTo(checkin))
                             .and().body("booking.bookingdates.checkout", Matchers.equalTo(checkout));

        String firstName = createBookingResponse.then().extract().jsonPath().getString("booking.firstname");
        System.out.println("Firstname: " + firstName);

        String lastName = createBookingResponse.then().extract().jsonPath().getString("booking.lastname");
        System.out.println("Lastname: " + lastName);

        String bookingDatesFromResponse = createBookingResponse.then().extract().jsonPath().getString("booking.bookingdates");
        System.out.println("Booking dates: " + bookingDatesFromResponse);

        CreateBookingResponse createBookingResponsePojo = createBookingResponse.as(CreateBookingResponse.class);
        System.out.println("Firstname: " + createBookingResponsePojo.getBooking().getFirstname());

    }

}
