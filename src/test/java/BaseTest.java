
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;


import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

public class BaseTest {
    String consumerKey = "HQqWDhlNh48NJXGBVkLNsZILJ";
    String consumerSecret = "BdhxsE5CGFCyS9XVF2FiBK6ZC6Wnz5ytJfyMvs1R5EpLERyAMc";
    String accessToken = "1020943670764363778-yjVWe2Q3twis8snUb32mVSnU318iTI";
    String accessSecret= "PzxjtUyyFz9BctYZTnCEO5sFqI58CxQ96XKccwP2LxFkH";

    protected ResponseSpecification timeSpec = new ResponseSpecBuilder().
            expectResponseTime(lessThanOrEqualTo(3000L), MILLISECONDS).
            build();
    protected ResponseSpecification responseCode200 = new ResponseSpecBuilder().expectStatusCode(200).build();

    String baseURI = "https://api.twitter.com/1.1/statuses";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = baseURI;
    }
}
