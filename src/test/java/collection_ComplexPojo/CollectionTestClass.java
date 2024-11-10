package collection_ComplexPojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionTestClass {

    @Test
    public void testComplexPojo() throws JsonProcessingException {
        Info info = new Info();
        info.setName("Sample Collection");
        info.setDescription("This is just a sample collection.");
        info.setSchema("https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        Header header = new Header();
        header.setKey("Content-Type");
        header.setValue("application/json");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Body body = new Body();
        body.setMode("raw");
        body.setRaw("{\"data\": \"123\"}");

        Request request = new Request();
        request.setURL("https://postman-echo.com/post");
        request.setMethod("POST");
        request.setDescription("This is a sample POST Request");
        request.setHeader(headerList);
        request.setBody(body);

        RequestRoot requestRoot = new RequestRoot();
        requestRoot.setName("Sample POST Request");
        requestRoot.setRequest(request);
        List<RequestRoot> requestRootList = new ArrayList<>();
        requestRootList.add(requestRoot);

        Item item = new Item();
        item.setName("This is a folder");
        item.setItem(requestRootList);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        CollectionNested collectionNested = new CollectionNested();
        collectionNested.setInfo(info);
        collectionNested.setItem(itemList);

        CollectionRoot collectionRoot = new CollectionRoot();
        collectionRoot.setCollection(collectionNested);

        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(collectionRoot);
        System.out.println("Request Body is ==>" + payload);

      Response response = RestAssured.given()
                .baseUri("https://api.getpostman.com")
                .body(collectionRoot)
              .contentType(ContentType.JSON)
              .header("X-API-Key", "PMAK-67219749c1b7b9000104a776-dd5639b4ed81e5b7a04b0c61f2f1d71631")
                .when()
                .post("/collections")
                .then()
                .extract().response();

      response.prettyPrint();

      String collectionUid = response.path("collection.uid");

      Response getResponse = RestAssured.given()
              .pathParam("collectionUid", collectionUid)
              .header("X-API-Key", "PMAK-67219749c1b7b9000104a776-dd5639b4ed81e5b7a04b0c61f2f1d71631")
              .when()
              .get("https://api.getpostman.com/collections/{collectionUid}")
              .then()
              .extract().response();

        System.out.println("Get Response is ==>" + getResponse.prettyPrint());
    }
}
