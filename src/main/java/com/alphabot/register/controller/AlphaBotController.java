package com.alphabot.register.controller;

import com.alphabot.register.discord.Listeners;
import com.alphabot.register.integration.alphabot.Alphabot;
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

    Logger LOGGER = LoggerFactory.getLogger(AlphaBotController.class);
    @Autowired
    AlphaBotService alphaBotService;

    @GetMapping
    public ResponseEntity<?> getRaffles() {
        Alphabot alphabot = new Alphabot();
        return ResponseEntity.ok(alphabot.getRaffles());
    }

    @PostMapping(consumes ="application/json")
    public ResponseEntity<?> postRaffles(@RequestBody RafflesActivePost rafflesActivePost) {

        LOGGER.info(rafflesActivePost.toString());

        if ("raffle:active".equals(rafflesActivePost.getEvent()))
            alphaBotService.registerRaffle(rafflesActivePost.getData().getRaffle().getSlug(), rafflesActivePost.getData().getRaffle().getName());

        return ResponseEntity.ok().build();
    }

}
