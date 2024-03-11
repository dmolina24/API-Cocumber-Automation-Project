package com.testing.api.requests;

import com.testing.api.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class BaseRequest {

    /**
     * Lunch a get request in order to retrieve data
     * @param endpoint
     * @param headers
     * @return Response with status code and data also others parameters
     */
    protected Response requestGet(String endpoint, Map<String, ?> headers) {
        return RestAssured.given()
                          .contentType(Constants.VALUE_CONTENT_TYPE)
                          .headers(headers)
                          .when()
                          .get(endpoint);
    }

    /**
     * This is a function to create a new element using rest assured
     * @param endpoint api url
     * @param headers a map of headers
     * @param body model object
     * @return Response with status code and data also others parameters
     */
    protected Response requestPost(String endpoint, Map<String, ?> headers, Object body) {
        return RestAssured.given()
                          .contentType(Constants.VALUE_CONTENT_TYPE)
                          .headers(headers)
                          .body(body)
                          .when()
                          .post(endpoint);
    }

    /**
     * Lunch a put request in order to update data
     * @param endpoint
     * @param headers
     * @param body
     * @return Response with status code and data also others parameters
     */
    protected Response requestPut(String endpoint, Map<String, ?> headers, Object body) {
        return RestAssured.given()
                          .contentType(Constants.VALUE_CONTENT_TYPE)
                          .headers(headers)
                          .body(body)
                          .when()
                          .put(endpoint);
    }

    /**
     * Delete an entity register
     * @param endpoint
     * @param headers
     * @return Response with status code and data also others parameters
     */
    protected Response requestDelete(String endpoint, Map<String, ?> headers) {
        return RestAssured.given()
                          .contentType(Constants.VALUE_CONTENT_TYPE)
                          .headers(headers)
                          .when()
                          .delete(endpoint);
    }

    /**
     * Set up the header to new HTTP request
     * @return headers
     */
    protected Map<String, String> createBaseHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.CONTENT_TYPE, Constants.VALUE_CONTENT_TYPE);
        return headers;
    }

    /**
     * Validate the schema file format with the answer on the response
     * @param response http response class
     * @param schemaPath string location path
     * @return boolen if the response's format is similar to schema file
     */
    public boolean validateSchema(Response response, String schemaPath) {
        try {
            response.then()
                    .assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
            return true; // Return true if the assertion passes
        } catch (AssertionError e) {
            // Assertion failed, return false
            return false;
        }
    }
}
