import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class PatchHttpRequest {

    @Test
    public void patchHttpRequest() {
        PostHTTPMethod postHTTPMethod = new PostHTTPMethod();
        postHTTPMethod.postHTTPMethod();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("FullName", "Udit Sharma");

        Response response = RestAssured.given().baseUri("https://reqres.in/api/users/248")
                .contentType(ContentType.JSON).body(jsonObject).patch();

        response.prettyPrint();
    }
}
