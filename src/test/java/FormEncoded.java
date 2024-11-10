import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import org.testng.annotations.Test;

public class FormEncoded {

    @Test
    public void formEncoded() {
        RestAssured.given()
                .baseUri("https://postman-echo.com")
                .given()
                .config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .log().all()
                .formParams("form1", "value1")
                .formParams("form 2", "value 2")
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
