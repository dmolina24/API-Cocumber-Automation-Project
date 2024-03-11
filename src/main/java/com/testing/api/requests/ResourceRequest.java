package com.testing.api.requests;
import com.google.gson.Gson;
import com.testing.api.models.Client;
import com.testing.api.models.Resource;
import com.testing.api.utils.Constants;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResourceRequest extends BaseRequest{
    private String endpoint;

    public Response getResources(){
        endpoint = String.format(Constants.URL, Constants.RESOURCES_PATH);
        return requestGet(endpoint, createBaseHeaders());
    }

    public Response getResource(){
        endpoint = "";
        return  requestGet(endpoint, createBaseHeaders());
    }

    public Response updateResource(Resource resource, String resourceId){
        endpoint = String.format(Constants.URL, Constants.RESOURCES_PATH);
        return  requestPut(endpoint, createBaseHeaders(), resource);
    }

    public Resource getResourceEntity(String resourceJson){
        Gson gson = new Gson();
        return gson.fromJson(resourceJson, Resource.class);
    }

    public List<Resource> getResourcesEntity(@NotNull Response response){
        JsonPath jsonPath = response.jsonPath();
        try{
            List<Resource> list = jsonPath.getList("", Resource.class);
            return list;
        }catch (Exception e){
            System.out.println("exception" + e.toString());
            return null;
        }
    }


}
