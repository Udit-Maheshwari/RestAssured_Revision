package PojoClass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class MockServerClassUsingMockPojoClass {

    @Test
    public void desearazlitation_SearazilizationDemo() throws JsonProcessingException {
        MockPojoClass mockPojoClass = new MockPojoClass();
        mockPojoClass.setName("morpheus");
        mockPojoClass.setJob("leader");

        MockPojoClass desearilization = RestAssured.given()
                .baseUri("https://7dad44a6-8bb0-4a1e-9f5f-84da672df86c.mock.pstmn.io")
                .body(mockPojoClass)
                .when().
                post("/postRequest")
                .then()
                .log()
                .all()
                .extract()
                .response()
                .as(MockPojoClass.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String serStr = objectMapper.writeValueAsString(mockPojoClass);
        String desStr = objectMapper.writeValueAsString(desearilization);
        MatcherAssert.assertThat(objectMapper.readTree(desStr), Matchers.equalTo(objectMapper.readTree(serStr)));
    }
}
