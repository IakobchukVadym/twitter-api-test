package com.twitter.tests;

import com.twitter.TwitterApp;
import com.twitter.model.response.error.TwitterError;
import com.twitter.utils.assertions.TwitterSoftAssertions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.twitter.properties.AppProperties.ACCESS_TOKEN;
import static com.twitter.properties.AppProperties.BASE_URL;
import static com.twitter.properties.AppProperties.CONSUMER_KEY;
import static com.twitter.properties.AppProperties.CONSUMER_SECRET;
import static com.twitter.utils.CommonMethods.getRandomString;
import static com.twitter.utils.ResponseWrapper.createTwitterError;
import static com.twitter.utils.assertions.ResponseAssert.assertResponseCode;
import static io.restassured.RestAssured.given;

public class OauthErrorsTest extends TwitterApp {

    private static final int TOKEN_ERROR_CODE = 89;
    private static final int AUTHENTICATE_ERROR_CODE = 32;
    private static final String TOKEN_ERROR_MESSAGE = "Invalid or expired token.";
    private static final String AUTHENTICATE_ERROR_MESSAGE = "Could not authenticate you.";
    private static final String FAULT_KEY = getRandomString(18);

    @Test(dataProvider = "Authentication")
    public void verifyResponseCodeForAuthentication(String consumerKey, String consumerSecret, String accessToken,
                                                    String accessSecret, int statusCode, int errorCode,
                                                    String errorMessage) {
        RequestSpecification spec = given()
                .auth()
                .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .baseUri(BASE_URL);

        Response response = statusClient.getHomeTimeline(spec);
        assertResponseCode(response, statusCode);

        TwitterError twitterError = createTwitterError(response);

        //Optional or add new assertion
        TwitterSoftAssertions softAssertions = new TwitterSoftAssertions();

        softAssertions.assertThat(twitterError.getErrors())
                .as("Errors list should not be empty")
                .isNotEmpty();

        softAssertions.assertThat(twitterError.getErrors().get(0))
                .hasCode(errorCode)
                .hasMessage(errorMessage);
        softAssertions.assertAll();
    }

    @DataProvider(name = "Authentication", parallel = true)
    public Object[][] credentials() {
        return new Object[][]{
                {FAULT_KEY, FAULT_KEY, FAULT_KEY, FAULT_KEY, 401, TOKEN_ERROR_CODE, TOKEN_ERROR_MESSAGE},
                {CONSUMER_KEY, FAULT_KEY, FAULT_KEY, FAULT_KEY, 401, TOKEN_ERROR_CODE, TOKEN_ERROR_MESSAGE},
                {CONSUMER_KEY, CONSUMER_SECRET, FAULT_KEY, FAULT_KEY, 401, TOKEN_ERROR_CODE, TOKEN_ERROR_MESSAGE},
                {CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, FAULT_KEY, 401, AUTHENTICATE_ERROR_CODE, AUTHENTICATE_ERROR_MESSAGE}
        };
    }
}
