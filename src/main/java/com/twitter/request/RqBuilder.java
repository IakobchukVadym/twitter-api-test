package com.twitter.request;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import static com.twitter.properties.AppProperties.ACCESS_SECRET;
import static com.twitter.properties.AppProperties.ACCESS_TOKEN;
import static com.twitter.properties.AppProperties.BASE_URL;
import static com.twitter.properties.AppProperties.CONSUMER_KEY;
import static com.twitter.properties.AppProperties.CONSUMER_SECRET;
import static io.restassured.RestAssured.given;

public class RqBuilder {

    private RequestSpecification spec = given()
            .auth()
            .oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_SECRET)
            .baseUri(BASE_URL)
            .filter(new AllureRestAssured());

    public RqBuilder withCount(int number) {
        spec.param("count", number);
        return this;
    }

    public RqBuilder withScreenName(String screenName) {
        spec.param("screen_name", screenName);
        return this;
    }

    public RqBuilder withStatus(String status) {
        spec.queryParam("status", status);
        return this;
    }

    public RqBuilder withExcludeReplies(boolean exclude) {
        spec.queryParam("exclude_replies", exclude);
        return this;
    }

    public RequestSpecification build() {
        return spec;
    }
}
