package org.group32.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
