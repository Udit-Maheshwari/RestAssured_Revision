package spotifyAPI.test;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import spotifyAPI.GenericMethods.StatusCode;
import spotifyAPI.pojo.ErrorMain;
import spotifyAPI.pojo.PlayList;
import spotifyAPI.PlayListAPI_EndPoint.EndPoints_Lombok_WithoutBuilder;
import spotifyAPI.pojo.PlayList_UsingLombok;
import spotifyAPI.utils.DataLoader;
import spotifyAPI.utils.PropertyUtils;

@Epic("Spotify OAuth 2.0 API")  // this is used for define EPI Details as same mention in Jira ticket
@Feature("OAuth 2.0 API") // Defined the feature of this test class what can we used
public class Spotify_PlayListAPI_UsingLombokPluginWithoutBuilderpattern{

    //public static String playListId = "";
    public PlayList_UsingLombok playListBuilder(String name, String description, boolean _public) {
        PlayList_UsingLombok playList = new PlayList_UsingLombok();
        playList.setName(name);
        playList.setDescription(description);
        playList.set_public(_public);
        return playList;
    }

    public void assertCommonAssertion(PlayList_UsingLombok responsePlayList, PlayList_UsingLombok requestPlayList) {
        MatcherAssert.assertThat(responsePlayList.getName(), Matchers.equalTo(requestPlayList.getName()));
        MatcherAssert.assertThat(responsePlayList.getDescription(), Matchers.equalTo(requestPlayList.getDescription()));
        MatcherAssert.assertThat(responsePlayList.get_public(), Matchers.equalTo(requestPlayList.get_public()));
    }

    public void verifyStatusCode(int actualStatusCode, StatusCode statusCode) {
        Assert.assertEquals(actualStatusCode, statusCode.code);
    }

    @Description("Create PlayList") // to define the test case Description name in allure report
    @Issue("DOTCOM-110203") // this is defined the defect id for followup
    @TmsLink("TS-02") // this is defined the Test management System(TMS) for Manual test cases ID
    @Link("https://example.com") // to provide additional link which is required in test cases
    @Test(priority = 0, description = "Create a user play list")// Description in test ng define the test case name in allure report
    public void createPlayList() {
        // this is the normal pattern
        PlayList_UsingLombok playList = playListBuilder("Bollywood Songs", "New Bollywood playlist description", false);

        // This is the below form using Builder Pattern
/*        PlayList_Builderpattern playList = new PlayList_Builderpattern().
                setName("Bollywood Songs").setDescription("New Bollywood playlist description")
                .setPublic(false);*/

        Response response = EndPoints_Lombok_WithoutBuilder.post(playList);
        verifyStatusCode(response.getStatusCode(), StatusCode.CODE_201);
        PlayList_UsingLombok responsePlayList = response.as(PlayList_UsingLombok.class);
        assertCommonAssertion(responsePlayList, playList);

        PropertyUtils.propertyWriter("src/test/resources/data.properties", responsePlayList.getId());
        System.out.println("User play list id is --> " + DataLoader.getInstance().getUserPlayListId());
    }

    @Test(priority = 1)
    public void getPlayList() {
       // Response response = EndPoints.get(playListId);
        Response response = EndPoints_Lombok_WithoutBuilder.get(DataLoader.getInstance().getUserPlayListId());
        verifyStatusCode(response.getStatusCode(), StatusCode.CODE_200);

        PlayList responsePlayList =  response.as(PlayList.class);
        MatcherAssert.assertThat(responsePlayList.getName(), Matchers.equalTo("Bollywood Songs"));
        MatcherAssert.assertThat(responsePlayList.getDescription(), Matchers.equalTo("New Bollywood playlist description"));
        MatcherAssert.assertThat((responsePlayList.getOwner().getDisplayName()), Matchers.equalTo("31hhytgx4xp5cfqxugfwgligj24a"));
        MatcherAssert.assertThat(responsePlayList.getPublic(), Matchers.equalTo(true));
    }

    @Test(priority = 2)
    public void updatePlayListApi() {
        PlayList_UsingLombok updatePlayList = playListBuilder("Update Bollywood Songs", "Updated Bollywood playlist description", false);

       // Response response = EndPoints.update(updatePlayList, playListId);
        Response response = EndPoints_Lombok_WithoutBuilder.update(updatePlayList, DataLoader.getInstance().getUserPlayListId());
        verifyStatusCode(response.getStatusCode(), StatusCode.CODE_200);

    }

    @Test(priority = 3)
    public void shouldNotBeCreateNewApiUsingIncorrectToken() {
        PlayList_UsingLombok playList = playListBuilder("Latest Songs", "Latest playlist description", false);
        Response response = EndPoints_Lombok_WithoutBuilder.post(playList, "123456");

        ErrorMain errorMain =  response.as(ErrorMain.class);
        MatcherAssert.assertThat(errorMain.getError().getStatus(), Matchers.equalTo(StatusCode.CODE_401.code));
        MatcherAssert.assertThat(errorMain.getError().getMessage(), Matchers.equalTo(StatusCode.CODE_401.msg));
    }

//    @Test(priority = 4)
//    public void deletePlayList() {
//        Response response = EndPoints.delete(playListId);
//        Assert.assertEquals(response.getStatusCode(), 200);
//
//        response.prettyPrint();
//    }

}
