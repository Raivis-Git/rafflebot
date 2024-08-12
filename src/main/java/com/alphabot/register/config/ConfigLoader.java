package com.alphabot.register.config;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class ConfigLoader {
    private final Properties properties = new Properties();

    public ConfigLoader() {
        String env = System.getProperty("env", "dev");
        String propertiesFileName = "application-" + env + ".properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + propertiesFileName);
                return;
            }

            // Load the properties file
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getAlphabotAuthentication() {
        return this.getProperty("alphabot.authentication");
    }
}
