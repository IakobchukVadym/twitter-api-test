package com.twitter.tests;

import com.twitter.request.RqBuilder;
import com.twitter.utils.BaseTest;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import twitter4j.Status;

import java.util.List;

import static com.twitter.utils.CommonMethods.getRandInt;
import static org.assertj.core.api.Assertions.assertThat;

public class RetwitTest extends BaseTest {

    @Test
    public void verifyRetwit() {
        Status originalStatus = getStatus("Gunnersaurus");

        RequestSpecification spec = new RqBuilder().build();
        Status status = statusService.createRetwit(spec, originalStatus.getId());

        assertThat(status.getText())
                .as("Created retwit text be the same as requested")
                .isEqualTo("RT @Gunnersaurus: " + originalStatus.getText());

        assertThat(status.isRetweet())
                .as("Retweet status should be true")
                .isTrue();
    }

    @Test
    public void verifyUntwit() {
        Status originalStatus = getStatus("Arsenal");

        RequestSpecification spec = new RqBuilder().build();
        statusService.createRetwit(spec, originalStatus.getId());
        Status unRetwitedStatus = statusService.removeRetwit(spec, originalStatus.getId());

        assertThat(unRetwitedStatus)
                .as("Deleted retwit should be the same as original")
                .isEqualByComparingTo(originalStatus);
    }

    private Status getStatus(String screenName) {
        int count = 100;
        RequestSpecification spec = new RqBuilder().withCount(count)
                .withScreenName(screenName)
                .build();

        List<Status> statuses = statusService.retrieveUserTimeline(spec);
        return statuses.get(getRandInt(count));
    }
}
