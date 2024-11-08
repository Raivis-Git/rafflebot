package com.alphabot.register.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JdaConfiguration {

    @Value("${discord.token}")
    public String discordToken;

    @Bean
    public JDABuilder jdaBuilder() {
        return JDABuilder.createDefault(discordToken)
                .setActivity(Activity.playing("with Raffles"));
    }

    @Bean
    public JDA jda(JDABuilder jdaBuilder, List<ListenerAdapter> listeners) {
        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
        listeners.forEach(jda::addEventListener);
        return jda;
    }
}
