package com.twitter.utils;

import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public final class CommonMethods {

    private CommonMethods() {
    }

    public static String getRandomString(int len) {
        return randomAlphanumeric(len);
    }

    public static int getRandInt(int max) {
        return ThreadLocalRandom.current().nextInt(1, max + 1);
    }
}
