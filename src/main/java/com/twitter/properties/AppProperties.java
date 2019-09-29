package com.twitter.properties;

import com.twitter.utils.Decryptor;

import java.util.Properties;

public class AppProperties {

    private static final String ENVIRONMENT = System.getProperty("env");
    private static final String APP_PROPERTIES_PATH = "config/" + ENVIRONMENT + "/app.properties";
    private static final Properties properties = LoadProperties.load(APP_PROPERTIES_PATH);

    private static final String PASSWORD = properties.getProperty("security.password");
    public static final String CONSUMER_KEY = Decryptor.decrypt(properties.getProperty("consumer.key"), PASSWORD);
    public static final String CONSUMER_SECRET = Decryptor.decrypt(properties.getProperty("consumer.secret"), PASSWORD);
    public static final String ACCESS_TOKEN = Decryptor.decrypt(properties.getProperty("access.token"), PASSWORD);
    public static final String ACCESS_SECRET = Decryptor.decrypt(properties.getProperty("access.secret"), PASSWORD);
    public static final String BASE_URL = properties.getProperty("url");

    private AppProperties() {
    }
}
