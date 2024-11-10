import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthentication {

    @Test
    public void basicAuthentication() {
        //https://httpbin.org/basic-auth/udit/123

        Response response = RestAssured.given()
                .baseUri("https://httpbin.org/basic-auth/udit/123")
                .auth().preemptive().basic("udit", "123").get();

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
