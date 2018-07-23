package com.twitter.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;


import java.util.ArrayList;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

public class BaseTest {
    String consumerKey = "HQqWDhlNh48NJXGBVkLNsZILJ";
    String consumerSecret = "BdhxsE5CGFCyS9XVF2FiBK6ZC6Wnz5ytJfyMvs1R5EpLERyAMc";
    String accessToken = "1020943670764363778-yjVWe2Q3twis8snUb32mVSnU318iTI";
    String accessSecret= "PzxjtUyyFz9BctYZTnCEO5sFqI58CxQ96XKccwP2LxFkH";

    ArrayList<String> oauthValues = new ArrayList<String>() {{
        add(consumerKey);
        add(consumerSecret);
        add(accessToken);
        add(accessSecret);
    }};

    protected ResponseSpecification timeSpec = new ResponseSpecBuilder().
            expectResponseTime(lessThanOrEqualTo(3000L), MILLISECONDS).
            build();
    protected ResponseSpecification responseCode200 = new ResponseSpecBuilder().expectStatusCode(200).build();
    protected RequestSpecification oauth = new RequestSpecBuilder().setAuth().build()s

    private String baseURI = "https://api.twitter.com/1.1/";


    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = baseURI;
    }
}
