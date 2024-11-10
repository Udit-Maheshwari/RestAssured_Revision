import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostHTTPMethod {

    @Test
    public void postHTTPMethod() {
        // https://reqres.in/api/users

        // create JSon object class object to create the data
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Udit");
        jsonObject.put("Job", "QA");*/

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode=objectMapper.createObjectNode();
        objectNode.put("FullName", "Udit Maheshwari");
        objectNode.put("Job", "Automation QA Engineer");

        Response response = RestAssured.given().baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON).body(objectNode).post();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
