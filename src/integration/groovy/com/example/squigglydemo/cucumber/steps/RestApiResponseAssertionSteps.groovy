package com.example.squigglydemo.cucumber.steps

import com.example.squigglydemo.cucumber.support.RestApiAbility
import io.cucumber.java.en.Then
import org.skyscreamer.jsonassert.JSONAssert

class RestApiResponseAssertionSteps implements RestApiAbility {

    @Then("GET {string} should return")
    void checkGetResponse(String path, String expected) {
        def actual = executeGet(path, [:])
        JSONAssert.assertEquals(expected, actual, true)
    }

    @Then("GET {string} with {string} fields filter should return")
    void checkGetResponse(String path, String fields, String expected) {
        def actual = executeGet(path, [fields: fields])
        JSONAssert.assertEquals(expected, actual, true)
    }
}
