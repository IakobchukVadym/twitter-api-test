package com.twitter.service;

import com.twitter.client.StatusClient;
import com.twitter.model.response.error.TwitterError;
import com.twitter.utils.ResponseWrapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import twitter4j.Status;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.twitter.utils.ResponseWrapper.createStatus;
import static com.twitter.utils.ResponseWrapper.createTwitterError;
import static com.twitter.utils.assertions.ResponseAssert.assertResponseCode;

@Log4j2
public class StatusService {
    private StatusClient statusClient = new StatusClient();

    public Status createTweet(RequestSpecification spec) {
        Response response = statusClient.postTweet(spec);
        assertAndLogIfError(response);

        return getStatus(response);
    }

    public Status removeTweet(RequestSpecification spec, long id) {
        Response response = statusClient.deleteTweet(spec, id);
        assertAndLogIfError(response);
        return getStatus(response);
    }

    public Status createRetweet(RequestSpecification spec, long id) {
        Response response = statusClient.postRetweet(spec, id);
        assertAndLogIfError(response);

        return getStatus(response);
    }

    public Status removeRetweet(RequestSpecification spec, long id) {
        Response response = statusClient.deleteRetweet(spec, id);
        assertAndLogIfError(response);

        return getStatus(response);
    }

    public List<Status> retrieveHomeTimeline(RequestSpecification spec) {
        Response response = statusClient.getHomeTimeline(spec);
        assertAndLogIfError(response);

        return getStatuses(response);
    }

    public List<Status> retrieveUserTimeline(RequestSpecification spec) {
        Response response = statusClient.getUserTimeline(spec);
        assertAndLogIfError(response);

        return getStatuses(response);
    }

    public List<Status> retrieveMentionTimeline(RequestSpecification spec) {
        Response response = statusClient.getMentionTimeline(spec);
        assertAndLogIfError(response);

        return getStatuses(response);
    }

    private Status getStatus(Response response) {
        String json = response.getBody().asString();

        return createStatus(json);
    }

    private List<Status> getStatuses(Response response) {
        List<Map<Object, Object>> jsons = response.getBody().jsonPath().getList("$");

        return jsons.stream()
                .map(ResponseWrapper::createStatus)
                .collect(Collectors.toList());
    }

    private void assertAndLogIfError(Response response) {
        int expectedCode = 200;
        if (response.getStatusCode() != expectedCode) {
            TwitterError twitterError = createTwitterError(response);
            log.error("Twitter has sent following errors " + twitterError);
        }
        assertResponseCode(response, expectedCode);
    }
}
