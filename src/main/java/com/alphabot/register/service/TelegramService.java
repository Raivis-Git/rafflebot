package com.alphabot.register.service;

import com.alphabot.register.module.*;
import com.alphabot.register.service.dto.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

@Service
public class TelegramService {

    Logger logger = LoggerFactory.getLogger(TelegramService.class);

    @Autowired
    private TelegramMessageService telegramMessageService;

    public void sendMessage(Client client, String message) {
        if (!client.getSendToTelegram())
            return;

        String telegramIdString = client.getTelegramId();
        if (!StringUtils.hasText(telegramIdString))
            return;

        logger.info("""
                Sending message to telegram
                telegram Id: {}\s
                message: {}""", telegramIdString, message);

        TelegramMessage telegramMessage = new TelegramMessage();
        telegramMessage.setTelegramId(Long.getLong(telegramIdString));
        telegramMessage.setMessage(message);
        telegramMessageService.sendMessage(telegramMessage);
    }
}
