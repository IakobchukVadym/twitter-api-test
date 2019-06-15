package com.twitter.utils.properties;

import java.util.Properties;

import static com.twitter.utils.properties.LoadProperties.load;

public class AppProperties {
   public static final String ENVIRONMENT = System.getProperty("env");
   public static final String APP_PROPERTIES_PATH = "config/" + ENVIRONMENT + "/app.Properties";
   private static Properties properties = load(APP_PROPERTIES_PATH);

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
