package httpBinAPI_Request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;

public class DigestAuthentication {

    @Test
    public void digestAuthentication() {
        // https://httpbin.org/digest-auth/auth/udit/password
        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification.header("accept", "application/json")
                .auth().digest("udit", "password")
                .get("https://httpbin.org/digest-auth/auth/udit/password");

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);
    }
}
