package Client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestClient {

    public Response SendGetRequest(String uri)
    {
        return given().when().get(uri);
    }


    public Response SendPostRequest(String uri, Object requestPayload)
    {
        return given().contentType(ContentType.JSON).when().body(requestPayload).post(uri);
    }


    public Response SendPutRequest(String uri, Object requestPayload)
    {
        return given().contentType(ContentType.JSON).when().body(requestPayload).put(uri);
    }


    public Response SendPatchRequest(String uri, Object requestPayload)
    {
        return given().contentType(ContentType.JSON).when().body(requestPayload).patch(uri);
    }


    public Response SendDeleteRequest(String uri)
    {
        return given().when().delete(uri);
    }
}
