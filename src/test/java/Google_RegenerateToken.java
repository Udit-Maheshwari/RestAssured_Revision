import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Google_RegenerateToken {

    @Test
    public void regenerateToken() {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("client_id", "232418106773-4n6d34adf56o2daltrh9gjdgubn240kg.apps.googleusercontent.com");
        parameter.put("client_secret", "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");
        parameter.put("grant_type", "refresh_token");
        parameter.put("refresh_token", "1//0g7irDBS_kNOhCgYIARAAGBASNwF-L9Irc7MvkXiArvY3Mwe7bveA71sABtVhX5ew7FU7C6vId8G9JBUA3G-P5rDdQe30wiWQSiE");

      Response response = RestAssured.given()
                .baseUri("https://oauth2.googleapis.com")
              .formParams(parameter)
                .given()
                .post("/token");
      response.prettyPrint();
    }


}
