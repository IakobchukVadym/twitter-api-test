package com.twitter.request;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import static com.twitter.properties.AppProperties.ACCESS_SECRET;
import static com.twitter.properties.AppProperties.ACCESS_TOKEN;
import static com.twitter.properties.AppProperties.BASE_URL;
import static com.twitter.properties.AppProperties.CONSUMER_KEY;
import static com.twitter.properties.AppProperties.CONSUMER_SECRET;
import static io.restassured.RestAssured.given;

@Log4j2
public class RequestBuilder {

    private RequestSpecification spec = given()
            .auth()
            .oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_SECRET)
            .baseUri(BASE_URL)
            .filter(new AllureRestAssured());

    public RequestBuilder withCount(int number) {
        spec.param("count", number);
        return this;
    }

    public RequestBuilder withScreenName(String screenName) {
        spec.param("screen_name", screenName);
        return this;
    }

    public RequestBuilder withStatus(String status) {
        spec.queryParam("status", status);
        log.info("Adding tweet text: " + status);
        return this;
    }

    public RequestBuilder withExcludeReplies(boolean exclude) {
        spec.queryParam("exclude_replies", exclude);
        return this;
    }

    public RequestSpecification build() {
        return spec;
    }
}
