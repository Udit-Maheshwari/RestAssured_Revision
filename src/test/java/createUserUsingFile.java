import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.devtools.v116.target.model.FilterEntry;
import org.testng.annotations.Test;

import java.io.File;

public class createUserUsingFile {

    @Test
    public void createUserUsingInputAsFile() {
        // https://reqres.in/api/users
        File file = new File("src\\test\\resources\\postResponseBody.json");
        System.out.println(file.getPath());

        RequestSpecification requestSpecification = RestAssured.given().body(file).baseUri("https://reqres.in/api/users");

        Response response = requestSpecification.post();

        response.prettyPrint();
    }
}
