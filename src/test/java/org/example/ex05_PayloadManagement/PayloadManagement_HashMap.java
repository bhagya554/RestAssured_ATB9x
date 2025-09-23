package org.example.ex05_PayloadManagement;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PayloadManagement_HashMap {
    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse vRes;

    @Description("Create Booking")
    @Test
    public void createBooking(){
       /*
       {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
        */
        HashMap<String,Object> payload=new LinkedHashMap<>();
        payload.put("firstname","Jim");
        payload.put("lastname","Brown");
        payload.put("totalprice",111);
        payload.put("depositpaid",true);
        HashMap<String,Object> bDates = new LinkedHashMap<>();
        bDates.put("checkin","2018-01-01");
        bDates.put("checkout","2019-01-01");
        payload.put("bookingdates",bDates);
        payload.put("additionalneeds","breakfast");
        System.out.println(payload);
        //https://restful-booker.herokuapp.com/booking
        reqSpec = given().body(payload).contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking");

        res=reqSpec.when().log().all().post();

        vRes=res.then().log().all();
        vRes.statusCode(200);
        vRes.body("booking.firstname",equalTo("Jim"));
        vRes.header("Content-Type",containsString("application/json"));
        vRes.body("bookingid",notNullValue());
        vRes.body("booking.bookingdates.checkin",equalTo("2018-01-01"));
    }
}
