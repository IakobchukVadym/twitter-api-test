import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class testTwitPost extends BaseTest {

    String twitId;
    String textToWrite = "This is posted as a part of Automation test on " + new Date().toString();


    @Test
    public void verifyTwitCreation(){

        Response response =
        given().
                log().params().
                auth().
                oauth(consumerKey,consumerSecret,accessToken,accessSecret).
                queryParam("status", textToWrite).
                when().
                post("/update.json").
                then().
                log().body().
                statusCode(200).
                body("text", equalTo(textToWrite)).
                extract().response();

        twitId = response.path("id_str");
    }

    @Test(dependsOnMethods = "verifyTwitCreation")
    public void verifyDuplicatePrevention(){
                given().
                        log().params().
                        auth().
                        oauth(consumerKey,consumerSecret,accessToken,accessSecret).
                        queryParam("status", textToWrite).
                        when().
                        post("/update.json").
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
                        log().params().
                        auth().
                        oauth(consumerKey,consumerSecret,accessToken,accessSecret).
                        when().
                        post("destroy/"+ twitId + ".json").
                        then().
                        log().all().
                        statusCode(200).
                        body("text", equalTo(textToWrite)).
                        body("id_str", equalTo(twitId));
    }
}
