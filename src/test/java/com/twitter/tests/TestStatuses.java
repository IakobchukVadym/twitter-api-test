package com.twitter.tests;

import org.testng.annotations.Test;

import static Common.CommonMethods.getRandInt;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestStatuses extends BaseTest {

    //Generate random number of twits to return in test
    int numberOfTwits = getRandInt(1, 10);

    @Test
    public void verifyTwits() {
        given().
                log().params().
                spec(oauth1).
                param("count", numberOfTwits).
                when().
                get("/statuses/home_timeline.json").
                then().
                log().ifValidationFails().
                statusCode(200).
                body("id.size()", equalTo(numberOfTwits));
    }

    @Test
    public void veifyResponseTime() {
        given().
                log().params().
                spec(oauth1).
                param("count", 0).
                when().
                get("statuses/home_timeline.json").
                then().log().body().
                spec(timeSpec);
    }
}
