import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PathParameterExample {

    @Test
    public void pathParameterExample() {
        //https://reqres.in/api/users/2

        RestAssured.given()
                .baseUri("https://reqres.in")
                .pathParam("userId", "2")
                .given()
                .get("/api/users/{userId}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
