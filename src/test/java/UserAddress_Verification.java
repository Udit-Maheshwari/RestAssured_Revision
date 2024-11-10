import PojoClass.UserAddress_Geo_Nested;
import PojoClass.UserAddress_Nested;
import PojoClass.UserAddress_Parent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class UserAddress_Verification {

    @Test
    public void createUserAddress() throws JsonProcessingException {
        UserAddress_Geo_Nested userAddressGeoNested = new UserAddress_Geo_Nested();
        userAddressGeoNested.setLat("-37.3159");
        userAddressGeoNested.setLng("81.1496");

        UserAddress_Nested userAddressNested = new UserAddress_Nested();
        userAddressNested.setStreet("Kulas Light");
        userAddressNested.setSuite("Apt. 556");
        userAddressNested.setCity("Gwenborough");
        userAddressNested.setZipcode("92998-3874");
        userAddressNested.setGeo(userAddressGeoNested);

        UserAddress_Parent userAddressParent = new UserAddress_Parent();
        userAddressParent.setName("Udit Maheswari");
        userAddressParent.setUsername("Udit1997");
        userAddressParent.setEmail("xyz@example.com");
        userAddressParent.setAddress(userAddressNested);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(userAddressParent));

        Response response = RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(userAddressParent)
                .when()
                .post("/users");

        response.prettyPrint();

        MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(201));
        MatcherAssert.assertThat(response.contentType(), Matchers.equalTo("application/json; charset=utf-8"));
        MatcherAssert.assertThat(response.jsonPath().getString("id"), Matchers.notNullValue());
    }
}
