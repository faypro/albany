package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SimpleDeleteTest {
    private static final Logger LOGGER = LogManager.getLogger(SimpleDeleteTest.class);

    @Test
    public void deleteSingleUsers () {
        LOGGER.info("--------API Test: Delete User ------");
        /// RestAssured uses to get the endpoint. Specifying the base url or the endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that you want to send to the server
        /// Now Building the httpRequest and create an object. Now create GET request to get response.
        RequestSpecification httpRequest = RestAssured.given();

        ///Now make the call to get response(make sure to use "io.restassured.response") and create an object
        // Make a request to the server and this will return the response.
        ///Create string id to specify the user
        String id = "2";
        Response response = httpRequest.request(Method.DELETE, id);

        /// Now we have to validate the response with the web page. use Assertion for validation
        /// actual value = response & expected value = 200, which is comes from webpage
        Assert.assertEquals(response.getStatusCode(), 204);

        LOGGER.info("--------End Test: Delete User------");
    }
}
