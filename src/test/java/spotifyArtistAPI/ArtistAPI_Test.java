package spotifyArtistAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import spotifyPlayListAPI.utils.ConfigLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArtistAPI_Test {

    @Test(priority = 0)
    public void verifySingleArtist() {
        Response response = EndPoint.getSingleArtist(ConfigLoader.getInstance().getArtist_id());
        GenericMethods.verifyStatusCode(response.statusCode(), Status_Code.CODE_200.code);
        GenericMethods.verifyArtistName(response.path("name"), "Pitbull");
    }

    @Test(priority = 1)
    public void verifySeveralArtists() {
        Response response = EndPoint.getSeveralArtistDetails(ConfigLoader.getInstance().getSeveralArtist_id());
        GenericMethods.verifyStatusCode(response.getStatusCode(), Status_Code.CODE_200.code);
        List<String> expArtistName = new ArrayList<>();
        expArtistName.add("deadmau5");
        expArtistName.add("Ratatat");
        expArtistName.add("Avicii");
        GenericMethods.verifySeveralArtistName(response.path("artists.name"), expArtistName);
    }

    @Test(priority = 2)
    public void verifyArtistAlbumUsingIncludeGroupFiler() {
        HashMap<String, String> filer = new HashMap<>();
        filer.put("include_groups", "single");
        Response response = EndPoint.getArtistAlbum(filer, ConfigLoader.getInstance().getArtist_id() + Routes.ALBUMS);
        GenericMethods.verifyStatusCode(response.statusCode(), Status_Code.CODE_200.code);
        MatcherAssert.assertThat(response.path("items[0].album_type"), Matchers.equalTo("single"));
    }


}
