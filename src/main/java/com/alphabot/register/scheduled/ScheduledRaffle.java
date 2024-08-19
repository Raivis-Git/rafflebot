package com.alphabot.register.scheduled;

import com.alphabot.register.dao.RaffleDAO;
import com.alphabot.register.integration.alphabot.Alphabot;
import com.alphabot.register.integration.alphabot.dto.Raffle;
import com.alphabot.register.integration.alphabot.dto.RaffleData;
import com.alphabot.register.service.RaffleQueueConsumerService;
import com.alphabot.register.service.RaffleQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ScheduledRaffle {

    @Autowired
    Alphabot alphabot;
    @Autowired
    RaffleQueueConsumerService raffleQueueConsumerService;
    @Autowired
    RaffleQueueService raffleQueueService;

    boolean started = false;


    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledRaffle.class);

    @Scheduled(fixedRate = 900, timeUnit = TimeUnit.SECONDS)
    public void scheduleLatestRaffles() {
        LOGGER.info("Scheduled latest raffle registration");
        Raffle raffle = alphabot.getLatestRaffles("10");
        for (RaffleData raffleData : raffle.getData().getRaffles()) {
            String slug = raffleData.getSlug();
            if (slug == null) {
                LOGGER.info("Slug is null from schedule");
                continue;
            }

            raffleQueueService.addToRaffleQueue(new RaffleDAO(slug,raffleData.getName()));
        }
        if (!started) {
            started = true;
            raffleQueueConsumerService.startConsuming();
        }
        LOGGER.info("Active threads: " + Thread.activeCount() + "\n" +
                "Queue size: " + raffleQueueService.getQueueSize());
    }

}
