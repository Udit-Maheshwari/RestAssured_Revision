package spotifyAPI.PlayListAPI_EndPoint;

import io.restassured.response.Response;
import spotifyAPI.GenericMethods.ReusableMethods;
import spotifyAPI.pojo.PlayList;
import spotifyAPI.pojo.tokenManager.TokenRenew;
import spotifyAPI.utils.ConfigLoader;

public class EndPoints {
    //private static String AccessToken = "BQDLDtm9H84RwU83gjYt8Dgfi1YUDjyLxkV0PxxieKaOUFqhDHYfmwzhoKGEUyk6FPxnZwo03Dtt0Uo7qin0Wbk0GJ6ZDkWOcDEQO55ep_shiAd1iJ4gobx3_GsaOKVB76sWH9-XBv1GcoYjfsTT5KtXMlqMpXuVWn7oDuWf3NJRII8me6-2cyu-ZzhPUEd6E6fRRZnRr4ZnRQtQTgciVrQUVKXcc8XkFl8iS3y9MlRV28QTd_--sc_xNtMb0MuEcmTPe_k68icK98sjNVw42-Xe";

    public static Response post(PlayList playList) {
        return ReusableMethods.post(playList, TokenRenew.getToken(), "/users/"+ ConfigLoader.getInstance().getUser_id()+"/playlists");
    }

    public static Response post(PlayList playList, String token) {
        return ReusableMethods.post(playList, token, "/users/"+ ConfigLoader.getInstance().getUser_id()+"/playlists");
    }

    public static Response get(String playListId) {
        return ReusableMethods.get(TokenRenew.getToken(), "/playlists/"+playListId);
    }

    public static Response update(PlayList updatePlayList, String playListId) {
        return ReusableMethods.update(updatePlayList, TokenRenew.getToken(), "/playlists/"+playListId);
    }

    public static Response delete(String playListId) {
        return ReusableMethods.delete(TokenRenew.getToken(), "/playlists/"+playListId);
    }
}
