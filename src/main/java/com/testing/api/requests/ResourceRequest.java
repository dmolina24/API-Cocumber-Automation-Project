package com.testing.api.requests;
import com.google.gson.Gson;
import com.testing.api.models.Resource;
import com.testing.api.utils.Constants;
import com.testing.api.utils.JsonFileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceRequest extends BaseRequest{
    private String endpoint;

    public Response getResources(){
        endpoint = String.format(Constants.URL, Constants.RESOURCES_PATH);
        return requestGet(endpoint, createBaseHeaders());
    }

    public Response getResource(String resourceId){
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.RESOURCES_PATH, resourceId );
        return  requestGet(endpoint, createBaseHeaders());
    }

    public Response createResource(Resource resource){
        endpoint = String.format(Constants.URL, Constants.RESOURCES_PATH);
        return requestPost(endpoint, createBaseHeaders(), resource);
    }

    public Response deleteResource(String clientId){
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.RESOURCES_PATH, clientId);
        return requestDelete(endpoint, createBaseHeaders());
    }

    public Response updateResource(Resource resource, String resourceId){
        endpoint = String.format(Constants.URL_WITH_PARAM,Constants.RESOURCES_PATH, resourceId);
        System.out.println(endpoint);
        return  requestPut(endpoint, createBaseHeaders(), resource);
    }

    public Resource getResourceEntity(String resourceJson){
        Gson gson = new Gson();
        return gson.fromJson(resourceJson, Resource.class);
    }

    public Resource getResourceEntity(@NotNull Response response){
        return response.as(Resource.class);
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


    public List<Response> createDefaultsResources(){
        JsonFileReader jsonFile = new JsonFileReader();
        List<Resource> resources = jsonFile.getResourcesByJson(Constants.DEFAULT_RESOURCE_FILE_PATH);
        return  resources.stream().map(this::createResource).collect(Collectors.toList());
    }


}
