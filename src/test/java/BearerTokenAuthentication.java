import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BearerTokenAuthentication {

    @Test
    public void bearertoken() {
        // 12343fgfgfg -- Bearer token
        //https://httpbin.org/bearer -- Curl URL

        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.header("accept", "application/json")
                .header("Authorization", "Bearer 12343fgfgfg");
        requestSpecification.baseUri("https://httpbin.org/bearer");

        Response response =  requestSpecification.get();

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
