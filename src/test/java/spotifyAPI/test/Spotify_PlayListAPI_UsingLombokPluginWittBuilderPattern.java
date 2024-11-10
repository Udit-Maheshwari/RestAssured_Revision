package spotifyAPI.test;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import spotifyAPI.pojo.ErrorMain;
import spotifyAPI.pojo.PlayList;
import spotifyAPI.PlayListAPI_EndPoint.EndPoints_Lombok_WithBuilderPattern;
import spotifyAPI.pojo.PlayList_Lombok_BuilderPattern;
import spotifyAPI.utils.DataLoader;
import spotifyAPI.utils.PropertyUtils;

public class Spotify_PlayListAPI_UsingLombokPluginWittBuilderPattern {

    //public static String playListId = "";
    public PlayList_Lombok_BuilderPattern playListBuilder(String name, String description, boolean _public) {
        return PlayList_Lombok_BuilderPattern.builder().name(name)
                .description(description)
                ._public(_public).build();
    }

    public void assertCommonAssertion(PlayList_Lombok_BuilderPattern responsePlayList, PlayList_Lombok_BuilderPattern requestPlayList) {
        MatcherAssert.assertThat(responsePlayList.getName(), Matchers.equalTo(requestPlayList.getName()));
        MatcherAssert.assertThat(responsePlayList.getDescription(), Matchers.equalTo(requestPlayList.getDescription()));
        MatcherAssert.assertThat(responsePlayList.get_public(), Matchers.equalTo(requestPlayList.get_public()));
    }

    public void verifyStatusCode(int actualStatusCode, int expStatusCode) {
        Assert.assertEquals(actualStatusCode, expStatusCode);
    }


    @Test(priority = 0)
    public void createPlayList() {
        // this is the normal pattern
        PlayList_Lombok_BuilderPattern playList = playListBuilder("Bollywood Songs", "New Bollywood playlist description", false);

        // This is the below form using Builder Pattern
/*        PlayList_Builderpattern playList = new PlayList_Builderpattern().
                setName("Bollywood Songs").setDescription("New Bollywood playlist description")
                .setPublic(false);*/

        Response response = EndPoints_Lombok_WithBuilderPattern.post(playList);
        verifyStatusCode(response.getStatusCode(), 201);
        PlayList_Lombok_BuilderPattern responsePlayList = response.as(PlayList_Lombok_BuilderPattern.class);
        assertCommonAssertion(responsePlayList, playList);

        PropertyUtils.propertyWriter("src/test/resources/data.properties", responsePlayList.getId());
        System.out.println("User play list id is --> " + DataLoader.getInstance().getUserPlayListId());
    }

    @Test(priority = 1)
    public void getPlayList() {
       // Response response = EndPoints.get(playListId);
        Response response = EndPoints_Lombok_WithBuilderPattern.get(DataLoader.getInstance().getUserPlayListId());
        verifyStatusCode(response.getStatusCode(), 200);

        PlayList responsePlayList =  response.as(PlayList.class);
        MatcherAssert.assertThat(responsePlayList.getName(), Matchers.equalTo("Bollywood Songs"));
        MatcherAssert.assertThat(responsePlayList.getDescription(), Matchers.equalTo("New Bollywood playlist description"));
        MatcherAssert.assertThat((responsePlayList.getOwner().getDisplayName()), Matchers.equalTo("31hhytgx4xp5cfqxugfwgligj24a"));
        MatcherAssert.assertThat(responsePlayList.getPublic(), Matchers.equalTo(true));
    }

    @Test(priority = 2)
    public void updatePlayListApi() {
        PlayList_Lombok_BuilderPattern updatePlayList = playListBuilder("Update Bollywood Songs", "Updated Bollywood playlist description", false);

       // Response response = EndPoints.update(updatePlayList, playListId);
        Response response = EndPoints_Lombok_WithBuilderPattern.update(updatePlayList, DataLoader.getInstance().getUserPlayListId());
        verifyStatusCode(response.getStatusCode(), 200);

    }

    @Test(priority = 3)
    public void shouldNotBeCreateNewApiUsingIncorrectToken() {
        PlayList_Lombok_BuilderPattern playList = playListBuilder("Latest Songs", "Latest playlist description", false);
        Response response = EndPoints_Lombok_WithBuilderPattern.post(playList, "123456");

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
