package spotifyAPI;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

public class SpecBuilder {

    public static RequestSpecification setRequestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
      //  requestSpecBuilder.setBaseUri(System.getProperty("Base_URI")); // We can fetch base_URL value form console this is done because if we want to test on multiple environment then we can use this way
        requestSpecBuilder.setBaseUri("https://api.spotify.com");
        requestSpecBuilder.setBasePath("/v1");
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.addFilter(new AllureRestAssured()); // this is used for add request and response data in allure report
        requestSpecBuilder.log(LogDetail.ALL);
        return requestSpecBuilder.build();
    }

    public static ResponseSpecification setResponseSpecification() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
       // responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectResponseTime(Matchers.lessThan(4000L));
        responseSpecBuilder.log(LogDetail.ALL);

        return responseSpecBuilder.build();
    }
}
