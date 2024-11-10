import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class ExtractResponseBodyUsingExtractMethod {

    @Test
    public void extractResponseBodyUsingExtractMethod() {
        // https://reqres.in/api/users?page=2

      Response response =  RestAssured.given()
                .baseUri("https://reqres.in").
                when()
                .get("/api/users?page=2").
                then()
                .log().all()
                .extract()
              .response();

        System.out.println(response.prettyPrint());
        // Extract single value in response body
        // 1 st way to use path with response
      //  System.out.println("Page Number is = " + response.path("page"));

        // 2nd way to use JsonPath class
      //  JsonPath jsonPath = new JsonPath(response.asString());
//        System.out.println("Page Number is = " + jsonPath.getString("page"));

        // 3rd way without create object of jsonpath
        String pageNumber = JsonPath.from(response.asString()).getString("page");
        System.out.println("Page Number is = " + pageNumber);

    }
}
