package com.twitter.model.response.error;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class TwitterError {

    @JsonProperty("errors")
    private List<ErrorsItem> errors;

    public List<ErrorsItem> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "errors = '" + errors + '\'' +
                        "}";
    }
}