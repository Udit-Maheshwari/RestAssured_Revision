import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestSpecificationDemo {

    RequestSpecification requestSpecification;
    @BeforeClass
    public void configRequest() {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://reqres.in/api/users?page=2");
        requestSpecBuilder.log(LogDetail.HEADERS);
        requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void verifyGetRequest() {
        // https://reqres.in/api/users?page=2

        RestAssured.with().spec(requestSpecification)
              //  .log().headers()
                .when()
                .get().
                then()
             //   .log().headers()
                .statusCode(200)
                .headers("Content-Type", "application/json; charset=utf-8",
                        "Connection", "keep-alive")
                .log().headers();


    }
}
