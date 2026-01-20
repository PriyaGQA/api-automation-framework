package com.qa.constants;

public class FrameworkConstants {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    
    public static final String CONFIG_FILE_PATH =
            System.getProperty("user.dir")
            + "/src/test/resources/config.properties";
    
    public static final String BASE_URI = "base.uri";
    
    public static final String AUTH_TOKEN = "auth.token";



    private FrameworkConstants() {
        // prevent object creation
    }
}
