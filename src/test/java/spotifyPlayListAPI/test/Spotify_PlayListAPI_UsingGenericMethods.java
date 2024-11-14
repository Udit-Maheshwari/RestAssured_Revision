package spotifyPlayListAPI.test;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import spotifyPlayListAPI.pojo.ErrorMain;
import spotifyPlayListAPI.pojo.PlayList;
import spotifyPlayListAPI.PlayListAPI_EndPoint.EndPoints;
import spotifyPlayListAPI.utils.DataLoader;
import spotifyPlayListAPI.utils.PropertyUtils;

public class Spotify_PlayListAPI_UsingGenericMethods extends BaseTest {

    //public static String playListId = "";
    public PlayList playListBuilder(String name, String description, boolean _public) {
        PlayList playList = new PlayList();
        playList.setName(name);
        playList.setDescription(description);
        playList.setPublic(_public);
        return playList;
    }

    public void assertCommonAssertion(PlayList responsePlayList, PlayList requestPlayList) {
        MatcherAssert.assertThat(responsePlayList.getName(), Matchers.equalTo(requestPlayList.getName()));
        MatcherAssert.assertThat(responsePlayList.getDescription(), Matchers.equalTo(requestPlayList.getDescription()));
        MatcherAssert.assertThat(responsePlayList.getPublic(), Matchers.equalTo(requestPlayList.getPublic()));
    }

    public void verifyStatusCode(int actualStatusCode, int expStatusCode) {
        Assert.assertEquals(actualStatusCode, expStatusCode);
    }


    @Test(priority = 0)
    public void createPlayList() {
        // this is the normal pattern
        PlayList playList = playListBuilder("Bollywood Songs", "New Bollywood playlist description", false);

        // This is the below form using Builder Pattern
/*        PlayList_Builderpattern playList = new PlayList_Builderpattern().
                setName("Bollywood Songs").setDescription("New Bollywood playlist description")
                .setPublic(false);*/

        Response response = EndPoints.post(playList);
        verifyStatusCode(response.getStatusCode(), 201);
        PlayList responsePlayList = response.as(PlayList.class);
        assertCommonAssertion(responsePlayList, playList);

        PropertyUtils.propertyWriter("src/test/resources/data.properties", responsePlayList.getId());
        System.out.println("User play list id is --> " + DataLoader.getInstance().getUserPlayListId());
    }

    @Test(priority = 1)
    public void getPlayList() {
       // Response response = EndPoints.get(playListId);
        Response response = EndPoints.get(DataLoader.getInstance().getUserPlayListId());
        verifyStatusCode(response.getStatusCode(), 200);

        PlayList responsePlayList =  response.as(PlayList.class);
        MatcherAssert.assertThat(responsePlayList.getName(), Matchers.equalTo("Bollywood Songs"));
        MatcherAssert.assertThat(responsePlayList.getDescription(), Matchers.equalTo("New Bollywood playlist description"));
        MatcherAssert.assertThat((responsePlayList.getOwner().getDisplayName()), Matchers.equalTo("31hhytgx4xp5cfqxugfwgligj24a"));
        MatcherAssert.assertThat(responsePlayList.getPublic(), Matchers.equalTo(true));
    }

    @Test(priority = 2)
    public void updatePlayListApi() {
        PlayList updatePlayList = playListBuilder("Update Bollywood Songs", "Updated Bollywood playlist description", false);

       // Response response = EndPoints.update(updatePlayList, playListId);
        Response response = EndPoints.update(updatePlayList, DataLoader.getInstance().getUserPlayListId());
        verifyStatusCode(response.getStatusCode(), 200);

    }

    @Test(priority = 3)
    public void shouldNotBeCreateNewApiUsingIncorrectToken() {
        PlayList playList = playListBuilder("Latest Songs", "Latest playlist description", false);
        Response response = EndPoints.post(playList, "123456");

        ErrorMain errorMain =  response.as(ErrorMain.class);
        MatcherAssert.assertThat(errorMain.getError().getStatus(), Matchers.equalTo(401));
        MatcherAssert.assertThat(errorMain.getError().getMessage(), Matchers.equalTo("Invalid access token"));
    }

//    @Test(priority = 4)
//    public void deletePlayList() {
//        Response response = EndPoints.delete(playListId);
//        Assert.assertEquals(response.getStatusCode(), 200);
//
//        response.prettyPrint();
//    }

}
