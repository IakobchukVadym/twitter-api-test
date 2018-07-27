package com.twitter.tests;

import com.twitter.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class TestRetwit extends BaseTest {

    private String twitId;

    @Test
    public void getTwitId() {
        twitId =
                given().
                        spec(oauth1).
                        param("screen_name", "Gunnersaurus").
                        param("count", 1).
                        when().
                        get("/statuses/user_timeline.json").
                        then().
                        statusCode(200).
                        extract().path("id_str[0]");
    }

    @Test(dataProvider = "RetwitOptions", dependsOnMethods = "getTwitId")
    public void verifyRetwit(int statuscode) {
        given().
                spec(oauth1).
                when().
                post("statuses/retweet/" + twitId + ".json").
                then().
                log().ifValidationFails().
                statusCode(statuscode);
    }

    @Test(dependsOnMethods = "verifyRetwit")
    public void verifyDeletionOfRetwit() {
        given().
                spec(oauth1).
                when().
                post("statuses/unretweet/" + twitId + ".json").
                then().
                //log().ifValidationFails().
                log().ifValidationFails().
                statusCode(200);
    }

    @DataProvider(name = "RetwitOptions")
    public Object[][] codes() {
        return new Object[][]{
                {200},
                {403}
        };
    }
}
