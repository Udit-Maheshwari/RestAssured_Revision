package spotifyArtistAPI;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

public class SpecBuilderArtist {

    public static RequestSpecification setRequestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(Routes.BASE_URI);
        requestSpecBuilder.setBasePath(Routes.BASE_Path);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.addFilter(new AllureRestAssured());
        requestSpecBuilder.log(LogDetail.ALL);
        return requestSpecBuilder.build();
    }

    public static ResponseSpecification setResponseSpecification() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.log(LogDetail.ALL);
        responseSpecBuilder.expectResponseTime(Matchers.lessThan(3000L));
        return responseSpecBuilder.build();
    }
}
