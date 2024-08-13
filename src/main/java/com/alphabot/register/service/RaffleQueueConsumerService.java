package com.alphabot.register.service;

import com.alphabot.register.dao.RaffleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RaffleQueueConsumerService {

    @Autowired
    private RaffleQueueService raffleQueueService;
    @Autowired
    private AlphaBotService alphaBotService;

    @Async("taskExecutor")
    public void startConsuming() {
        while (true) {
            RaffleDAO data = raffleQueueService.takeFromRaffleQueue();
            process(data);
        }
    }

    private void process(RaffleDAO raffleDAO) {
        alphaBotService.registerRaffle(raffleDAO.getSlug(), raffleDAO.getRaffleName());
        System.out.println("Processed by: " + Thread.currentThread().getName());
    }

}
