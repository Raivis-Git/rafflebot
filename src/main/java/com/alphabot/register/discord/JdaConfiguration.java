package com.alphabot.register.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
public class JdaConfiguration {

    @Bean
    public JDABuilder jdaBuilder() {
        return JDABuilder.createDefault("MTI2NzY0NTQzNDgwNTg4Mjk3MQ.GWuQQt.oiyNwXoyx7I2VANIV79Bqqrc8ZIJykJTrRhfdI")
                .setActivity(Activity.playing("with Spring Boot"));
    }

    @Bean
    public JDA jda(JDABuilder jdaBuilder, List<ListenerAdapter> listeners) throws LoginException {
        JDA jda = jdaBuilder.build();
        listeners.forEach(jda::addEventListener);
        return jda;
    }
}
