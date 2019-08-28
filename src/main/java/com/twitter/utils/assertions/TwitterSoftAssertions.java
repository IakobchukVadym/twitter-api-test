package com.twitter.utils.assertions;

import org.assertj.core.api.SoftAssertions;

public class TwitterSoftAssertions extends SoftAssertions {

    public ErrorsItemAssert assertThat(com.twitter.model.exception.ErrorsItem actual) {
        return proxy(ErrorsItemAssert.class, com.twitter.model.exception.ErrorsItem.class, actual);
    }
}
