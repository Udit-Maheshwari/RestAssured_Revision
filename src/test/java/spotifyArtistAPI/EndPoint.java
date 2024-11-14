package spotifyArtistAPI;

import io.restassured.response.Response;
import spotifyPlayListAPI.pojo.tokenManager.TokenRenew;

import java.util.HashMap;

public class EndPoint {

    public static Response getSingleArtist(String artistId) {
        return GenericMethods.getSingleArtist(TokenRenew.getToken(), artistId);
    }

    public static Response getSeveralArtistDetails(String artists) {
        return GenericMethods.getSeveralArtistsDetails(TokenRenew.getToken(), artists);
    }

    public static Response getArtistAlbum(HashMap<String, String> queryParam, String path) {
        return GenericMethods.getArtistsAlbum(TokenRenew.getToken(), path,queryParam);
    }
}
