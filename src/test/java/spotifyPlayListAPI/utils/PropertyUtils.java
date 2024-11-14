package spotifyPlayListAPI.utils;

import java.io.*;
import java.util.*;

public class PropertyUtils {

    public static Properties propertyReader(String path) {
        Properties properties = new Properties();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            try {
                properties.load(reader);
                reader.close();
            } catch (Exception e) {
               e.printStackTrace();
                throw  new RuntimeException("Failed to load property" + path);
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("File Is not found" + path);
        }
        return properties;
    }

    public static Properties propertyWriter(String path, String playListId) {
        Properties properties = new Properties();

        PrintWriter writer;
        BufferedReader reader;
        Map<Object, Object> map = new HashMap<>();
        map.put("user_playlist_id", playListId);
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(path)));
            reader = new BufferedReader(new FileReader(path));
            try {
                properties.load(reader);
                writer.write("user_playlist_id=" + playListId);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw  new RuntimeException("Failed to Input property" + path);
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("File Is not found" + path);
        }

        return properties;
    }
}
