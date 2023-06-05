package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBookingIdsResponse {

    @JsonProperty(value = "bookingid")
    private int bookingId;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
