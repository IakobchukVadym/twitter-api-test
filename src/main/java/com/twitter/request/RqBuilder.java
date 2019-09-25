package com.twitter.request;

import com.twitter.utils.Decryptor;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import static com.twitter.properties.AppProperties.getAccessSecret;
import static com.twitter.properties.AppProperties.getAccessToken;
import static com.twitter.properties.AppProperties.getConsumerKey;
import static com.twitter.properties.AppProperties.getConsumerSecret;
import static com.twitter.properties.AppProperties.getSecurityPassword;
import static com.twitter.properties.AppProperties.getUrl;
import static io.restassured.RestAssured.given;

public class RqBuilder {
    private static final String PASSWORD = getSecurityPassword();
    public static final String CONSUMER_KEY = Decryptor.decrypt(getConsumerKey(), PASSWORD);
    public static final String CONSUMER_SECRET = Decryptor.decrypt(getConsumerSecret(), PASSWORD);
    public static final String ACCESS_TOKEN = Decryptor.decrypt(getAccessToken(), PASSWORD);
    public static final String ACCESS_SECRET = Decryptor.decrypt(getAccessSecret(), PASSWORD);

    private RequestSpecification spec = given()
            .auth()
            .oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_SECRET)
            .baseUri(getUrl())
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
