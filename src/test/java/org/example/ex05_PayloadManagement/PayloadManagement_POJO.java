package org.example.ex05_PayloadManagement;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


import org.example.ex05_PayloadManagement.pojoClasses.Booking;
import org.example.ex05_PayloadManagement.pojoClasses.BookingDates;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PayloadManagement_POJO {
    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse vRes;

    @Description("Create Booking")
    @Test
    public void createBooking(){

        Booking booking = new Booking();
        booking.setFirstname("Bhagya");
        booking.setLastname("Kudupudi");
        booking.setTotalprice(9000);
        booking.setAdditionalneeds("Breakfast");
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        booking.setBookingDates(bookingDates);

        //https://restful-booker.herokuapp.com/booking
        reqSpec = given().body(booking).contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking");

        res=reqSpec.when().log().all().post();

        vRes=res.then().log().all();
        vRes.statusCode(200);
        vRes.body("booking.firstname",equalTo("Bhagya"));
        vRes.body("booking.lastname",equalTo("Kudupudi"));
        vRes.header("Content-Type",containsString("application/json"));
        vRes.body("bookingid",notNullValue());
        vRes.body("booking.bookingdates.checkin",equalTo("2018-01-01"));
    }
}
