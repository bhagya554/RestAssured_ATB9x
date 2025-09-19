package org.example.ex02_RestAssuredBasics.POST;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
/*
url: https://restful-booker.herokuapp.com/auth
Header: Content-Type - application/json
Body:
{
    "username" : "admin",
    "password" : "password123"
}
 */
public class PostRequest_BDDStyle {
    String payload;
@Description("Restful Booker-Post Request-Create token : Positive Testcase")
@Test
public void test_postReq_Positive(){
    payload="{\n" +
            "    \"username\" : \"admin\",\n" +
            "    \"password\" : \"password123\"\n" +
            "}";
    given()
            .baseUri("https://restful-booker.herokuapp.com")
            .basePath("/auth")
            .body(payload)
            .header("Content-Type","application/json")
    .when()
            .log().all().post()
    .then()
            .statusCode(200).log().all();
}

    @Description("Restful Booker-Post Request-Create token : Negative Testcase - Invalid credentials")
    @Test
    public void test_postReq_Negative(){
        payload="{\n" +
                "    \"username\" : \"admin123\",\n" +
                "    \"password\" : \"password12\"\n" +
                "}";
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .body(payload)
                .header("Content-Type","application/json")
        .when()
                .log().all().post()
        .then()
                .statusCode(200).log().all();
    }
}
