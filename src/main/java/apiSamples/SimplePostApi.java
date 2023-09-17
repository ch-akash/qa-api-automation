package apiSamples;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.BookingDates;
import pojo.CreateBookingRequest;

import java.util.HashMap;
import java.util.Map;

public class SimplePostApi {

    public static void main(String[] args) {

        RequestSpecification requestSpecification = RestAssured.given();

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

//        requestSpecification.contentType(ContentType.JSON)
//                            .and().baseUri("https://restful-booker.herokuapp.com")
//                            .and().basePath("/booking")
//                            .and().filters(new ResponseLoggingFilter(), new RequestLoggingFilter())
//                            .and().body(requestBody)
//                            .when().post();

        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setFirstname("Jerry");
        createBookingRequest.setLastname("Helpert");
        createBookingRequest.setAdditionalFields("Mineral water");
        createBookingRequest.setDepositPaid(true);
        createBookingRequest.setTotalPrice(1000);
        BookingDates bookingDatesPojo = new BookingDates();
        bookingDatesPojo.setCheckOut("2024-01-01");
        bookingDatesPojo.setCheckIn("2023-11-11");
        createBookingRequest.setBookingDates(bookingDatesPojo);

        requestSpecification.contentType(ContentType.JSON)
                            .and().baseUri("https://restful-booker.herokuapp.com")
                            .and().basePath("/booking")
                            .and().filters(new ResponseLoggingFilter(), new RequestLoggingFilter())
                            .and().body(createBookingRequest)
                            .when().post();
        //Map<String,Object>
        //Pojo class

    }
}
