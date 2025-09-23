package org.example.ex04_Assertions;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Ex2_TestNG_Assertions {

    @Test
    public void hardAssert(){
        System.out.println("Start of program");
        boolean isMale=true;
        Assert.assertTrue(isMale);
        System.out.println("End of program");
    }

    @Test
    public void softAssert(){
        System.out.println("Start of program");
        boolean isMale=false;
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isMale);
        System.out.println("End of program");
        softAssert.assertAll();
    }

}
