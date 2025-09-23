package org.example.ex07_JacksonDemo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class JacksonImplementation {
    //we are creating a booking.
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse vRes;
    ObjectMapper objectMapper = new ObjectMapper();
   Booking booking = new Booking();
   Bookingdates bookingDates = new Bookingdates();
   BookingResponse bookingResponse = new BookingResponse();
    String jsonStringPayload;
    @Test
    public void createBooking() throws JsonProcessingException {

        booking.setFirstname("Azai");
        booking.setLastname("Venkatesh");
        booking.setAdditionalneeds("lunch");
        booking.setTotalprice(345);
        booking.setDepositpaid(true);

        bookingDates.setCheckin("2018-11-21");
        bookingDates.setCheckout("2019-11-21");
        booking.setBookingdates(bookingDates);
        jsonStringPayload = objectMapper.writeValueAsString(booking);
        requestSpecification = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .body(jsonStringPayload)
                .contentType(ContentType.JSON);
        response = requestSpecification.log().all().when().post();

        /*
        3 ways to extract the response parameters
        1. response.then().extract().path("");
        2. Using jsonPath()
        3. Using Deserialization
         */
        vRes = response.then().log().all();

        //Using response.then().extract().path("");
        String firstName = response.then().extract().path("booking.firstname");
        System.out.println("Using extract().path(): " + firstName);
        //Using jsonPath()
        int bookingId=response.jsonPath().getInt("bookingid");
        System.out.println("Using jsonPath method : " + bookingId);

        //Using Deserilization
        String jsonResponseString=response.asString();
        bookingResponse = objectMapper.readValue(jsonResponseString, BookingResponse.class);

        System.out.println("Using Deserialization: " + bookingResponse.getBookingid());
        System.out.println("Using Deserialization: " + bookingResponse.getBooking().getLastname());
        System.out.println("Using Deserialization: " + bookingResponse.getBooking().getBookingdates().getCheckin());

        assertThat(bookingResponse.getBookingid()).isPositive().isNotNull().isNotZero();
        assertThat(bookingResponse.getBooking().getLastname()).isEqualTo("Venkatesh").isNotNull().isNotBlank();

    }
}
