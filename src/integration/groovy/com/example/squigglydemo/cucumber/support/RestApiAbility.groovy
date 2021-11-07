package com.example.squigglydemo.cucumber.support

import wslite.rest.RESTClient

trait RestApiAbility {

    private final static RESTClient HTTP = new RESTClient("http://localhost:8080")

    String executeGet(String path, Map<String, String> query) {
        HTTP.get(
            path: path,
            query: query
        ).getContentAsString()
    }
}