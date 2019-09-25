package com.twitter.properties;

import java.util.Properties;

public class AppProperties {
    private static final String ENVIRONMENT = System.getProperty("env");
    private static final String APP_PROPERTIES_PATH = "config/" + ENVIRONMENT + "/app.properties";
    private static Properties properties = LoadProperties.load(APP_PROPERTIES_PATH);

    private AppProperties() {
    }

    public static String getConsumerKey() {
        return properties.getProperty("consumer.key");
    }

    public static String getConsumerSecret() {
        return properties.getProperty("consumer.secret");
    }

    public static String getAccessToken() {
        return properties.getProperty("access.token");
    }

    public static String getAccessSecret() {
        return properties.getProperty("access.secret");
    }

    public static String getSecurityPassword() {
        return properties.getProperty("security.password");
    }

    public static String getUrl() {
        return properties.getProperty("url");
    }
}
