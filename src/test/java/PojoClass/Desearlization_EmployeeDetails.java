package PojoClass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Desearlization_EmployeeDetails {

    @Test
    public void desearalization_EmpDetails() throws JsonProcessingException {
        String jsonObject;
      //  ArrayList<EmployeeDetails> employeeList = new ArrayList<>();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setId(9500);
        employeeDetails.setUsername("Udit Maheshwari");
        employeeDetails.setFirstName("Udit");
        employeeDetails.setLastName("Maheshwari");
        employeeDetails.setEmail("xyz@gmail.com");
        employeeDetails.setPassword("123");
        employeeDetails.setPhoneNumber(1234567890);
     //   employeeList.add(employeeDetails);

        ObjectMapper objectMapper = new ObjectMapper();
        jsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeDetails);

        RequestSpecification requestSpecification = RestAssured.given();
        //https://reqres.in/api/users
        Response response = requestSpecification.baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON).body(employeeDetails).post();

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 201);

       // EmployeeDetails empDetails = objectMapper.readValue(jsonObject, EmployeeDetails.class);
        EmployeeDetails empDetails = response.as(EmployeeDetails.class);
        System.out.println("ID is :"+empDetails.getId());
        System.out.println("User Full Name is :"+empDetails.getUsername());
        System.out.println("First Name is :"+empDetails.getFirstName());
        System.out.println("Last Name is :"+empDetails.getLastName());
        System.out.println("Email is :"+empDetails.getEmail());
        System.out.println("Password is :"+empDetails.getPassword());
        System.out.println("Mobile Number is :"+empDetails.getPhoneNumber());
    }
}
