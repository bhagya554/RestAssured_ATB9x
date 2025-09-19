package org.example.ex02_RestAssuredBasics.PUT;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutRequest_NonBDDStyle
{
    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse vRes;
    String payload;
    int bookingId;
    String token;
    @Description("Restful Booker-Post Request-Create Booking")
    @Test(priority = 1)
    public void createBooking() {
        payload = "{\n" +
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

        res = reqSpec.when().log().all().post();
        vRes=res.then().log().all().statusCode(200);
        bookingId = res.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
    }

    @Description("Restful Booker-Post Request-Create token")
    @Test(priority = 2)
    public void createToken() {
        payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        reqSpec=given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .body(payload)
                .contentType(ContentType.JSON);

        res=reqSpec.when().log().all().post();
        vRes=res.then().log().all().statusCode(200);
        token=res.jsonPath().get("token");
    }
    /* We need:
    token
    bookingId

    public String getToken(){}
    public String getBookingId(){}

    In this test we would hardcoad token and bookingId value. In future we call above 2 functions
     */
    @Description("Restful Booker-Put Request-Update Booking - Positive")
    @Test(priority = 3)
    public void updateBooking_Positive() {
        payload = "{\n" +
                "    \"firstname\" : \"Karunya\",\n" +
                "    \"lastname\" : \"Hello\",\n" +
                "    \"totalprice\" : 456,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"dinner\"\n" +
                "}";
        //https://restful-booker.herokuapp.com/booking
        reqSpec = given().body(payload).contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/"+bookingId)
                .cookie("token",token);

        res = reqSpec.when().log().all().put();
        System.out.println("Updated Details");

        vRes=res.then().log().all().statusCode(200);

    }

    @Description("Restful Booker-Put Request-Update Booking - Negative: Invalid token")
    @Test(priority = 4)
    public void updateBooking_Negative1() {
        token="abc";
        payload = "{\n" +
                "    \"firstname\" : \"Karunya\",\n" +
                "    \"lastname\" : \"Hello\",\n" +
                "    \"totalprice\" : 456,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"dinner\"\n" +
                "}";
        //https://restful-booker.herokuapp.com/booking
        reqSpec = given().body(payload).contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/"+bookingId)
                .cookie("token",token);

        res = reqSpec.when().log().all().put();
        System.out.println("Updated Details");
        vRes=res.then().log().all().statusCode(403);

    }


}
