package org.example.ex02_RestAssuredBasics.GET;
import static io.restassured.RestAssured.*;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
//https://restful-booker.herokuapp.com/booking/1
public class GetRequestPractice_BDDStyle {
    String id;
    @Description("Positive Input: 1")
    @Test
    public void test_Get_Positive(){
        id="1";
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/" + id)
        .when()
                .log().all()
                .get()
        .then()
                .statusCode(200)
                .log().all();
    }
    @Description("Negative Input: 0000")
    @Test
    public void test_Get_Negative1(){
        id="0000";
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/" + id)
        .when()
                .log().all()
                .get()
        .then()
                .statusCode(404)
                .log().all();
    }
}
