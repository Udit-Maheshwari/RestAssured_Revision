package spotifyPlayListAPI.GenericMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import spotifyPlayListAPI.SpecBuilder;

import java.util.Map;

public class ReusableMethods {

    public static Response post(Object playList, String accessToken, String path) {
        return RestAssured.given(SpecBuilder.setRequestSpecification())
                .body(playList)
                .auth().oauth2(accessToken) // we can pass access token in the header as well
                //.header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .when().
                post(path)
                .then()
                .spec(SpecBuilder.setResponseSpecification())
                .extract().
                response();
    }

    public static Response get(String accessToken, String path) {
        return RestAssured
                .given(SpecBuilder.setRequestSpecification())
                .auth().oauth2(accessToken)
               // .header("Authorization", "Bearer " + accessToken)
                .when().
                get(path)
                .then()
                .spec(SpecBuilder.setResponseSpecification())
                .extract().
                response();
    }

    public static Response update(Object updatePlayList, String accessToken, String path) {
        return RestAssured.given(SpecBuilder.setRequestSpecification())
                .body(updatePlayList)
                .auth().oauth2(accessToken)
              //  .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .when().
                put(path)
                .then()
                .spec(SpecBuilder.setResponseSpecification())
                .extract()
                .response();
    }

    public static Response postGenerateToken(Map<String, String> formParams) {
       return RestAssured.given()
                .baseUri("https://accounts.spotify.com")
                .formParams(formParams)
                .contentType(ContentType.URLENC)
                .log().all()
                .given()
                .post("/api/token")
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response delete(String accessToken, String path) {
        return RestAssured
                .given(SpecBuilder.setRequestSpecification())
                .auth().oauth2(accessToken)
                //.header("Authorization", "Bearer " + accessToken)
                .when().
                delete(path)
                .then()
                .spec(SpecBuilder.setResponseSpecification())
                .extract().
                response();
    }

}
