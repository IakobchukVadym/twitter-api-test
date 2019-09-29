package com.twitter.tests;

import com.twitter.request.RqBuilder;
import com.twitter.utils.BaseTest;
import com.twitter.utils.assertions.TwitterSoftAssertions;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import twitter4j.Status;

import java.util.List;
import java.util.stream.Collectors;

import static com.twitter.request.ScreenName.ARSENAL;
import static com.twitter.request.ScreenName.GUNNERSAURUS;
import static com.twitter.utils.CommonMethods.getRandInt;
import static org.assertj.core.api.Assertions.assertThat;

public class RetweetTest extends BaseTest {

    @Test
    public void verifyRetweet() {
        Status originalStatus = getStatus(GUNNERSAURUS.getName());

        RequestSpecification spec = new RqBuilder().build();
        Status status = statusService.createRetweet(spec, originalStatus.getId());

        TwitterSoftAssertions softAssertions = new TwitterSoftAssertions();
        softAssertions.assertThat(status)
                .hasText("RT @Gunnersaurus: " + originalStatus.getText())
                .isRetweet(true)
                .hasRetwetedStaus(originalStatus);
        softAssertions.assertAll();
    }

    @Test
    public void verifyUntweet() {
        Status originalStatus = getStatus(ARSENAL.getName());

        RequestSpecification spec = new RqBuilder().build();
        statusService.createRetweet(spec, originalStatus.getId());
        Status unRetweetedStatus = statusService.removeRetweet(spec, originalStatus.getId());

        assertThat(unRetweetedStatus)
                .as("Deleted retweet should be the same as original")
                .isEqualByComparingTo(originalStatus);
    }

    private Status getStatus(String screenName) {
        RequestSpecification spec = new RqBuilder().withCount(100)
                .withScreenName(screenName)
                .build();

        List<Status> statuses = statusService.retrieveUserTimeline(spec);
        List<Status> statusesWithotRetweets = statuses.stream()
                .filter(status -> !status.isRetweet())
                .collect(Collectors.toList());

        return statusesWithotRetweets.get(getRandInt(statusesWithotRetweets.size() - 1));
    }
}
