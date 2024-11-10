import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HTTPGetRequest {

    @Test
    public void getHTTPRequest() {

        // https://petstore.swagger.io/v2/pet/findByStatus?status=available
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io").basePath("/v2/pet/findByStatus")
                .queryParam("status", "available");

        Response response = requestSpecification.get();
        response.prettyPrint();

        response.then().body("status[0]", Matchers.equalTo("available"));

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
