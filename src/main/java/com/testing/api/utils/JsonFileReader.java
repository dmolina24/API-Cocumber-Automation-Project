package com.testing.api.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testing.api.models.Client;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonFileReader {
    /**
     * This method read a JSON file and deserialize the body into a Client object
     *
     * @param jsonFileName json file location path
     *
     * @return Client : client
     */
    public Client getClientByJson(String jsonFileName) {
        Client client = new Client();
        try (Reader reader = new FileReader(jsonFileName)) {
            Gson gson = new Gson();

            client = gson.fromJson(reader, Client.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    public List<Client> getClientsByJson(String  jsonFileName){
        List<Client> clients =  new ArrayList<>();
        try (Reader reader = new FileReader(jsonFileName)) {
            Gson gson = new Gson();
            Type clientListType = new TypeToken<List<Client>>() {}.getType();
            clients = gson.fromJson(reader, clientListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
