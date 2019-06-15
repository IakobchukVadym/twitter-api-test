package com.twitter.tests;

import com.twitter.TwitterApp;
import com.twitter.model.response.error.TwitterError;
import com.twitter.request.RequestBuilder;
import com.twitter.utils.assertions.TwitterSoftAssertions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import twitter4j.Status;

import java.time.LocalDateTime;

import static com.twitter.utils.ResponseWrapper.createTwitterError;
import static com.twitter.utils.assertions.ResponseAssert.assertResponseCode;
import static com.twitter.utils.assertions.StatusAssert.assertThat;

public class TweetTest extends TwitterApp {

    @Test
    public void verifyTweetCreation() {
        String postText = "verifyTweetCreation test post " + LocalDateTime.now();
        RequestSpecification spec = new RequestBuilder()
                .withStatus(postText)
                .build();

        Status status = statusService.createTweet(spec);

        assertThat(status).hasText(postText);
    }

    @Test
    public void verifyTweetDuplicatePrevention() {
        RequestSpecification spec = new RequestBuilder()
                .withStatus("verifyDuplicatePrevention test post " + LocalDateTime.now())
                .build();

        statusClient.postTweet(spec);
        Response response = statusClient.postTweet(spec);
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
        RequestSpecification spec = new RequestBuilder()
                .withStatus("verifyTweetDeletion test post " + LocalDateTime.now())
                .build();

        Status statusCreated = statusService.createTweet(spec);

        Status statusDeleted = statusService.removeTweet(spec, statusCreated.getId());
        Assertions.assertThat(statusDeleted)
                .isEqualByComparingTo(statusCreated);
    }
}
