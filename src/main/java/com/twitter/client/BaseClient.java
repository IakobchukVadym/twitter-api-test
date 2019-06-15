package com.twitter.client;

import com.google.common.collect.ImmutableMap;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import static com.twitter.properties.AppProperties.BASE_URL;

@Log4j2
public abstract class BaseClient {

    public Response get(RequestSpecification spec, String endpoint) {
        Response response = spec.get(endpoint);
        logRequest("GET", endpoint, response.getStatusCode());
        return response;
    }

    public Response post(RequestSpecification spec, String endpoint) {
        Response response = spec.post(endpoint);
        logRequest("POST", endpoint, response.getStatusCode());
        return response;
    }

    public Response post(RequestSpecification spec, String endpoint, ImmutableMap paramMap) {
        Response response = spec.post(endpoint, paramMap);
        String finalEndpoint = endpoint.replace("{id}", String.valueOf(paramMap.get("id")));
        logRequest("POST", finalEndpoint, response.getStatusCode());
        return response;
    }

    private void logRequest(String method, String endpoint, int code) {
        log.info(String.format("Sending %s request to %s" + "\n Response code: %s ",
                method, BASE_URL + endpoint, code));
    }
}
