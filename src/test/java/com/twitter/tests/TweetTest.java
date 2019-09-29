package com.twitter.tests;

import com.twitter.model.response.exception.TwitterError;
import com.twitter.request.RqBuilder;
import com.twitter.utils.BaseTest;
import com.twitter.utils.assertions.TwitterSoftAssertions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import twitter4j.Status;

import java.util.Date;

import static com.twitter.ResponseWrapper.createTwitterError;
import static com.twitter.utils.assertions.AssertResponse.assertResponseCode;
import static com.twitter.utils.assertions.StatusAssert.assertThat;

public class TweetTest extends BaseTest {

    @Test
    public void verifyTweetCreation() {
        String postText = "verifyTweetCreation test post " + new Date().toString();
        RequestSpecification spec = new RqBuilder()
                .withStatus(postText)
                .build();

        Status status = statusService.createTweet(spec);

        assertThat(status).hasText(postText);
    }

    @Test
    public void verifyDuplicatePrevention() {
        RequestSpecification spec = new RqBuilder()
                .withStatus("verifyDuplicatePrevention test post " + new Date().toString())
                .build();

        statusClient.postTwit(spec);
        Response response = statusClient.postTwit(spec);
        assertResponseCode(response, 403);

        TwitterError twitterError = createTwitterError(response);

        TwitterSoftAssertions softAssertions = new TwitterSoftAssertions();
        softAssertions.assertThat(twitterError.getErrors().size())
                .isEqualTo(1);

        softAssertions.assertThat(twitterError.getErrors().get(0))
                .hasCode(187)
                .hasMessage("Status is a duplicate.");
        softAssertions.assertAll();
    }

    @Test
    public void verifyTweetDeletion() {
        RequestSpecification spec = new RqBuilder()
                .withStatus("verifyTweetDeletion test post " + new Date().toString())
                .build();

        Status statusCreated = statusService.createTweet(spec);

        Status statusDeleted = statusService.removeTweet(spec, statusCreated.getId());
        Assertions.assertThat(statusDeleted)
                .isEqualByComparingTo(statusCreated);
    }
}
