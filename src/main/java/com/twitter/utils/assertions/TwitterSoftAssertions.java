package com.twitter.utils.assertions;

import com.twitter.model.response.exception.ErrorsItem;
import org.assertj.core.api.SoftAssertions;
import twitter4j.Status;

public class TwitterSoftAssertions extends SoftAssertions {

    public ErrorsItemAssert assertThat(ErrorsItem actual) {
        return proxy(ErrorsItemAssert.class, ErrorsItem.class, actual);
    }

    public StatusAssert assertThat(Status actual) {
        return proxy(StatusAssert.class, Status.class, actual);
    }
}
