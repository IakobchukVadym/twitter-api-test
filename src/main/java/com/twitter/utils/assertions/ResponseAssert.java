package com.twitter.utils.assertions;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class ResponseAssert {
    private ResponseAssert() {
    }

    public static void assertResponseCode(Response response, int code) {
        Assertions.assertThat(response.getStatusCode())
                .as("Response status code should be qual to " + code)
                .isEqualTo(code);
    }
}
