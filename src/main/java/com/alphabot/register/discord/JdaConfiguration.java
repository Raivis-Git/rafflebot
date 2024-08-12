package com.alphabot.register.discord;

import com.alphabot.register.config.ConfigLoader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
public class JdaConfiguration {

    ConfigLoader configLoader = new ConfigLoader();

    @Bean
    public JDABuilder jdaBuilder() {
        return JDABuilder.createDefault(configLoader.getProperty("discord.token"))
                .setActivity(Activity.playing("with Raffles"));
    }

    @Bean
    public JDA jda(JDABuilder jdaBuilder, List<ListenerAdapter> listeners) throws LoginException {
        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
        listeners.forEach(jda::addEventListener);
        return jda;
    }
}
