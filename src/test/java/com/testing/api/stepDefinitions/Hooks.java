package com.testing.api.stepDefinitions;

import com.testing.api.utils.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void testStart(Scenario scenario) {
        logger.info("*****************************************************************************************");
        logger.info("	Start Scenario: " + scenario.getName());
        logger.info("*****************************************************************************************");
        RestAssured.baseURI = Constants.BASE_URL;
        BaseSteps.response = null;
    }

    @After
    public void cleanUp(Scenario scenario){
        logger.info("*****************************************************************************************");
        logger.info("   End Scenario: "+ scenario.getName());
        logger.info("*****************************************************************************************");
        BaseSteps.response = null;
    }
}
