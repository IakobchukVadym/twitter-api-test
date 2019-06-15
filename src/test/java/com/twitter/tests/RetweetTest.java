package com.twitter.tests;

import com.twitter.TwitterApp;
import com.twitter.request.RequestBuilder;
import com.twitter.request.ScreenName;
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

public class RetweetTest extends TwitterApp {

    @Test
    public void verifyRetweet() {
        Status originalStatus = getStatus(GUNNERSAURUS);

        RequestSpecification spec = new RequestBuilder().build();
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
        Status originalStatus = getStatus(ARSENAL);

        RequestSpecification spec = new RequestBuilder().build();
        statusService.createRetweet(spec, originalStatus.getId());
        Status unRetweetedStatus = statusService.removeRetweet(spec, originalStatus.getId());

        assertThat(unRetweetedStatus)
                .as("Deleted retweet should be the same as original")
                .isEqualByComparingTo(originalStatus);
    }

    private Status getStatus(ScreenName screenName) {
        RequestSpecification spec = new RequestBuilder().withCount(100)
                .withScreenName(screenName.getName())
                .build();

        List<Status> statuses = statusService.retrieveUserTimeline(spec);
        List<Status> statusesWithotRetweets = statuses.stream()
                .filter(status -> !status.isRetweet())
                .collect(Collectors.toList());

        return statusesWithotRetweets.get(getRandInt(statusesWithotRetweets.size() - 1));
    }
}
