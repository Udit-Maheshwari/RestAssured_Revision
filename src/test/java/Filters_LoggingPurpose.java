import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Filters_LoggingPurpose {

    @Test
    public void filtersForLogging() {
        RestAssured.given()
                .baseUri("https://reqres.in")
                .pathParam("userId", "2")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter(LogDetail.STATUS))
                .when()
                .get("/api/users/{userId}")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void filtersForLoggingInFile() throws FileNotFoundException {
        // Print all the log in File
        PrintStream printStream = new PrintStream(new File("Filter.log"));
        RestAssured.given()
                .baseUri("https://reqres.in")
                .pathParam("userId", "2")
                .filter(new RequestLoggingFilter(printStream))
                .filter(new ResponseLoggingFilter(LogDetail.STATUS, printStream))
                .when()
                .get("/api/users/{userId}")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
