package main.java.Common;

import java.security.SecureRandom;

public class CommonMethods {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String getRandomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public static int getRandInt(int min, int max) {
        int i = min + (int) (Math.random() * ((max - min) + 1));
        return i;
    }
}
