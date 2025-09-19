package org.example.ex02_RestAssuredBasics.POST;
//Non-BDD style - we use Rest Assured interfaces

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequest_NonBDDStyle {

    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse vRes;
    String payload;
    @Description("Restful Booker-Post Request-Create token : Positive Testcase")
    @Test
    public void test_postReq_NonBDD_Positive() {
        payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        reqSpec=given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .body(payload)
                .contentType(ContentType.JSON);

        res=reqSpec.when().log().all().post();

        vRes = res.then().log().all().statusCode(200);
    }

    @Description("Restful Booker-Post Request-Create token : Negative Testcase - Invalid Credentials")
    @Test
    public void test_postReq_NonBDD_Negative() {
        payload="{\n" +
                "    \"username\" : \"admin3132\",\n" +
                "    \"password\" : \"password1233\"\n" +
                "}";
        reqSpec=given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .body(payload)
                .contentType(ContentType.JSON);

        res=reqSpec.when().log().all().post();

        vRes = res.then().log().all().statusCode(200);
    }

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

        res=reqSpec.when().post();
        int bookingId= res.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
    }
}
