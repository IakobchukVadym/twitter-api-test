package com.twitter.service;

import com.twitter.ResponseWrapper;
import com.twitter.clients.StatusClient;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import twitter4j.Status;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.twitter.ResponseWrapper.createStatus;
import static com.twitter.utils.assertions.AssertResponse.assertResponseCode;

public class StatusService {
    private StatusClient statusClient = new StatusClient();

    public Status createTwit(RequestSpecification spec) {
        Response response = statusClient.postTwit(spec);
        assertResponseCode(response, 200);

        return getStatus(response);
    }

    public Status removeTwit(RequestSpecification spec, long id) {
        Response response = statusClient.deleteTwit(spec, id);
        assertResponseCode(response, 200);

        return getStatus(response);
    }

    public Status createRetwit(RequestSpecification spec, long id) {
        Response response = statusClient.postRetwit(spec, id);
        assertResponseCode(response, 200);

        return getStatus(response);
    }

    public Status removeRetwit(RequestSpecification spec, long id) {
        Response response = statusClient.deleteRetwit(spec, id);
        assertResponseCode(response, 200);

        return getStatus(response);
    }

    public List<Status> retrieveHomeTimeline(RequestSpecification spec) {
        Response response = statusClient.getHomeTimeline(spec);
        assertResponseCode(response, 200);

        return getStatuses(response);
    }

    public List<Status> retrieveUserTimeline(RequestSpecification spec) {
        Response response = statusClient.getUserTimeline(spec);
        assertResponseCode(response, 200);

        return getStatuses(response);
    }

    public List<Status> retrieveMentionTimeline(RequestSpecification spec) {
        Response response = statusClient.getMentionTimeline(spec);
        assertResponseCode(response, 200);

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
}
