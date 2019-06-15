package com.twitter.utils;

import java.security.SecureRandom;

public final class CommonMethods {

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

   private CommonMethods() {
   }

   public static String getRandomString(int len) {
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public static int getRandInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
