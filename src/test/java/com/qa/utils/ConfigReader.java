package com.qa.utils;

import java.io.FileInputStream;
import java.util.Properties;

import com.qa.constants.FrameworkConstants;

public class ConfigReader {

    private static Properties prop;

    static {
        try {
            FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir")
                + "/src/test/resources/config.properties"
            );
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("config.properties file not found");
        }
    }
    
    public static String getValue(String key) {
        String value = prop.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }
        return value;
    }
    
    public static String getAuthToken() {
        return getValue(FrameworkConstants.AUTH_TOKEN);
    }

}
