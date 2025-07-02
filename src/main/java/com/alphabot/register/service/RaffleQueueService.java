package com.alphabot.register.service;

import com.alphabot.register.dao.RaffleDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class RaffleQueueService {

    Logger logger = LoggerFactory.getLogger(RaffleQueueService.class);

    private final ConcurrentLinkedQueue<RaffleDAO> raffleQueue = new ConcurrentLinkedQueue<>();

    public void addToRaffleQueue(RaffleDAO raffleDAO) {
        raffleQueue.offer(raffleDAO);
        logger.info("Added to Queue: {}", raffleDAO);
    }

    public RaffleDAO takeFromRaffleQueue() {
        RaffleDAO raffleDAO = raffleQueue.poll();
        logger.info("Took from Queue: {}", raffleDAO);
        return raffleDAO;
    }

    public Integer getQueueSize() {
        return raffleQueue.size();
    }

    public boolean hasData() {
        return !raffleQueue.isEmpty();
    }
}
