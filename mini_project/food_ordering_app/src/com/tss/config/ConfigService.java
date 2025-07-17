package com.tss.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {
    private static ConfigService instance;

    private ConfigService() {
    }

    /**
     * Gets the singleton instance of ConfigService.
     * @return The singleton instance.
     */
    public static ConfigService getInstance() {
        if (instance == null) {
            synchronized (ConfigService.class) {
                if (instance == null) {
                    instance = new ConfigService();
                }
            }
        }
        return instance;
    }

    /**
     * Loads properties from a specified file path.
     * @param filePath The path to the properties file.
     * @return The loaded Properties object.
     */
    public Properties loadProperties(String filePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        }
        return props;
    }
}