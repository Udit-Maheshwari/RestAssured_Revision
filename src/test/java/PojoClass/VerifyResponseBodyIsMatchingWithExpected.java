package PojoClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class VerifyResponseBodyIsMatchingWithExpected {

    @Test
    public void verifyResponseBodyASExpected() {
        ArrayList<EmployeeDetails> employeeList = new ArrayList<>();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setId(9500);
        employeeDetails.setUsername("Udit Maheshwari");
        employeeDetails.setFirstName("Udit");
        employeeDetails.setLastName("Maheshwari");
        employeeDetails.setEmail("xyz@gmail.com");
        employeeDetails.setPassword("123");
        employeeDetails.setPhoneNumber(1234567890);
        employeeList.add(employeeDetails);

        RequestSpecification requestSpecification = RestAssured.given();
        //https://petstore.swagger.io/v2/user/createWithList
        Response response = requestSpecification.baseUri("https://petstore.swagger.io/v2/user/createWithList")
                .contentType(ContentType.JSON).body(employeeList).post();

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);

        // verify Response Body is matching with expected or not
        String expPayLoad = "{\"code\":200,\"type\":\"unknown\",\"message\":\"ok\"}";
        System.out.println("Response body as string is :"+response.asString());
        boolean isResponseBodyMatching = response.asString().equals(expPayLoad);
        System.out.println("isResponseBodyMatching :"+isResponseBodyMatching);
        Assert.assertEquals(isResponseBodyMatching, true);
    }
}
