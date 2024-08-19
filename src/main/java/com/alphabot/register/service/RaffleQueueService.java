package com.alphabot.register.service;

import com.alphabot.register.dao.RaffleDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class RaffleQueueService {

    Logger LOGGER = LoggerFactory.getLogger(RaffleQueueService.class);

    private final BlockingQueue<RaffleDAO> raffleQueue = new LinkedBlockingQueue<>();

    public void addToRaffleQueue(RaffleDAO raffleDAO) {
        try {
            raffleQueue.put(raffleDAO);  // This will block if the queue is full
            LOGGER.info("Added to Queue: " + raffleDAO);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to add data to the raffle queue", e);
        }
    }

    public RaffleDAO takeFromRaffleQueue() {
        try {
            return raffleQueue.take();  // This will block until there's data to consume
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to retrieve data from the raffle queue", e);
        }
    }

    public Integer getQueueSize() {
        return raffleQueue.size();
    }

    public boolean hasData() {
        return !raffleQueue.isEmpty();
    }
}
