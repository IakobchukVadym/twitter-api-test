package com.twitter.Common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.oauth;

public class BaseTest {
    protected String consumerKey = "HQqWDhlNh48NJXGBVkLNsZILJ";
    protected String consumerSecret = "BdhxsE5CGFCyS9XVF2FiBK6ZC6Wnz5ytJfyMvs1R5EpLERyAMc";
    protected String accessToken = "1020943670764363778-yjVWe2Q3twis8snUb32mVSnU318iTI";
    protected String accessSecret = "PzxjtUyyFz9BctYZTnCEO5sFqI58CxQ96XKccwP2LxFkH";

    protected String baseURI = "https://api.twitter.com/1.1/";

    protected RequestSpecification oauth1 = new RequestSpecBuilder().setBaseUri(baseURI).
            setAuth(oauth(consumerKey, consumerSecret, accessToken, accessSecret)).build();
}
