package com.alphabot.register.controller;

import com.alphabot.register.controller.dto.*;
import com.alphabot.register.service.*;
import com.alphabot.register.service.dto.*;
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

//    @Autowired
//    TelegramMessageService telegramMessageService;

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

//    @GetMapping("/test1")
//    public ResponseEntity<?> test() {
//        telegramMessageService.sendMessage(new TelegramMessage(6829974665L, "Test Message")).subscribe();
//        return ResponseEntity.ok("Test");
//    }

}
