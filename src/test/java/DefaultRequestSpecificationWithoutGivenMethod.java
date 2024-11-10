import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DefaultRequestSpecificationWithoutGivenMethod {

    @BeforeClass
    public void configRequest() {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://reqres.in/api/users?page=2");
        requestSpecBuilder.log(LogDetail.HEADERS);
        RestAssured.requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void verifyGetRequest() {
        // https://reqres.in/api/users?page=2
        // due to we initialize RequestSpecification as default that is Given() method is not required
                RestAssured.when()
                .get().
                then()
                //   .log().headers()
                .statusCode(200)
                .headers("Content-Type", "application/json; charset=utf-8",
                        "Connection", "keep-alive")
                .log().headers();


    }
}
