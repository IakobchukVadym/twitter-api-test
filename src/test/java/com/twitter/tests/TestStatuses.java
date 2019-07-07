package com.twitter.tests;

import com.twitter.utils.BaseTest;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.Test;

import static com.twitter.utils.CommonMethods.getRandInt;
import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

public class TestStatuses extends BaseTest {

   @Test
   public void verifyTimeline() {
      int numberOfTwits = getRandInt(1, 10);
      given()
         .spec(oauth1)
         .param("count", numberOfTwits)
         .filter(new AllureRestAssured())
         .when()
         .get("/statuses/home_timeline.json")
         .then()
         .statusCode(200)
         .body("id.size()", equalTo(numberOfTwits));
   }

   @Test
   public void verifyResponseTime() {
      given()
         .spec(oauth1)
         .param("count", 1)
         .when()
         .get("statuses/home_timeline.json")
         .then()
         .time(lessThanOrEqualTo(3000L), MILLISECONDS);
   }
}
