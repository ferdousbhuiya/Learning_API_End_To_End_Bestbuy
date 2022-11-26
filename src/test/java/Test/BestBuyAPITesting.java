package Test;

import Request.RequestFactory;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class BestBuyAPITesting extends BaseTest{

    RequestFactory requestFactory= new RequestFactory();

    @Test
    public void verifyGetProduct()
    {
        extentReport.createTestCase("Verify Get Product");
        Response response = requestFactory.getAllProduct();
        extentReport.addLog(Status.INFO, response.asPrettyString());
        response.then().statusCode(200);

        //RestAssured.given().when().get("/products").then().statusCode(200);
    }

    @Test
    public void verifyGetProductWithSpecificID()
    {
        extentReport.createTestCase("Verify Get Product With Specific ID");
        Response response = requestFactory.getAllProduct();
        extentReport.addLog(Status.INFO, response.asPrettyString());
        response.then().statusCode(200);

        RestAssured.given().when().params("id", 43900).get("/products").then().log().all().statusCode(200);
    }

    @Test
    public void verifyGetProductWithLimit()
    {
        extentReport.createTestCase("Verify Get Porduct With Limit");
        RestAssured.given().when().params("$limit", 5).get("/products").then().log().all().statusCode(200);
    }

    @Test
    public void verifyPostProduct()
    {
       extentReport.createTestCase("Verify Add product with request payload as map");
        String requestPayload = "{\n" +
                "  \"name\": \"Nokia\",\n" +
                "  \"type\": \"Mobile Phone\",\n" +
                "  \"price\": 100,\n" +
                "  \"shipping\": 0,\n" +
                "  \"upc\": \"string\",\n" +
                "  \"description\": \"Button Mobile\",\n" +
                "  \"manufacturer\": \"Nokia\",\n" +
                "  \"model\": \"3306\",\n" +
                "  \"url\": \"string\",\n" +
                "  \"image\": \"string\"\n" +
                "}";

        RestAssured.given().contentType(ContentType.JSON)
                .body(requestPayload).when().post("/products")
                .then().statusCode(201).log().all();

        requestFactory.addProduct(requestPayload).then().log().all().statusCode(201);
    }
    @Test
    public void verifyPostProductWithPayloadAsObject()
    {
        extentReport.createTestCase("Verify Post Product With Payload As Object");
        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("name", "Nokia-3306");
        requestPayload.put("type", "Mobile Phone");
        requestPayload.put("price", 200);
        requestPayload.put("shipping", 0);
        requestPayload.put("upc", "String");
        requestPayload.put("description", "Nokia button phone");
        requestPayload.put("manufacturer", "Nokia Finland");
        requestPayload.put("model", "Nokia 3360");
        requestPayload.put("url", "Nokia.com");
        requestPayload.put("image", "String");


        RestAssured.given().contentType(ContentType.JSON)
                .body(requestPayload).when().post("/products")
                .then().statusCode(201).log().all();


        requestFactory.addProduct(requestPayload).then().log().all().statusCode(201);

    }

    @Test
    public void verifyUpdateProductWithPayloadAsObject()
    {
        extentReport.createTestCase("Verify Update Product With Payload As Object");
        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("name", "Nokia-3356");
        requestPayload.put("type", "Mobile Phone");
        requestPayload.put("price", 200);
        requestPayload.put("shipping", 0);
        requestPayload.put("upc", "String");
        requestPayload.put("description", "Nokia button phone");
        requestPayload.put("manufacturer", "Nokia Finland");
        requestPayload.put("model", "Nokia 3360");
        requestPayload.put("url", "Nokia.com");
        requestPayload.put("image", "String");


        int ProductId = RestAssured.given().contentType(ContentType.JSON)
                .body(requestPayload).when().post("/products")
                .then().extract().path("id");



        requestPayload.put("name", "Nokia-3360");
        requestPayload.put("type", "Mobile Phone");
        requestPayload.put("price", 210);
        requestPayload.put("shipping", 10);
        requestPayload.put("upc", "String");
        requestPayload.put("description", "Nokia phone");
        requestPayload.put("manufacturer", "Nokia Finland");
        requestPayload.put("model", "Nokia 3360");
        requestPayload.put("url", "Nokia.com");
        requestPayload.put("image", "String");


        RestAssured.given().contentType(ContentType.JSON)
                .body(requestPayload).when().put("/products/"+ProductId)
                .then().statusCode(200).log().all();

    }

    @Test
    public void verifyDeleteProductWithPayloadAsObject()
    {
        extentReport.createTestCase("Verify Delete Product With Payload AS Object");
        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("name", "Nokia-3356");
        requestPayload.put("type", "Mobile Phone");
        requestPayload.put("price", 200);
        requestPayload.put("shipping", 0);
        requestPayload.put("upc", "String");
        requestPayload.put("description", "Nokia button phone");
        requestPayload.put("manufacturer", "Nokia Finland");
        requestPayload.put("model", "Nokia 3360");
        requestPayload.put("url", "Nokia.com");
        requestPayload.put("image", "String");

// Add a product
        int ProductId = RestAssured.given().contentType(ContentType.JSON)
                .body(requestPayload).when().post("/products")
                .then().extract().path("id");

// Then delete that product by getting the Product ID

        RestAssured.given().contentType(ContentType.JSON)
                .when().delete("/products/"+ProductId)
                .then().statusCode(200).log().all();
  // Again check that product is available or not by the specific product id

        RestAssured.given().when().get("/products/"+ProductId).then().statusCode(404);
    }
}
