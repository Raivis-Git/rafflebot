package com.alphabot.register;

import com.alphabot.register.discord.DiscordMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class RegisterApplication {

	public static void main(String[] args) {
//		DiscordMain discordMain = new DiscordMain();
//		discordMain.initJDA();

		SpringApplication.run(RegisterApplication.class, args);
	}

}
