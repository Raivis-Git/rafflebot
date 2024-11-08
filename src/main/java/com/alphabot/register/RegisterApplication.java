package com.alphabot.register;

import io.github.cdimascio.dotenv.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
@EnableAsync
public class RegisterApplication {

	public static void main(String[] args) {
		loadEnvVariables();
		SpringApplication.run(RegisterApplication.class, args);
	}

	private static void loadEnvVariables() {
		Dotenv.configure()
				.directory(".")
				.filename("application.env")
				.ignoreIfMissing()
				.load()
				.entries()
				.forEach(e -> System.setProperty(e.getKey(), e.getValue()));
	}

}
