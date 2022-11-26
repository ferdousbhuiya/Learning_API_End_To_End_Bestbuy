package Test;

import Request.RequestFactory;
import Utils.ExtentReportUtils;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ProductTests extends BaseTest{
ExtentReportUtils extentReportUtils= new ExtentReportUtils("Test Case Report");
RequestFactory requestFactory = new RequestFactory();


    @Test
    public void verifyGetProduct()
    {
        extentReport.createTestCase("Verify Get Product");
        Response response = requestFactory.getAllProduct();
        extentReport.addLog(Status.INFO, response.asPrettyString());
        response.then().statusCode(200);
    }
    @Test
    public void verifyAddProduct()
    {
        extentReportUtils.createTestCase("Verify Add Product");

        String requestPayload= "{\n" +
                "   \"name\":\"iPhone 14\",\n" +
                "   \"type\":\"Mobile Phone\",\n" +
                "   \"price\":1000,\n" +
                "   \"shipping\":0,\n" +
                "   \"upc\":\"string\",\n" +
                "   \"description\":\"string\",\n" +
                "   \"manufacturer\":\"string\",\n" +
                "   \"model\":\"string\",\n" +
                "   \"url\":\"string\",\n" +
                "   \"image\":\"string\"\n" +
                "}";
       requestFactory.addProduct(requestPayload).then().log().all().statusCode(201);


    }

    @Test
    public void verifyAddProductWithRequestsPayloadMap()
    {
        extentReportUtils.createTestCase("Verify Add product with request payload as map");


        Map<String, Object > requestPayload = new HashMap<>();
        requestPayload.put("name", "iPhone14");
        requestPayload.put("type", "Mobile phone");
        requestPayload.put("price", 1000);
        requestPayload.put("shipping", 10);
        requestPayload.put("upc", "iPhone14");
        requestPayload.put("description", "iPhone14");
        requestPayload.put("manufacturer", "Apple");
        requestPayload.put("model", "iPhone14");
        requestPayload.put("url", "iPhone14");
        requestPayload.put("image", "iPhone14");

        requestFactory.addProduct(requestPayload).then().log().all().statusCode(201);


    }

    @Test
    public void verifyEndToEndCallFlow()
    {
        extentReportUtils.createTestCase("verify EndToEnd Call Flow");



        // 1. Send a Post Request to Create a product

        Map<String, Object > requestPayload = new HashMap<>();
        requestPayload.put("name", "iPhone14");
        requestPayload.put("type", "Mobile phone");
        requestPayload.put("price", 1000);
        requestPayload.put("shipping", 10);
        requestPayload.put("upc", "iPhone14");
        requestPayload.put("description", "iPhone14");
        requestPayload.put("manufacturer", "Apple");
        requestPayload.put("model", "iPhone14");
        requestPayload.put("url", "iPhone14");
        requestPayload.put("image", "iPhone14");

        Response response = requestFactory.addProduct(requestPayload);
        response.then().log().all().statusCode(201);




        // Get ID of the product.

        // 2. Send a Get Request to verify if the product is added.

        // 3. Send a Put request to edit the porduct.

        // 4. Send a Delete request to delete the product.

        // 5 . Send a Get request to verify the delete.



    }
}
