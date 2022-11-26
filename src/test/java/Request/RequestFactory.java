package Request;

import Client.RestClient;
import io.restassured.response.Response;

public class RequestFactory {

    RestClient restClient;
    public RequestFactory()
    {
        restClient = new RestClient();
    }

    public Response getAllProduct()
    {
        return restClient.SendGetRequest("/products");
    }

    public Response addProduct( Object requestPayload)
    {
        return restClient.SendPostRequest("/products", requestPayload);
    }
    public Response updateProduct( Object requestPayload)
    {
        return restClient.SendPutRequest("/products", requestPayload);
    }
}
