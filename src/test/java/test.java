import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.util.ArrayList;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.lessThan;

public class test extends BaseTest {


    @Test
    public void verifyLastTwit(){
gi
        Response response =
        given().
                log().params().
                auth().
                oauth(consumerKey,consumerSecret,accessToken,accessSecret).
                param("count", 5).
                when().
                get("https://api.twitter.com/1.1/statuses/home_timeline.json").
                then().
                log().cookies().
                statusCode(200).
                time(lessThan(1500L), MILLISECONDS).
                extract().response();

        ArrayList<String > s = response.path("entities.user_mentions.screen_name");
        System.out.println(s);
        String contentType = response.header("content-type");
        System.out.println("Content type of response is " + contentType);
        Map<String, String> allCookies = response.getCookies();
        Cookies detailedCookies = response.getDetailedCookies();
        //System.out.println(Arrays.asList(allCookies));
        System.out.println("Detailed coockies are " + detailedCookies);
    }

    @Test
    public void veifyResponseTime(){
        given().
                log().params().
                auth().
                oauth(consumerKey,consumerSecret,accessToken,accessSecret).
                param("count", "20").
                when().
                get("https://api.twitter.com/1.1/statuses/home_timeline.json").
                then().log().body().
                spec(timeSpec).spec(responseCode200);
    }
}
