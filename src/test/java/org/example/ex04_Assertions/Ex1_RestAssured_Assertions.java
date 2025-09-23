package org.example.ex04_Assertions;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Ex1_RestAssured_Assertions {
    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse vRes;
    String payload;
    @Description("Create Booking")
    @Test
    public void createBooking(){
        payload="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
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
