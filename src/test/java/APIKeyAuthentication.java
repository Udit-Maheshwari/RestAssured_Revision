import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class APIKeyAuthentication {

    @Test
    public void apiKeyAuthentication() {
       // RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";
        Response response  = RestAssured.given()
                .queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
                .queryParam("q", "Aligarh")
                .queryParam("cnt", "1")
                .when()
                .get("https://api.openweathermap.org/data/2.5/forecast/daily");
        response.prettyPrint();
        response.then().statusCode(200); // Check for a 200 OK response
        response.then().body("city.name", equalTo("Aligarh"));
        response.then().time(Matchers.lessThan(2000L));
    }
}
