package org.example.ex02_RestAssuredBasics.GET;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequest_BDDStyle_UsingTestNG {
    String pincode;
    @Test(priority = 1)
    public void test_GET_Req_Positive(){
        pincode="500032";
        given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
        .when()
                .log().all().get()
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 2)
    public void test_GET_Req_Negitive(){
        pincode="-1";
        given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
        .when()
                .log().all().get()
       .then()
                .statusCode(404)
                .log().all();
    }
}
