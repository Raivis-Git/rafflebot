package com.alphabot.register.service;

import com.alphabot.register.service.dto.*;
import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import reactor.core.*;
import reactor.core.publisher.*;

@Service
public class TelegramMessageService {

    Logger logger = LoggerFactory.getLogger(TelegramMessageService.class);

    private final WebClient webClient;

    public TelegramMessageService(WebClient webClient) {
        this.webClient = webClient;
    }

    // POST Request
    public Disposable sendMessage(TelegramMessage telegramMessage) {
        logger.info("Send data using Telegram:\n" + telegramMessage);
        return webClient.post()
                .uri("/sendTextMessage")
                .body(Mono.just(telegramMessage), TelegramMessage.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .doOnError(error -> logger.error("Error sending message: ", error))
                .doOnSuccess(result -> logger.debug("Successfully sent message: {}", result)).subscribe();
    }

}
