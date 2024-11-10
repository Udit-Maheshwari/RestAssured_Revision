package spotifyAPI.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() {
        properties = PropertyUtils.propertyReader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance() {
        if(dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getUserPlayListId() {
        String playListId = properties.get("user_playlist_id").toString();
        if(playListId != null) return playListId;
        else throw new RuntimeException("user Play list is not loaded in data.properties file");
    }
}
