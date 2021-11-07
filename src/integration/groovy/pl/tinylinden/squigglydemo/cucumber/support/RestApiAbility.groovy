package pl.tinylinden.squigglydemo.cucumber.support

import wslite.rest.RESTClient

trait RestApiAbility {

    private final static RESTClient HTTP = new RESTClient("http://localhost:8080")

    String executeGet(
            String path,
            Map<String, String> query,
            String accept = "application/vnd.example.public.v1+json") {
        HTTP.get(
                path: path,
                query: query,
                accept: accept,
        ).getContentAsString()
    }
}