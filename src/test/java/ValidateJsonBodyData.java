import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ValidateJsonBodyData {

    @Test
    public void validateJsonBodyContent() {
        // https://reqres.in/api/users?page=2

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://reqres.in/api/users?page=2");

        Response response = requestSpecification.get();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
        // Validate JSON Body Content

        // to find the data for User 4
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("First Name of 4th user is :" + jsonPath.get("data[3].first_name"));

        // to find first name of every user
        List<String> firstNames = jsonPath.getList("data.first_name");
        for(String firstName : firstNames){
            System.out.println("First name value is :"+firstName);
        }
    }
}
