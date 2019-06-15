package com.twitter.properties;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class LoadProperties {
    private LoadProperties() {
    }

    public static Properties load(String path) {
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = LoadProperties.class.getClassLoader()
                    .getResourceAsStream(path);

            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
            } else {
                throw new IllegalArgumentException(
                        "Please add existing env path property to run the tests like \"-Denv=prod\" \n" +
                                "Your current path is -Denv=" + path);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return properties;
    }
}
