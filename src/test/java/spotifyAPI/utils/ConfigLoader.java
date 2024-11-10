package spotifyAPI.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyReader("src/test/resources/config.properties");
    }

    // below method is called as Singleton class to provide the data single time
    public static ConfigLoader getInstance() {
        if(configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClient_id() {
        String client_id = properties.getProperty("client_id");
        if(!(client_id == null)) return client_id;
        else throw new RuntimeException("Client Id is not found in config.properties file");
    }

    public String getClient_secret() {
        String client_secret = properties.getProperty("client_secret");
        if(!(client_secret == null)) return client_secret;
        else throw new RuntimeException("Client Secret is not found in config.properties file");
    }

    public String getRefresh_token() {
        String refresh_token = properties.getProperty("refresh_token");
        if(!(refresh_token == null)) return refresh_token;
        else throw new RuntimeException("Refresh_token is not found in config.properties file");
    }

    public String getGrant_type() {
        String grant_type = properties.getProperty("grant_type");
        if(!(grant_type == null)) return grant_type;
        else throw new RuntimeException("Grant type is not found in config.properties file");
    }

    public String getUser_id() {
        String user_id = properties.getProperty("user_id");
        if(!(user_id == null)) return user_id;
        else throw new RuntimeException("User ID is not found in config.properties file");
    }
}
