package com.alphabot.register.service;

import com.alphabot.register.dao.RaffleDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RaffleQueueConsumerService {


    Logger LOGGER = LoggerFactory.getLogger(RaffleQueueConsumerService.class);
    @Autowired
    private RaffleQueueService raffleQueueService;
    @Autowired
    private AlphaBotService alphaBotService;

    boolean isRunning = false;

    @Async("taskExecutor")
    public void startConsuming() {
        if (!isRunning) {
            try {
                while (raffleQueueService.hasData()) {
                    LOGGER.info("Task executor started running!");
                    isRunning = true;
                    RaffleDAO data = raffleQueueService.takeFromRaffleQueue();
                    process(data);
                }
            } finally {
                isRunning = false;
                LOGGER.info("Task executor stopped running!");
            }
        }
    }

    private void process(RaffleDAO raffleDAO) {
        alphaBotService.registerRaffle(raffleDAO.getSlug(), raffleDAO.getRaffleName());
        LOGGER.info("Processed by: " + Thread.currentThread().getName() + "--- Raffle: " + raffleDAO.getRaffleName());
    }

}
