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

    @Async("taskExecutor")
    public void startConsuming() {
        try {
            while (raffleQueueService.hasData()) {
                LOGGER.info("Task executor started running!");
                RaffleDAO data = raffleQueueService.takeFromRaffleQueue();
                processRaffle(data);
            }
        } finally {
            LOGGER.info("Task executor stopped running!");
        }
    }

    private void processRaffle(RaffleDAO raffleDAO) {
        try {
            alphaBotService.registerRaffle(raffleDAO.getSlug(), raffleDAO.getRaffleName());
            LOGGER.info("Processed by: " + Thread.currentThread().getName() + "--- Raffle: " + raffleDAO.getRaffleName());
        } catch (Exception e) {
            LOGGER.error("Failed to process raffle: " + raffleDAO.getSlug());
        }
    }

}
