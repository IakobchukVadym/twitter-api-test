package com.twitter.utils;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public final class CommonMethods {

    private CommonMethods() {
    }

    public static String getRandomString(int len) {
        String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(symbols.charAt(rnd.nextInt(symbols.length())));
        }
        return sb.toString();
    }

    public static int getRandInt(int max) {
        return ThreadLocalRandom.current().nextInt(1, max + 1);
    }
}
