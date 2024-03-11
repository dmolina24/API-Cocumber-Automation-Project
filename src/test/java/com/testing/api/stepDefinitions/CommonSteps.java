package com.testing.api.stepDefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CommonSteps extends BaseSteps {

    @Then("the response should have a status code of {int}")
    public void theResponseShouldHaveAStatusCodeOf(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }
}
