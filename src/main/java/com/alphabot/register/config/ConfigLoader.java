//package com.alphabot.register.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//@Configuration
//public class ConfigLoader {
//    private final Properties properties = new Properties();
//
//    Logger LOGGER = LoggerFactory.getLogger(ConfigLoader.class);
//
//    public ConfigLoader() {
//        String env = System.getProperty("env", "dev");
//        String propertiesFileName = "application-" + env + ".properties";
//
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
//            if (input == null) {
//                LOGGER.info("Sorry, unable to find " + propertiesFileName);
//                return;
//            }
//
//            // Load the properties file
//            properties.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public String getProperty(String key) {
//        return properties.getProperty(key);
//    }
//
//    public String getAlphabotAuthentication() {
//        return this.getProperty("alphabot.authentication");
//    }
//}
