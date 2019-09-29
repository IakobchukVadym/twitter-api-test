package com.twitter.client;

import com.google.common.collect.ImmutableMap;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StatusClient extends BaseClient {

    private static final String STATUSES = "/statuses";
    private static final String CREATE_TWEET_ENDPOINT = STATUSES + "/update.json";
    private static final String DELETE_TWEET_ENDPOINT = STATUSES + "/destroy/{id}.json";
    private static final String RETWEET_ENDPOINT = STATUSES + "/retweet/{id}.json";
    private static final String UNTWEET_ENDPOINT = STATUSES + "/unretweet/{id}.json";
    private static final String HOME_TIMELINE_ENDPOINT = STATUSES + "/home_timeline.json";
    private static final String USER_TIMELINE_ENDPOINT = STATUSES + "/user_timeline.json";
    private static final String MENTION_TIMELINE_ENDPOINT = STATUSES + "/mentions_timeline.json";

    public Response postTwit(RequestSpecification spec) {
        return post(spec, CREATE_TWEET_ENDPOINT);
    }

    public Response deleteTwit(RequestSpecification spec, long id) {
        return post(spec, DELETE_TWEET_ENDPOINT, ImmutableMap.of("id", id));
    }

    public Response postRetwit(RequestSpecification spec, long id) {
        return post(spec, RETWEET_ENDPOINT, ImmutableMap.of("id", id));
    }

    public Response deleteRetwit(RequestSpecification spec, long id) {
        return post(spec, UNTWEET_ENDPOINT, ImmutableMap.of("id", id));
    }

    public Response getHomeTimeline(RequestSpecification requestSpecification) {
        return get(requestSpecification, HOME_TIMELINE_ENDPOINT);
    }

    public Response getUserTimeline(RequestSpecification requestSpecification) {
        return get(requestSpecification, USER_TIMELINE_ENDPOINT);
    }

    public Response getMentionTimeline(RequestSpecification requestSpecification) {
        return get(requestSpecification, MENTION_TIMELINE_ENDPOINT);
    }
}
