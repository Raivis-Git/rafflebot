package com.alphabot.register.controller;

import com.alphabot.register.controller.dto.*;
import com.alphabot.register.service.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/raffles/telegram")
public class TelegramBotController {

    Logger logger = LoggerFactory.getLogger(TelegramBotController.class);

    @Autowired
    AlphaBotService alphaBotService;

    @GetMapping("/test")
    public ResponseEntity<?> testConnection() {
        return ResponseEntity.ok("Test successful");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerTelegram(@RequestBody TelegramRegister telegramRegister) {
        logger.info("Received register request for telegram");

        TelegramRegisterResponse registerResponse = alphaBotService.registerTelegram(
                telegramRegister.getTelegramId(),
                telegramRegister.getTelegramUserName(),
                telegramRegister.getRaffleKey());

        return ResponseEntity.ok(registerResponse);
    }
}
