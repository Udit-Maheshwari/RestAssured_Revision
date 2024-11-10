import PojoClass.CreateUser_Nested;
import PojoClass.CreateUser_Parent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateUserTestClass {

    @Test(dataProvider = "userData")
    public void createNewUser(String name, String job) throws JsonProcessingException {
        CreateUser_Nested createUserNested = new CreateUser_Nested();
        createUserNested.setName(name);
        createUserNested.setJob(job);

        CreateUser_Parent createUserParent = new CreateUser_Parent();
        createUserParent.setDescription(createUserNested);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(createUserParent));

        Response response = RestAssured.given()
                .baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .body(createUserParent)
                .when()
                .post();

        response.prettyPrint();
        CreateUser_Parent createUserParent1 = response.as(CreateUser_Parent.class);
        String userName = createUserParent1.getDescription().getName();
        System.out.println("Name is ==> "+userName);
       String value =  response.jsonPath().get("description.name");
        System.out.println("Value is ==> "+value);

        MatcherAssert.assertThat(value, Matchers.equalTo(userName));
    }

    // try to use Data Provide to provide multiple value
    @DataProvider(name = "userData")
    public String[][] getUserData() {
        return new String[][] {
                {"Udit", "QA"},
                {"Aman", "Leader"},
                {"Ananya", "Manager"}
        };
    }
}
