import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;

public class HowToBlackListedHeadersFromLog {

    @Test
    public void extractResponseBodyUsingExtractMethod() {
        // https://reqres.in/api/users?page=2

        HashSet<String> headers =  new HashSet<>();
        headers.add("Content-Type");
        headers.add("Connection");

        Response response =  RestAssured.given()
                // to hide single header value
               // .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Content-Type")))
                .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeaders(headers)))
                .baseUri("https://reqres.in").
                when()
                .get("/api/users/2").
                then()
                .log().all()
                .extract()
                .response();

        System.out.println(response.prettyPrint());
        String pageNumber = JsonPath.from(response.asString()).getString("page");
        System.out.println("Page Number is = " + pageNumber);

    }
}
