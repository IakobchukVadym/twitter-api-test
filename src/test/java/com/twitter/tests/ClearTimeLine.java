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

        statuses.forEach(twit -> {
            log.info("deleted twit id is " + twit.getId());
            log.info("deleted twit text is " + twit.getText());
            statusClient.deleteTwit(spec, twit.getId());
        });
    }
}
