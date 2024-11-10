package spotifyAPI;

import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import spotifyAPI.pojo.ErrorMain;
import spotifyAPI.pojo.PlayList;
import spotifyAPI.pojo.PlayList_Builderpattern;

public class Spotify_PlayListAPITest {

    public static String playListId = "";

    @Test(priority = 0)
    public void createPlayList() {
        // this is the normal pattern
      /*  PlayList playList = new PlayList();
        playList.setName("Bollywood Songs");
        playList.setDescription("New Bollywood playlist description");
        playList.setPublic(false)*/;

        // This is the below form using Builder Pattern
        PlayList_Builderpattern playList = new PlayList_Builderpattern().
                setName("Bollywood Songs").setDescription("New Bollywood playlist description")
                .setPublic(false);

      PlayList responsePlayList =  RestAssured.given(SpecBuilder.setRequestSpecification())
                .pathParam("userId", "31hhytgx4xp5cfqxugfwgligj24a")
                .body(playList)
                .when().
                post("/users/{userId}/playlists")
                .then()
                .spec(SpecBuilder.setResponseSpecification())
              .assertThat()
                .statusCode(201)
              .extract().
              response()
              .as(PlayList.class);

        MatcherAssert.assertThat(responsePlayList.getName(), Matchers.equalTo(playList.getName()));
        MatcherAssert.assertThat(responsePlayList.getDescription(), Matchers.equalTo(playList.getDescription()));
        MatcherAssert.assertThat(responsePlayList.getPublic(), Matchers.equalTo(playList.getPublic()));

        playListId = responsePlayList.getId();
    }

    @Test(priority = 1)
    public void getPlayList() {
        PlayList responsePlayList =  RestAssured.given(SpecBuilder.setRequestSpecification())
                .when().
                get("/playlists/"+playListId)
                .then()
                .spec(SpecBuilder.setResponseSpecification()).assertThat()
                .statusCode(200)
                .extract().
                response()
                .as(PlayList.class);

        MatcherAssert.assertThat(responsePlayList.getName(), Matchers.equalTo("Bollywood Songs"));
        MatcherAssert.assertThat(responsePlayList.getDescription(), Matchers.equalTo("New Bollywood playlist description"));
        MatcherAssert.assertThat((responsePlayList.getOwner().getDisplayName()), Matchers.equalTo("31hhytgx4xp5cfqxugfwgligj24a"));
        MatcherAssert.assertThat(responsePlayList.getPublic(), Matchers.equalTo(true));
    }

    @Test(priority = 2)
    public void updatePlayListApi() {
        PlayList updatePlayList = new PlayList();
        updatePlayList.setName("Update Bollywood Songs");
        updatePlayList.setDescription("Updated Bollywood playlist description");
        updatePlayList.setPublic(false);

        RestAssured.given(SpecBuilder.setRequestSpecification())
                .body(updatePlayList)
                .when().
                put("/playlists/"+playListId)
                .then()
                .spec(SpecBuilder.setResponseSpecification()).assertThat()
                .statusCode(200);
    }

    @Test
    public void shouldNotBeCreateNewApiUsingIncorrectToken() {
        PlayList playList = new PlayList();
        playList.setName("Latest Songs");
        playList.setDescription("Latest playlist description");
        playList.setPublic(false);

        ErrorMain errorMain =  RestAssured.given(SpecBuilder.setRequestSpecification())
                .pathParam("userId", "31hhytgx4xp5cfqxugfwgligj24a")
                .body(playList)
                .when().
                post("/users/{userId}/playlists")
                .then()
                .spec(SpecBuilder.setResponseSpecification()).assertThat()
                .statusCode(401)
                .extract().
                response()
                .as(ErrorMain.class);

        MatcherAssert.assertThat(errorMain.getError().getStatus(), Matchers.equalTo(401));
        MatcherAssert.assertThat(errorMain.getError().getMessage(), Matchers.equalTo("Invalid access token"));
    }


}
