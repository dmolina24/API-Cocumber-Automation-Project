package com.testing.api.requests;

import com.google.gson.Gson;
import com.testing.api.models.Client;
import com.testing.api.utils.Constants;
import com.testing.api.utils.JsonFileReader;
import io.cucumber.messages.types.Hook;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRequest extends BaseRequest {
    private String endpoint;

    /**
     *
     * @return Response
     */
    public Response getClients() {
        endpoint = String.format(Constants.URL, Constants.CLIENTS_PATH);
        return requestGet(endpoint, createBaseHeaders());
    }

    public Response getClient(String clientId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.CLIENTS_PATH, clientId);
        return requestGet(endpoint, createBaseHeaders());
    }

    public Response createClient(Client client) {
        endpoint = String.format(Constants.URL, Constants.CLIENTS_PATH);
        return requestPost(endpoint, createBaseHeaders(), client);
    }

    public Response updateClient(Client client, String clientId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.CLIENTS_PATH, clientId);
        return requestPut(endpoint, createBaseHeaders(), client);
    }

    public Response deleteClient(String clientId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.CLIENTS_PATH, clientId);
        return requestDelete(endpoint, createBaseHeaders());
    }

    public Client getClientEntity(@NotNull Response response) {
        return response.as(Client.class);
    }

    public List<Client> getClientsEntity(@NotNull Response response) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("", Client.class);
    }

    public Response createDefaultClient() {
        JsonFileReader jsonFile = new JsonFileReader();
        return this.createClient(jsonFile.getClientByJson(Constants.DEFAULT_CLIENT_FILE_PATH));
    }

    public List<Response> createDefaultsClients(){
        JsonFileReader jsonFile = new JsonFileReader();
        List<Client> clients = jsonFile.getClientsByJson(Constants.DEFAULT_CLIENT_FILE_PATH);
        return clients.stream().map(this::createClient).collect(Collectors.toList());
    }

    public Client getClientEntity(String clientJson) {
        Gson gson = new Gson();
        return gson.fromJson(clientJson, Client.class);
    }
}
