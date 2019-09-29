package com.twitter.utils.assertions;

import com.twitter.model.response.exception.ErrorsItem;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ErrorsItemAssert extends AbstractAssert<ErrorsItemAssert, ErrorsItem> {

    public ErrorsItemAssert(ErrorsItem actual) {
        super(actual, ErrorsItemAssert.class);
    }

    public static ErrorsItemAssert assertThat(ErrorsItem actual) {
        return new ErrorsItemAssert(actual);
    }

    public ErrorsItemAssert hasCode(int code) {
        Assertions.assertThat(actual.getCode())
                .as("Error code should be the same as expected")
                .isEqualTo(code);
        return this;
    }

    public ErrorsItemAssert hasMessage(String message) {
        Assertions.assertThat(actual.getMessage())
                .as("Error message should be the same as expected")
                .isEqualTo(message);
        return this;
    }
}
