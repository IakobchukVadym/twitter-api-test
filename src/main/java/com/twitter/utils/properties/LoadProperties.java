package com.twitter.utils.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
   private LoadProperties() {
   }

   public static Properties load(String path) {
      Properties properties = new Properties();
      try {
         InputStream resourceAsStream =
            LoadProperties.class.getClassLoader().getResourceAsStream(path);
         if (resourceAsStream != null) {
            properties.load(resourceAsStream);
         } else
            throw new IllegalArgumentException(
               String.format("%s path to properties file is not correct", path));
      } catch (IOException e) {
         e.printStackTrace();
      }
      return properties;
   }
}
