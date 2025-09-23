package org.example.ex06_GSONDemo;


import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.ex05_PayloadManagement.GSON.BookingDates;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import static io.restassured.RestAssured.given;

public class GSONImplementation {
    //we are creating a booking.
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse vRes;
    Gson gson = new Gson();
    Booking booking = new Booking();
    Bookingdates bookingDates=new Bookingdates();
    BookingResponse bookingResponse = new BookingResponse();
    String jsonStringPayload;
    @Test
    public void createBooking(){

       booking.setFirstname("Jaasritha");
       booking.setLastname("Guthula");
       booking.setAdditionalneeds("lunch");
       booking.setTotalprice(345);
       booking.setDepositpaid(true);

       bookingDates.setCheckin("2018-11-21");
       bookingDates.setCheckout("2019-11-21");
       booking.setBookingdates(bookingDates);
       jsonStringPayload = gson.toJson(booking);
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
        bookingResponse = gson.fromJson(jsonResponseString,BookingResponse.class);

        System.out.println("Using Deserialization: " + bookingResponse.getBookingid());
        System.out.println("Using Deserialization: " + bookingResponse.getBooking().getLastname());
        System.out.println("Using Deserialization: " + bookingResponse.getBooking().getBookingdates().getCheckin());

        assertThat(bookingResponse.getBookingid()).isPositive().isNotNull().isNotZero();
        assertThat(bookingResponse.getBooking().getLastname()).isEqualTo("Guthula").isNotNull().isNotBlank();

    }
}
