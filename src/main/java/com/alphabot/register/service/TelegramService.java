package com.alphabot.register.service;

import com.alphabot.register.module.*;
import com.alphabot.register.service.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

@Service
public class TelegramService {

    @Autowired
    private TelegramMessageService telegramMessageService;

    public void sendMessage(Client client, String message) {
        if (!client.getSendToTelegram())
            return;

        String telegramIdString = client.getTelegramId();
        if (!StringUtils.hasText(telegramIdString))
            return;

        TelegramMessage telegramMessage = new TelegramMessage();
        telegramMessage.setTelegramId(Long.getLong(telegramIdString));
        telegramMessage.setMessage(message);
        telegramMessageService.sendMessage(telegramMessage);
    }
}
