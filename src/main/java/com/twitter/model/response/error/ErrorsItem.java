package com.twitter.model.response.error;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ErrorsItem {

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return
                "ErrorsItem{" +
                        "code = '" + code + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}