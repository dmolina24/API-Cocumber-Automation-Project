package com.testing.api.stepDefinitions;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseSteps {
    protected static final Logger logger = LogManager.getLogger(ClientSteps.class);
    protected static Response response;
}
