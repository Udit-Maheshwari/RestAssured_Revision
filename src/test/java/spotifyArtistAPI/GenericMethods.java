package spotifyArtistAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import spotifyPlayListAPI.SpecBuilder;

import java.util.HashMap;
import java.util.List;

public class GenericMethods {

    public static Response getSingleArtist(String accessToken, String artistID) {
        return RestAssured.given(SpecBuilderArtist.setRequestSpecification())
                .auth().oauth2(accessToken)
                .when()
                .get(artistID)
                .then()
                .spec(SpecBuilderArtist.setResponseSpecification())
                .extract().response();
    }

    public static Response getSeveralArtistsDetails(String accessToke, String artistsID) {
        return RestAssured.given(SpecBuilderArtist.setRequestSpecification())
                .auth().oauth2(accessToke)
                .queryParam("ids", artistsID)
                .when()
                .get()
                .then()
                .spec(SpecBuilderArtist.setResponseSpecification())
                .extract().response();
    }

    public static Response getArtistsAlbum(String accessToke, String path, HashMap<String, String> queryParam) {
        return RestAssured.given(SpecBuilderArtist.setRequestSpecification())
                .auth().oauth2(accessToke)
                .queryParams(queryParam)
                .when()
                .get(path)
                .then()
                .spec(SpecBuilderArtist.setResponseSpecification())
                .extract().response();
    }

    public static void verifyStatusCode(int actualStatus, int expStatusCode) {
        Assert.assertEquals(actualStatus, expStatusCode);
    }

    public static void verifyArtistName(String actualArtistName, String expArtistName) {
        MatcherAssert.assertThat(actualArtistName, Matchers.equalTo(expArtistName));
    }

    public static void verifySeveralArtistName(List<String> actualArtistName, List<String> expArtistName) {
        MatcherAssert.assertThat(actualArtistName, Matchers.equalTo(expArtistName));
    }


}
