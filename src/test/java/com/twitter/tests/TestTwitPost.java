package com.twitter.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestTwitPost extends BaseTest {

    String twitId;
    String textToPublish = "This is posted as a part of Automation com.twitter.test.TestStatuses on " + new Date().toString();


    @Test
    public void verifyTwitCreation(){
        Response response =
            given().
                    auth().
                    oauth(consumerKey,consumerSecret,accessToken,accessSecret).
                    queryParam("status", textToPublish).
                    when().
                    post("/statuses/update.json").
                    then().
                    log().body().
                    statusCode(200).
                    body("text", equalTo(textToPublish)).
                    extract().response();

            twitId = response.path("id_str");
    }

    @Test(dependsOnMethods = "verifyTwitCreation")
    public void verifyDuplicatePrevention(){
                given().
                        auth().
                        oauth(consumerKey,consumerSecret,accessToken,accessSecret).
                        queryParam("status", textToPublish).
                        when().
                        post("/statuses/update.json").
                        then().
                        log().body().
                        statusCode(403).
                        root("errors").
                        body("code", hasItem(187)).
                        body("message", hasItem("Status is a duplicate."));
    }

    @Test(dependsOnMethods = "verifyDuplicatePrevention")
    public void verifyTwitDeletion(){
                given().
                        auth().
                        oauth(consumerKey,consumerSecret,accessToken,accessSecret).
                        when().
                        post("/statuses/destroy/"+ twitId + ".json").
                        then().
                        log().all().
                        statusCode(200).
                        body("text", equalTo(textToPublish)).
                        body("id_str", equalTo(twitId));
    }
}
