package utils;

import java.util.HashMap;

public class RequestUtil {

    public static HashMap<Object, Object> getStringObjectMap(Object firstname, Object lastname, Object additionalNeeds, Object depositPaid, Object amount, Object checkout, Object checkin) {
        var requestBody = new HashMap<>();
        var bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        requestBody.put("firstname", firstname);
        requestBody.put("lastname", lastname);
        requestBody.put("totalprice", amount);
        requestBody.put("depositpaid", depositPaid);
        requestBody.put("additionalneeds", additionalNeeds);
        requestBody.put("bookingdates", bookingDates);
        return requestBody;
    }
}
