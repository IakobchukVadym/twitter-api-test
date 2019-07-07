package com.twitter.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.twitter.utils.properties.AppProperties.*;
import static io.restassured.RestAssured.oauth;

public abstract class BaseTest {
  protected static String password = getSecurityPassword();
  protected static String consumerKey = Decryptor.decrypt(getConsumerKey(), password);
  protected static String consumerSecret = Decryptor.decrypt(getConsumerSecret(), password);
  protected static String accessToken = Decryptor.decrypt(getAccessToken(), password);
  protected static String accessSecret = Decryptor.decrypt(getAccessSecret(), password);
  protected static String baseUrl = getUrl();

  protected static RequestSpecification oauth1 =
     new RequestSpecBuilder()
        .setBaseUri(baseUrl)
        .setAuth(oauth(consumerKey, consumerSecret, accessToken, accessSecret))
        .build();
}
