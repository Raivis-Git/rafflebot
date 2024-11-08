package com.alphabot.register.config;

import io.github.cdimascio.dotenv.*;
import jakarta.annotation.*;
import org.slf4j.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    Logger logger = LoggerFactory.getLogger(EnvConfig.class);

    @PostConstruct
    public void init() {
        try {
            Dotenv dotenv = Dotenv.configure()
                    .filename("application.env")
                    .load();

            // Load environment variables
            dotenv.entries().forEach(e -> {
                System.setProperty(e.getKey(), e.getValue());
                logger.info("Loaded env variable: {} = {}", e.getKey(), e.getValue());
            });

        } catch (Exception e) {
            logger.error("Error loading application.env file: {}", e.getMessage());
        }
    }
}
