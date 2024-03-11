package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.requests.ClientRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ClientSteps extends BaseSteps{

    private final ClientRequest clientRequest = new ClientRequest();
    private Client client;

    @Given("there are registered clients in the system")
    public void thereAreRegisteredClientsInTheSystem() {

        response = clientRequest.getClients();
        //logger.info(response.jsonPath().prettify());

        Assert.assertEquals(200, response.getStatusCode());

        List<Client> clientList = clientRequest.getClientsEntity(response);
        if(clientList.isEmpty()){
            response = clientRequest.createDefaultClient();
           // logger.info(response.statusCode());
            Assert.assertEquals(201, response.getStatusCode());
        }
    }



    @Given("I have a client with the following details:")
    public void iHaveAClientWithTheFollowingDetails(DataTable clientData) {
        //logger.info("I have a client with the following details:" + clientData);

        Map<String, String > clientDataMap = clientData.asMaps().get(0);

        client = Client.builder()
                        .name(clientDataMap.get("Name"))
                        .lastName(clientDataMap.get("LastName"))
                        .country(clientDataMap.get("Country"))
                        .city(clientDataMap.get("City"))
                        .email(clientDataMap.get("Email"))
                        .phone(clientDataMap.get("Phone"))
                        .build();

        //logger.info("Client mapped: " + client);
    }

    @When("I retrieve the details of the client with ID {string}")
    public void sendGETRequest(String clientId) {
        logger.info("I retrieve the details of the client with ID " + clientId);
    }

    @When("I send a GET request to view all the clients")
    public void iSendAGETRequestToViewAllTheClient() {
        //logger.info("I send a GET request to view all the clients");

        response = clientRequest.getClients();
        //logger.info(response.jsonPath().prettify());
        //logger.info(response.statusCode());
    }

    @When("I send a POST request to create a client")
    public void iSendAPOSTRequestToCreateAClient() {
        //logger.info("I send a POST request to create a client");
        response = clientRequest.createClient(client);
    }

    @When("I send a DELETE request to delete the client with ID {string}")
    public void iSendADELETERequestToDeleteTheClientWithID(String clientId) {
        logger.info("I send a DELETE request to delete the client with ID " + clientId);
    }

    @When("I send a PUT request to update the client with ID {string}")
    public void iSendAPUTRequestToUpdateTheClientWithID(String clientId, String requestBody) {
        logger.info("I send a PUT request to update the client with ID " + requestBody + clientId);
    }


    @Then("the response should have the following details:")
    public void theResponseShouldHaveTheFollowingDetails(DataTable expectedData) {
        logger.info("the response should have the following details:" + expectedData);

        client = clientRequest.getClientEntity(response);
        Map<String, String> expectedDataMap = expectedData.asMaps().get(0);

        Assert.assertEquals(expectedDataMap.get("Name"), client.getName());
        Assert.assertEquals(expectedDataMap.get("LastName"), client.getLastName());
        Assert.assertEquals(expectedDataMap.get("Country"), client.getCountry());
        Assert.assertEquals(expectedDataMap.get("City"), client.getCity());
        Assert.assertEquals(expectedDataMap.get("Email"), client.getEmail());
        Assert.assertEquals(expectedDataMap.get("Phone"), client.getPhone());
        Assert.assertEquals(expectedDataMap.get("Id"), client.getId());
    }

    @Then("the response should include the details of the created client")
    public void theResponseShouldIncludeTheDetailsOfTheCreatedClient() {
        logger.info("the response should include the details of the created client");
        Client newCLient = clientRequest.getClientEntity(response);
        newCLient.setId(null);
        Assert.assertEquals(client, newCLient);
    }

    @Then("validates the response with client JSON schema")
    public void userValidatesResponseWithClientJSONSchema() {
        logger.info("validates the response with client JSON schema");

        String path = "schemas/clientSchema.json";
        Assert.assertTrue(clientRequest.validateSchema(response, path));
        logger.info("Successfully Validated schema from Client Object ");
    }

    @Then("validates the response with client list JSON schema")
    public void userValidatesResponseWithClientListJSONSchema() {

        String path = "schemas/clientListSchema.json";
        Assert.assertTrue(clientRequest.validateSchema(response, path));
        logger.info("Successfully Validated schema from Client List");
    }




}
