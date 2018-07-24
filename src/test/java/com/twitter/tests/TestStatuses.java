package com.twitter.tests;

import com.twitter.Common.BaseTest;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static main.java.Common.CommonMethods.getRandInt;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

public class TestStatuses extends BaseTest {

    //Generate random number of twits to return in test
    int numberOfTwits = getRandInt(1, 10);

    @Test
    public void verifyTimeline() {
        given().
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
                spec(oauth1).
                param("count", 1).
                when().
                get("statuses/home_timeline.json").
                then().
                time(lessThanOrEqualTo(3000L), MILLISECONDS);
    }
}
