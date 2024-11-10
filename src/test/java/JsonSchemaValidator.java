import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class JsonSchemaValidator {

    @Test
    public void jsonSchemaValidation() {
        //https://reqres.in/api/users/2

        RestAssured.given()
                .baseUri("https://reqres.in")
                .pathParam("userId", "2")
                .when()
                .get("/api/users/{userId}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(io.restassured.module.jsv.JsonSchemaValidator
                        .matchesJsonSchemaInClasspath("jsonSchemaSample.json"));

        // verify Json Format using Json Schema validator
    }
}
