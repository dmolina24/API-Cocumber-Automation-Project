package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.requests.ClientRequest;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceSteps {
    private static final Logger logger = LogManager.getLogger(ClientSteps.class);

    private final ClientRequest clientRequest = new ClientRequest();

    private Response response;
    private Client client;

    @Given("there are registered resources in the system")
    public void thereAreRegisteredClientsInTheSystem(){

    }

    @Given("I send a GET request to view all the resources")
    public void iSendGETRequestToViewAllTheResources(){

    }

    @Given("validates the response with the resource list JSON schema")
    public void userValidatesResponseWithResourceListJSONSchema(){

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
