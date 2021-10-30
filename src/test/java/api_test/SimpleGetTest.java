package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class SimpleGetTest{
    private static final Logger LOGGER = LogManager.getLogger(SimpleGetTest.class);

    @Test
    public void getAllUsers () {
        LOGGER.info(" ------- API Test: Read(Get) All Uses ");
        /// RestAssured uses to get the endpoint. Specifying the base url or the endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that you want to send to the server
        /// Now Building the httpRequest and create an object. Now create GET request to get response.
         RequestSpecification httpRequest = RestAssured.given();

        ///Now make the call to get response(make sure to use "io.restassured.response") and create an object
        // Make a request to the server and this will return the response.
        Response response = httpRequest.request(Method.GET);
        /// Logger uses to get the response body details during the test fail scenario.
        LOGGER.debug(response.getBody().asPrettyString());

        /// Now we have to validate the response with the web page. use Assertion for validation
        /// actual value = response & expected value = 200, which is comes from webpage
        Assert.assertEquals(response.getStatusCode(), 200);

        ///Jason path uses to Validate response body. and create object
        JsonPath jsonPath = response.jsonPath();
        /// data.email uses to get all info from evey users. we choose data.email coz it's used for array.
        ///  the Return type is a list and have to store in the list
        List<String> list = jsonPath.get("data.email");


        /// Validating individual email. in the list, contains uses to validate true of false. and boolean needs to added.
        String emailId = "george.bluth@reqres.in";
        boolean emailExist = list.contains(emailId);
        /// Assert used for validating the boolean condition. the message added to find out if it fails.
        Assert.assertTrue(emailExist, emailId + " does not exist");

        LOGGER.info("--------End Test: Read(Get) All Users ------");
    }
}