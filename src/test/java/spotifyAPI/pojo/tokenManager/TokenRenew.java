package spotifyAPI.pojo.tokenManager;

import io.restassured.response.Response;
import spotifyAPI.GenericMethods.ReusableMethods;
import spotifyAPI.utils.ConfigLoader;

import java.time.Instant;
import java.util.HashMap;

public class TokenRenew {
    private static String accessToken;
    private static Instant expiryTime;

    public synchronized static String getToken() {
        try {
            if(accessToken == null || Instant.now().isAfter(expiryTime)) {
                System.out.println("Token is generating...");
                Response response = renewToken();
                accessToken = response.path("access_token");
                int expiryTimeInSeconds = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryTimeInSeconds - 300);
            }else {
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    public static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("client_id", ConfigLoader.getInstance().getClient_id());
        formParams.put("client_secret", ConfigLoader.getInstance().getClient_secret());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefresh_token());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrant_type());

        Response response = ReusableMethods.postGenerateToken(formParams);

        if(response.statusCode() != 200) {
            throw new RuntimeException("Token is not generated");
        }
        return response;
    }
}
