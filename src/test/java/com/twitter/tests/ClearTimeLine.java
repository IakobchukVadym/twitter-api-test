package com.twitter.tests;

import com.twitter.request.RqBuilder;
import com.twitter.utils.BaseTest;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeSuite;
import twitter4j.Status;

import java.util.List;

@Log4j2
public class ClearTimeLine extends BaseTest {
    private static final int MAX_TWIT_COUNT = 200;

    @BeforeSuite
    public void before() {
        RequestSpecification spec = new RqBuilder()
                .withCount(MAX_TWIT_COUNT)
                .build();

        List<Status> statuses = statusService.retrieveUserTimeline(spec);

        statuses.forEach(satus -> {
            log.info("deleted tweet id is " + satus.getId());
            log.info("deleted tweet text is " + satus.getText());
            statusClient.deleteTwit(spec, satus.getId());
        });
    }
}
