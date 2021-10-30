package api_test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplePatchTest {
    private static final Logger LOGGER = LogManager.getLogger(SimplePatchTest.class);

    @Test
    public void updateUserSingleField () {
        LOGGER.info(" ------- API Test: Patch(Update)  Update user's single field");

        // RestAssured uses to get the endpoint. Specifying the base url or the endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that you want to send to the server
        /// Now Building the httpRequest and create an object. Now create GET request to get response.
        RequestSpecification httpRequest = RestAssured.given();

        ///Build requestBody and pass it. Faker is a library that we added now use it and  create an obj.
        Faker faker = new Faker();
        /// In the web page, request body used job. so we will create variable and use the request body to update job.
        /// Use logger to have the info
        String position = faker.job().position();
        LOGGER.debug(" User job: " + position);

        /// Now Building the requestBody by using Json library. Put used for key value = (name and job).
        JSONObject reqBody = new JSONObject();
        reqBody.put("job", position);

        /// Now go to postman app and paste the https://reqres.in/api/users. change "GET" to "POST"
        /// Click on "BODY" then "Raw". Now change "Text" to "Json" then go to the webPage and copy the value of request
        /// Paste it in the box of RAW under postman. Now Click on "Headers" and click "Hidden"
        /// Look for Content-Type, and It's value and copy them and paste them under httpRequest
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(reqBody.toJSONString());

        /// add a String obj to specify the users that needs to update.
        String id = "2";
        Response response = httpRequest.request(Method.PATCH, id);
        LOGGER.debug(response.getBody().asPrettyString());

        //Now Validating
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPath = response.jsonPath();
        String actualJob = jsonPath.getString("job");
        Assert.assertEquals(actualJob, position);

        LOGGER.info(" ------- End Test: Patch(Update)  Update user's single field");
    }
}
