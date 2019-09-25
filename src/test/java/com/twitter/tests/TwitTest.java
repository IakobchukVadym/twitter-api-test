package com.twitter.tests;

import com.twitter.model.exception.TwitterError;
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

public class TwitTest extends BaseTest {

    @Test
    public void verifyTwitCreation() {
        String postText = "verifyTwitCreation test post" + new Date().toString();
        RequestSpecification spec = new RqBuilder()
                .withStatus(postText)
                .build();

        Status status = statusService.createTwit(spec);

        Assertions.assertThat(status.getText())
                .as("Created twit message should be the same as provided")
                .isEqualTo(postText);
    }

    @Test
    public void verifyDuplicatePrevention() {
        RequestSpecification spec = new RqBuilder()
                .withStatus("verifyDuplicatePrevention test post" + new Date().toString())
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
    public void verifyTwitDeletion() {
        RequestSpecification spec = new RqBuilder()
                .withStatus("verifyTwitDeletion test post" + new Date().toString())
                .build();

        Status statusCreated = statusService.createTwit(spec);
        long id = statusCreated.getId();

        Status statusDeleted = statusService.removeTwit(spec, id);
        Assertions.assertThat(statusDeleted)
                .isEqualByComparingTo(statusCreated);
    }
}
