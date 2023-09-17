package pojo;

import lombok.Data;

@Data
public class CreateBookingRequest {

    private String firstname;
    private String Lastname;
    private boolean depositPaid;
    private int totalPrice;
    private BookingDates bookingDates;
    private String additionalFields;

}
