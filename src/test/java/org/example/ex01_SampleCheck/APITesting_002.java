package org.example.ex01_SampleCheck;
import static io.restassured.RestAssured.*;

public class APITesting_002 {
    public static void main(String[] args) {
        //Gherkin Syntax
        //Given() -> Prerequisite - url, auth, header,body..
        //When() -> HTTP Methods - GET/POST/PUT/DELETE/HEAD/OPTION
        //Then() -> Validation - 200 OK,
        /*
        Full URL: https://api.zippopotam.us/IN/500032
        Base URI : https://api.zippopotam.us
        Base Path: /IN/500032
         */
        given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/500032")
        .when()
                .get()
        .then()
                .statusCode(200)
                .log().all();
    }
}
