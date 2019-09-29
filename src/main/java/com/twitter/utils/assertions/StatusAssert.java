package com.twitter.utils.assertions;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import twitter4j.Status;

public class StatusAssert extends AbstractAssert<StatusAssert, Status> {

    public StatusAssert(Status actual) {
        super(actual, StatusAssert.class);
    }

    public static StatusAssert assertThat(Status actual) {
        return new StatusAssert(actual);
    }

    public StatusAssert hasId(long id) {
        Assertions.assertThat(actual.getId())
                .as("Status should have id as expected")
                .isEqualTo(id);

        return this;
    }

    public StatusAssert hasText(String text) {
        Assertions.assertThat(actual.getText())
                .as("Status should have text as expected")
                .isEqualTo(text);

        return this;
    }

    public StatusAssert isRetweet(boolean isRetweet) {
        Assertions.assertThat(actual.isRetweet())
                .as("Status should have text as expected")
                .isEqualTo(isRetweet);

        return this;
    }

    public StatusAssert hasRetwetedStaus(Status status) {
        Assertions.assertThat(actual.getRetweetedStatus())
                .as("Status should have text as expected")
                .isEqualByComparingTo(status);

        return this;
    }
}
