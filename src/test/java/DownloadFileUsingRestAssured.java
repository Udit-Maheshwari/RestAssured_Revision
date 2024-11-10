import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.*;

public class DownloadFileUsingRestAssured {

    @Test
    public void downloadFile() throws IOException {
        // https://raw.githubusercontent.com/appium/appium/refs/heads/master/packages/appium/sample-code/apps/ApiDemos-debug.apk

       byte[] bytes = RestAssured.given()
                .baseUri("https://raw.githubusercontent.com")
                .when()
                .get("/appium/appium/refs/heads/master/packages/appium/sample-code/apps/ApiDemos-debug.apk")
                .then()
                .log().all()
                .extract()
                .response().asByteArray();

       // convert bytes to File

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        os.write(bytes);
        os.close();

    }
}
