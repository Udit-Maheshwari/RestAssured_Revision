import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class VerifyResponseBodyUsingMatchersClass {


    @Test
    public void verifyResponseBodyUsingMatcherClass() {
        // https://reqres.in/api/users?page=2

        RestAssured.given()
                .baseUri("https://reqres.in").
               // log().ifValidationFails().
        config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
        when()
                .get("/api/users?page=2").
                then()
              //  .log().all()
              //  .log().ifError()
              //  .log().ifValidationFails()
                .assertThat().statusCode(201)
                .body("page", Matchers.equalTo(2),
                        "data.id", Matchers.hasItems(7, 8, 9, 10, 11, 12));
    }
}
