package com.twitter.tests;

import com.twitter.request.RqBuilder;
import com.twitter.utils.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import twitter4j.Status;

import java.util.List;

import static com.twitter.request.ScreenName.ARSENAL;
import static com.twitter.utils.CommonMethods.getRandInt;
import static com.twitter.utils.assertions.ResponseAssert.assertResponseCode;
import static org.assertj.core.api.Assertions.assertThat;

public class TimelineTest extends BaseTest {

    @Test
    public void verifyHomeTimeline() {
        int randInt = getRandInt(100);
        RequestSpecification spec = new RqBuilder()
                .withCount(randInt)
                .build();

        List<Status> statuses = statusService.retrieveHomeTimeline(spec);

        assertThat(statuses.size())
                .as("Number of tweets should be the same as requested")
                .isEqualTo(randInt);
    }

    @Test
    public void verifyUserTimeline() {
        int randInt = getRandInt(100);
        RequestSpecification spec = new RqBuilder()
                .withCount(randInt)
                .withScreenName(ARSENAL.getName())
                .build();

        List<Status> statuses = statusService.retrieveUserTimeline(spec);

        assertThat(statuses.size())
                .as("Number of tweets should be the same as requested")
                .isEqualTo(randInt);
    }

    @Test
    public void verifyResponseTime() {
        RequestSpecification spec = new RqBuilder()
                .withCount(1)
                .build();

        Response response = statusClient.getHomeTimeline(spec);
        assertResponseCode(response, 200);

        assertThat(response.getTime())
                .as("TimelineResponse time should be less than 3 seconds")
                .isLessThan(3000L);
    }
}
