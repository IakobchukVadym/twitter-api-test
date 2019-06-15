package com.twitter.tests;

import com.twitter.utils.BaseTest;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestRetwit extends BaseTest {

  private String twitId;

  @Test
  public void getTwitId() {
    twitId =
       given()
          .spec(oauth1)
          .param("screen_name", "Gunnersaurus")
          .param("count", 1)
          .filter(new AllureRestAssured())
          .when()
          .get("/statuses/user_timeline.json")
          .then()
          .statusCode(200)
          .extract()
          .path("id_str[0]");
  }

  @Test(dataProvider = "RetwitOptions", dependsOnMethods = "getTwitId")
  public void verifyRetwit(int statuscode) {
    given()
       .spec(oauth1)
       .filter(new AllureRestAssured())
       .when()
       .post("statuses/retweet/" + twitId + ".json")
       .then()
       .statusCode(statuscode);
  }

  @Test(dependsOnMethods = "verifyRetwit")
  public void verifyDeletionOfRetwit() {
    given()
       .spec(oauth1)
       .filter(new AllureRestAssured())
       .when()
       .post("statuses/unretweet/" + twitId + ".json")
       .then()
       .log()
       .ifValidationFails()
       .statusCode(200);
  }

  @DataProvider(name = "RetwitOptions")
  public Object[][] codes() {
    return new Object[][]{{200}, {403}};
  }
}
