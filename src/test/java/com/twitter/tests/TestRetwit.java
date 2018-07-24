package com.twitter.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

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

        System.out.println(twitId);

    }

    @Test
    public void verifyRetwit() {
        given().
                log().params().
                spec(oauth1).
                when().
                post("statuses/retweet/" + twitId + ".json").
                then().log().body().
                statusCode(200);
    }

//    @Test
//    public void verifyRetwitDiplicatePreve() {
//        given().
//                log().params().
//                spec(oauth1).
//                when().
//                post("statuses/retweet/" + twitId + ".json").
//                then().log().body().
//                statusCode(200);
//    }

//    @Test
//    public void verifyUntwit() {
//        given().
//                log().params().
//                spec(oauth1).
//                when().
//                post("statuses/unretweet/" + twitId + ".json").
//                then().log().body().
//                statusCode(200);
//    }

    @DataProvider(name = "Retwit")
    public Object[][] details() {
        return new Object[][]{
                {"errors.code", 327, 200},
                { 403}
        };
    }
}
