package com.alphabot.register.controller;

import com.alphabot.register.controller.dto.RafflesActivePost;
import com.alphabot.register.service.AlphaBotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/raffles")
public class AlphaBotController {

    Logger logger = LoggerFactory.getLogger(AlphaBotController.class);
    @Autowired
    AlphaBotService alphaBotService;

    @PostMapping(consumes ="application/json")
    public ResponseEntity<?> postRaffles(@RequestBody RafflesActivePost rafflesActivePost) {
        logger.info("Received a raffle post: " + rafflesActivePost);
        if ("raffle:active".equals(rafflesActivePost.getEvent())) {
            logger.info("Raffle slug: {}", rafflesActivePost.getData().getRaffle().getSlug());
            alphaBotService.registerRaffle(rafflesActivePost.getData().getRaffle().getSlug(), rafflesActivePost.getData().getRaffle().getName());
        }
        return ResponseEntity.ok().build();
    }
}
