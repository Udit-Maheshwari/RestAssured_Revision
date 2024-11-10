import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashSet;

public class VerifyHeadersValueFromResponseBody {

    @Test
    public void verifyHeadersValueFromResponseBody() {
        // https://reqres.in/api/users?page=2

        RestAssured.given()
                .baseUri("https://reqres.in").
                when()
                .get("/api/users/2").
                then()
                .log().all()
                .statusCode(200)
                .headers("Content-Type", "application/json; charset=utf-8",
                        "Connection", "keep-alive");


    }
}
