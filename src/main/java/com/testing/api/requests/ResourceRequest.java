package com.testing.api.requests;
import com.google.gson.Gson;
import com.testing.api.models.Resource;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResourceRequest extends BaseRequest{
    private String endpoint;

    public Response getResources(){
        endpoint = "";
        return requestGet(endpoint, createBaseHeaders());
    }

    public Response getResource(){
        endpoint = "";
        return  requestGet(endpoint, createBaseHeaders());
    }

    public Response updateResource(Resource resource){
        endpoint = "";
        return  requestPut(endpoint, createBaseHeaders(), resource);
    }

    public Resource getResourceEntity(String resourceJson){
        Gson gson = new Gson();
        return gson.fromJson(resourceJson, Resource.class);
    }

    public List<Resource> getResourcesEntity(@NotNull Response response){
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("", Resource.class);
    }
}
