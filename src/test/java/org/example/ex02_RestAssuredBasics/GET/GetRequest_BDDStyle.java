package org.example.ex02_RestAssuredBasics.GET;
import static io.restassured.RestAssured.*;
public class GetRequest_BDDStyle {
    public static void main(String[] args) {
        String pincode="500032";
        given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
        .when()
                .log().all().get()
        .then()
                .statusCode(200)
                .log().all();

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
