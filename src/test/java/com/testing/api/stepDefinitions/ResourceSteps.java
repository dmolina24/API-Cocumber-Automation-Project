package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.models.Resource;
import com.testing.api.requests.BaseRequest;
import com.testing.api.requests.ResourceRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ResourceSteps extends BaseSteps {

    private final ResourceRequest resourceRequest = new ResourceRequest();
    private Resource resource;

    @Given("there are registered resources in the system")
    public void thereAreRegisteredResourcesInTheSystem(){

        response = resourceRequest.getResources();
        logger.info(response.jsonPath().prettify());
        Assert.assertEquals(200, response.getStatusCode());

        List<Resource> resourceList = resourceRequest.getResourcesEntity(response);

        if(resourceList.isEmpty()){
            List<Response> responses = resourceRequest.createDefaultsResources();
            responses.parallelStream().forEach( response -> {
                Assert.assertEquals(201, response.getStatusCode());
            } );
        }
    }

    @Given("I send a GET request to view all the resources")
    public void iSendGETRequestToViewAllTheResources(){
        response = resourceRequest.getResources();
    }

    @Given("I retrieve the details of the latest resource")
    public void retrieveLatestResourceDetails(){

        response = resourceRequest.getResources();
        Assert.assertEquals(200, response.getStatusCode());
        List<Resource> resourcesList = resourceRequest.getResourcesEntity(response);
        resource = resourcesList.get(resourcesList.size() - 1 );
        logger.info(resource);
    }

    @Given("I send a PUT request to update the latest resource")
    public void iSendAPUTRequestToUpdateTheResourceWithID(String requestBody){
        String resourceId = resource.getId();
        resource = resourceRequest.getResourceEntity(requestBody);
        response = resourceRequest.updateResource(resource, resourceId);
    }

    @Given("validates the response with the resource list JSON schema")
    public void userValidatesResponseWithResourceListJSONSchema(){
        String path = "schemas/resourceListSchema.json";
        Assert.assertTrue(resourceRequest.validateSchema(response, path));
        logger.info("Successfully validated schema from resource list");
    }

    @Given("validates the response with the resource JSON schema")
    public void userValidatesResponseWithResourceJSONSchema(){
        String path = "schemas/resourceSchema.json";
        Assert.assertTrue(resourceRequest.validateSchema(response, path));
        logger.info("Successfully validated schema from resource");
    }

    @Then("the resource response should have the following details:")
    public void theResourceResponseShouldHaveTheFollowingDetails(DataTable expectedData) {
        logger.info("the response should have the following details:" + expectedData);

        resource = resourceRequest.getResourceEntity(response);
        Map<String, String> expectedDataMap = expectedData.asMaps().get(0);

        Assert.assertEquals(expectedDataMap.get("name"), resource.getName());
        Assert.assertEquals(expectedDataMap.get("trademark"), resource.getTrademark());
        Assert.assertEquals(Integer.parseInt(expectedDataMap.get("stock")), resource.getStock());
        Assert.assertEquals(Float.parseFloat(expectedDataMap.get("price")), resource.getPrice(), 0);
        Assert.assertEquals(expectedDataMap.get("description"), resource.getDescription());
        Assert.assertEquals(expectedDataMap.get("tags"), resource.getTags());
        Assert.assertEquals(Boolean.parseBoolean(expectedDataMap.get("active")), resource.isActive());
    }

}
