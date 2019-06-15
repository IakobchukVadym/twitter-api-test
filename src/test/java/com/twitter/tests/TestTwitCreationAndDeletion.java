package com.twitter.tests;

import com.twitter.utils.BaseTest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class TestTwitCreationAndDeletion extends BaseTest {

  private String twitId;
  private String textToPublish =
     "This is posted as part of Automation Test executed on " + new Date().toString();

  // Create new twit
  @Test
  public void verifyTwitCreation() {
    Response response =
       given()
          .spec(oauth1)
          .queryParam("status", textToPublish)
          .filter(new AllureRestAssured())
          .when()
          .post("statuses/update.json")
          .then()
          .log()
          .ifValidationFails()
          .statusCode(200)
          .body("text", equalTo(textToPublish))
          .extract()
          .response();
    twitId = response.path("id_str");
  }

  // Check that duplication prevention works
  @Test(dependsOnMethods = "verifyTwitCreation")
  public void verifyDuplicatePrevention() {
    given()
       .spec(oauth1)
       .queryParam("status", textToPublish)
       .filter(new AllureRestAssured())
       .when()
       .post("/statuses/update.json")
       .then()
       .log()
       .ifValidationFails()
       .statusCode(403)
       .root("errors")
       .body("code", hasItem(187))
       .body("message", hasItem("Status is a duplicate."));
  }

  // Delete last created twit
  @Test(dependsOnMethods = "verifyDuplicatePrevention")
  public void verifyTwitDeletion() {
    given()
       .spec(oauth1)
       .when()
       .filter(new AllureRestAssured())
       .post("/statuses/destroy/" + twitId + ".json")
       .then()
       .log()
       .ifValidationFails()
       .statusCode(200)
       .body("text", equalTo(textToPublish))
       .body("id_str", equalTo(twitId));
  }
}
