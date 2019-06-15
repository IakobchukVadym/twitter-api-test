package com.twitter;

import com.twitter.client.StatusClient;
import com.twitter.request.RequestBuilder;
import com.twitter.service.StatusService;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeSuite;
import twitter4j.Status;

import java.util.List;

@Log4j2
public abstract class TwitterApp {
    private static final int MAX_TWEET_COUNT = 200;
    protected StatusClient statusClient = new StatusClient();
    protected StatusService statusService = new StatusService();

    /* This will remove all previously created tweets */
    @BeforeSuite
    public void cleanUp() {
        RequestSpecification spec = new RequestBuilder()
                .withCount(MAX_TWEET_COUNT)
                .build();

        List<Status> statuses = statusService.retrieveUserTimeline(spec);

        statuses.forEach(satus -> {
            statusClient.deleteTweet(spec, satus.getId());
            log.info("deleted tweet id is " + satus.getId());
            log.info("deleted tweet text is " + satus.getText());
        });
    }
}
