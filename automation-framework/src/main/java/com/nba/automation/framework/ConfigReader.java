package com.nba.automation.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties; 

public class ConfigReader{
	public static Properties properties = new Properties();
    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties file not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}

