package coderByte;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class AssessmentSolutions {

    private final String URL = "https://api.football-data.org/v2/teams/12";
    private final String token = "e051043b86624518a57a263f9388d198";
    /**
        Validate 200 http code on successful response
     **/
    @Test
    public void getStatusCode200() {

        given().header("Content-Type", "application/json").header("X-Auth-Token", token)
                .when().get(URL).then().assertThat().statusCode(200).extract().response();
    }
    /**
        Validate 403 http code on failed authentication
     **/
    @Test
    public void getStatusCode403() {

        given().header("Content-Type", "application/json")
                .when().get(URL).then().assertThat().statusCode(403).extract().response();
    }
    /**
     Validate more than 20 squad members are there in API response
     **/
    @Test
    public void squadNumebr() {

        Response response = given().header("Content-Type", "application/json").header("X-Auth-Token", token)
                .when().get(URL).then().assertThat().statusCode(200).extract().response();
        JsonPath js = new JsonPath(response.asString());
        int count = js.getInt("squad.size()");
        Assert.assertTrue(count > 20);
    }
}
