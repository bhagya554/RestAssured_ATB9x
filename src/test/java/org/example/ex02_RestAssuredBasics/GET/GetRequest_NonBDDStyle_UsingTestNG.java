package org.example.ex02_RestAssuredBasics.GET;
//Non-BDD style - we use Rest Assured interfaces

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequest_NonBDDStyle_UsingTestNG {
    String pincode;
    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse vRes;
    @Description("Verify the GET Request with positive input")
    @Test(priority = 1)
    public void test_GET_Req_NonBDDStyle_Positive() {
        pincode = "500032";
        reqSpec = given();
        reqSpec.baseUri("https://api.zippopotam.us");
        reqSpec.basePath("/IN/" + pincode);

        res = reqSpec.when().log().all().get();

        vRes=res.then();
        vRes.statusCode(200);
        vRes.log().all();
    }
    @Description("Verify the GET Request with negative input: -1")
    @Test(priority = 1)
    public void test_GET_Req_NonBDDStyle_Negative1() {
        pincode = "-1";
        reqSpec = given();
        reqSpec.baseUri("https://api.zippopotam.us");
        reqSpec.basePath("/IN/" + pincode);

        res = reqSpec.when().log().all().get();

        vRes=res.then();
        vRes.statusCode(404);
        vRes.log().all();
    }
    @Description("Verify the GET Request with negative input: hello")
    @Test(priority = 1)
    public void test_GET_Req_NonBDDStyle_Negative2() {
        pincode = "hello";
        reqSpec = given();
        reqSpec.baseUri("https://api.zippopotam.us");
        reqSpec.basePath("/IN/" + pincode);

        res = reqSpec.when().log().all().get();

        vRes=res.then();
        vRes.statusCode(404);
        vRes.log().all();
    }

}
