package com.alphabot.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);  // Initial number of threads
        executor.setMaxPoolSize(2);  // Maximum number of threads
        executor.setQueueCapacity(10);  // Queue size before new threads are created
        executor.setThreadNamePrefix("RaffleQueueConsumer-");
        executor.setAllowCoreThreadTimeOut(true);
        executor.setKeepAliveSeconds(30); // Timeout idle threads
        executor.initialize();
        return executor;
    }

}
