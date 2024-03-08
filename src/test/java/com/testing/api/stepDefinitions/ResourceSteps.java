package com.testing.api.stepDefinitions;

import com.testing.api.models.Resource;
import com.testing.api.requests.BaseRequest;
import com.testing.api.requests.ResourceRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class ResourceSteps extends BaseSteps {

    private final ResourceRequest resourceRequest = new ResourceRequest();
    private Resource resource;

    @Given("there are registered resources in the system")
    public void thereAreRegisteredResourcesInTheSystem(){

        response = resourceRequest.getResources();
        //logger.info(response.jsonPath().prettify());
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Given("I send a GET request to view all the resources")
    public void iSendGETRequestToViewAllTheResources(){
        response = resourceRequest.getResources();
    }

    @Given("validates the response with the resource list JSON schema")
    public void userValidatesResponseWithResourceListJSONSchema(){
        String path = "schemas/resourceListSchema.json";
        Assert.assertTrue(resourceRequest.validateSchema(response, path));
        logger.info("Successfully validated schema from resource list");
    }

    @Given("I retrieve the details of the latest resource")
    public void retrieveLatestResourceDetails(){

    }

    @Given("I send a PUT request to update the latest resource")
    public void iSendAPUTRequestToUpdateTheResourceWithID(String resourceId, String requestBody){

    }

    @Given("validates the response with the resource JSON schema")
    public void userValidatesResponseWithResourceJSONSchema(){

    }


}
