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


public class GetTestWithPathVariables {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithPathVariables.class);

    @Test
    public void getSingleUser() {
        LOGGER.info("--------API Test: Read(Get) Single user with path variables------");

        // RestAssured uses to get the endpoint. Specifying the base url or the endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        /// Now Building the request and create an object. Now create a GET request to get response.
        // Get the RequestSpecification of the request that you want to send to the server
        RequestSpecification httpRequest = RestAssured.given();

        ///Now make the call to get response(make sure to use "io.restassured.response") and create an object
        /// Make a request to the server and this will return the response.
        ///String and the variable "invalidId" created to pass an Invalid value and store the value
        String id = "2";
        Response response = httpRequest.request(Method.GET, id);
        /// Logger uses to get the response body details during the test fail scenario.
        LOGGER.debug(response.getBody().asPrettyString());

        /// Now we have to validate the response with the web page. use Assertion for validation
        /// actual value = response & expected value = 200, which is comes from webpage
        Assert.assertEquals(response.getStatusCode(), 200);

        ///Jason path uses to Validate response body. and create object
        JsonPath jsonPath = response.jsonPath();

        //Validating the specified email exist in the response body
        /// data.email uses to get all info under single user. we choose data.email coz it's used for array.
        String expectedEmailId = "janet.weaver@reqres.in";
        String actualEmailID = jsonPath.getString("data.email");
        Assert.assertEquals(expectedEmailId, actualEmailID);
        LOGGER.info("--------End Test: Read(Get) Single user with path variables ------");
    }

    @Test
    public void attemptToGetUserWithInvalidId() {
        LOGGER.info("--------API Test: Attempt to retrieve User with invalid ID------");

        /// RestAssured uses to get the endpoint. Specifying the base url or the endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        /// Now Building the request and create an object. Now create a GET request to get response.
        // Get the RequestSpecification of the request that you want to send to the server
        RequestSpecification httpRequest = RestAssured.given();

        ///Now make the call to get response(make sure to use "io.restassured.response") and create an object
        /// Make a request to the server and this will return the response.
        ///String and the variable "invalidId" created to pass an Invalid value and store the value
        String invalidId = "23";
        Response response = httpRequest.request(Method.GET, invalidId);
        /// Logger uses to get the response body details during the test fail scenario.
        LOGGER.debug(response.getBody().asPrettyString());

        /// Now we have to validate the response with the web page. use Assertion for validation
        /// actual value = response & expected value = 404, which is comes from webpage
        Assert.assertEquals(response.getStatusCode(), 404);

        ///Jason path uses to Validate response body. and create object
        JsonPath jsonPath = response.jsonPath();
        /// jsonPath.get(data.email) uses to get all info from evey users. we choose data.email coz it's used for array.
        ///  the Return type is a list and have to store in the list
        List<String> list = jsonPath.get("date.email");

        /// Assert used for validating the boolean condition.get().toString() method uses to get response.
        ///The expected value is {} and its 2 character.
        Assert.assertEquals(jsonPath.get().toString(), "{}");

        LOGGER.info("--------End Test: Read(Get)  Attempt to retrieve User with invalid ID ------");

    }
}
