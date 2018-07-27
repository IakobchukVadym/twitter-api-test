package com.twitter.tests;

import com.twitter.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static com.twitter.common.CommonMethods.getRandomString;

public class TestOauthErrors extends BaseTest {

    //Genereate fault key which will be used for Authentication
    private String faultKey = getRandomString(18);

    @Test(dataProvider = "Authentication")
    public void verifyResponseCodeForAuthentication(String consumerKey,
                                                    String consumerSecret,
                                                    String accessToken,
                                                    String accessSecret,
                                                    int statuseCode) {
        given().
                auth().oauth(consumerKey, consumerSecret, accessToken, accessSecret).
                when().
                get(baseURI + "statuses/home_timeline.json").
                then().
                log().ifValidationFails().
                statusCode(statuseCode);
    }

    @DataProvider(name = "Authentication")
    public Object[][] credentials() {
        return new Object[][]{
                {faultKey, faultKey, faultKey, faultKey, 401},
                {consumerKey, faultKey, faultKey, faultKey, 401},
                {consumerKey, consumerSecret, faultKey, faultKey, 401},
                {consumerKey, consumerSecret, accessToken, faultKey, 401},
                {consumerKey, consumerSecret, accessToken, accessSecret, 200}
        };
    }
}
