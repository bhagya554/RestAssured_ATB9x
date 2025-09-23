package org.example.ex04_Assertions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class Ex3_AssertJ_Assertions {
    //https://restful-booker.herokuapp.com/booking
    RequestSpecification reqSpec;
    Response response;
    ValidatableResponse validatableResponse;
    String payload;
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
       reqSpec = given()
               .baseUri("https://restful-booker.herokuapp.com")
               .basePath("/booking")
               .body(payload)
               .contentType(ContentType.JSON);
       response=reqSpec.when().log().all().post();

       int bookingId=response.then().extract().path("bookingid");
       assertThat(bookingId).isPositive().isNotZero().isNotNull();
       String firstname=response.then().extract().path("booking.firstname");
       assertThat(firstname).isEqualTo("Jim").isNotEmpty().isNotNull().isAlphabetic();
    }
}
