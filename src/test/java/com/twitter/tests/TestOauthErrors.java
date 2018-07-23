package com.twitter.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestOauthErrors extends BaseTest {

    int numberOfTwits = 5;

    @Test(dataProvider = "Authentication")
    public void verifyTwits(String a, String b, String c, String d, int code) {
        given().
                auth().oauth(a, b, c, d).
                param("count", numberOfTwits).
                when().
                get(baseURI +"statuses/home_timeline.json").
                then().
                log().ifValidationFails().
                statusCode(code);
    }

    @DataProvider(name = "Authentication")
    public Object[][] credentials() {
        return new Object[][]{
                {consumerKey, "123123243", "123123243", "123123243", 401},
                {consumerKey, consumerSecret, "2311251235", "123123243", 401},
                {consumerKey, consumerSecret, accessToken, "123123243", 401},
                {consumerKey, consumerSecret, accessToken, accessSecret, 200}
        };
    }
}
