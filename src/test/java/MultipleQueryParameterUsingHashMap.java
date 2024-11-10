import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;

public class MultipleQueryParameterUsingHashMap {


    @Test
    public void multipleQueryParameterUsingHashMap() {
        // https://postman-echo.com/get
        HashMap<String, String> queryParam = new HashMap<>();
        queryParam.put("param1", "value1");
        queryParam.put("param2", "value2");

        RestAssured.given()
                .baseUri("https://postman-echo.com")
                .queryParams(queryParam)
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
