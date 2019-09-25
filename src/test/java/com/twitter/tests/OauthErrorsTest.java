package com.twitter.tests;

import com.twitter.model.exception.TwitterError;
import com.twitter.utils.BaseTest;
import com.twitter.utils.assertions.TwitterSoftAssertions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.twitter.ResponseWrapper.createTwitterError;
import static com.twitter.properties.AppProperties.getUrl;
import static com.twitter.request.RqBuilder.ACCESS_TOKEN;
import static com.twitter.request.RqBuilder.CONSUMER_KEY;
import static com.twitter.request.RqBuilder.CONSUMER_SECRET;
import static com.twitter.utils.CommonMethods.getRandomString;
import static com.twitter.utils.assertions.AssertResponse.assertResponseCode;
import static io.restassured.RestAssured.given;

public class OauthErrorsTest extends BaseTest {

    private static final int TOKEN_ERROR_CODE = 89;
    private static final int AUTHENTICATE_ERROR_CODE = 32;
    private static final String TOKEN_ERROR_MESSAGE = "Invalid or expired token.";
    private static final String AUTHENTICATE_ERROR_MESSAGE = "Could not authenticate you.";
    private static final String faultKey = getRandomString(18);

    @Test(dataProvider = "Authentication")
    public void verifyResponseCodeForAuthentication(String consumerKey, String consumerSecret, String accessToken,
                                                    String accessSecret, int statusCode, int errorCode,
                                                    String errorMessage) {
        RequestSpecification spec = given()
                .auth()
                .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .baseUri(getUrl());

        Response response = statusClient.getHomeTimeline(spec);
        assertResponseCode(response, statusCode);

        TwitterError twitterError = createTwitterError(response);

        TwitterSoftAssertions softAssertions = new TwitterSoftAssertions();
        softAssertions.assertThat(twitterError.getErrors().get(0))
                .hasCode(errorCode)
                .hasMessage(errorMessage);
        softAssertions.assertAll();
    }

    @DataProvider(name = "Authentication", parallel = true)
    public Object[][] credentials() {
        return new Object[][]{
                {faultKey, faultKey, faultKey, faultKey, 401, TOKEN_ERROR_CODE, TOKEN_ERROR_MESSAGE},
                {CONSUMER_KEY, faultKey, faultKey, faultKey, 401, TOKEN_ERROR_CODE, TOKEN_ERROR_MESSAGE},
                {CONSUMER_KEY, CONSUMER_SECRET, faultKey, faultKey, 401, TOKEN_ERROR_CODE, TOKEN_ERROR_MESSAGE},
                {CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, faultKey, 401, AUTHENTICATE_ERROR_CODE, AUTHENTICATE_ERROR_MESSAGE}
        };
    }
}
