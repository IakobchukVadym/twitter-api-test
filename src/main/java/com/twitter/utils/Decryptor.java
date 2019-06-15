package com.twitter.utils;

import org.jasypt.util.text.BasicTextEncryptor;

public final class Decryptor {

    private Decryptor() {
    }

    public static String decrypt(String encryptedPassword, String password) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password);
        return textEncryptor.decrypt(encryptedPassword);
    }
}
