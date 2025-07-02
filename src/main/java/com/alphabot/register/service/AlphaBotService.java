package com.alphabot.register.service;

import com.alphabot.register.controller.dto.*;
import com.alphabot.register.discord.DiscordMain;
import com.alphabot.register.integration.alphabot.Alphabot;
import com.alphabot.register.integration.alphabot.dto.Error;
import com.alphabot.register.integration.alphabot.dto.Register;
import com.alphabot.register.module.Client;
import com.alphabot.register.repository.ClientRepository;
import com.alphabot.register.util.*;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AlphaBotService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private Alphabot alphabot;
    @Autowired
    private DiscordMain discordMain;
    @Autowired
    private TelegramService telegramService;

    Logger logger = LoggerFactory.getLogger(AlphaBotService.class);

    public AlphaBotService() {
    }

    public void registerRaffle(String slug, String raffleName) {
        try {
            List<Client> clientList = getClientsSubscriptionByEndDateAfter(LocalDateTime.now());

            clientList.parallelStream().forEach(client -> {
                try {
                    Register register = alphabot.registerRaffle(slug, client.getRaffleKey());
                    logger.info("Client id: {}, telegramId: {}, sendToTelegram: {}, telegramUserName: {}"
                            , client.getId(), client.getTelegramId(), client.getSendToTelegram(), client.getTelegramUserName());
                    if (register.getSuccess()) {
                        discordMain.sendEmbedWebhook(client.getDiscordWebhook(), "Raffle registration successful",
                                "Registered to: \n" + raffleName, client.getDiscordName(), true);
                    }
                    else {
//                      If failed send embed msg to webhook
                        String returnString = getErrorString(register);
                        if (returnString != null) {
                            returnString = returnString.toLowerCase().trim();
                            sendMessageIfSpecificErrorFound(raffleName, client, returnString);
                        }
                    }

                } catch (Exception ignore) {
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    @Transactional(readOnly = true)
    public List<Client> getClientsSubscriptionByEndDateAfter(LocalDateTime localDateTime) {
        return clientRepository.findBySubscriptionEndDateAfter(localDateTime);
    }


    private void sendMessageIfSpecificErrorFound(String raffleName, Client client, String returnString) {
        String messageToSend = "Raffle: \n{0}\n Reason: {1}\n{2}";
        String title = "Raffle registration failed";

        if (returnString.contains("invalid api")) {
            returnString = "Refresh API Key";
            messageToSend = MessageFormat.format(messageToSend, raffleName, returnString, client.getDiscordName());
            discordMain.sendEmbedWebhook(client.getDiscordWebhook(), title, messageToSend, client.getDiscordName(), false);
            telegramService.sendMessage(client, messageToSend);

        } else if (returnString.contains("connect twitter") || returnString.contains("re-connect your twitter")) {
            returnString = "Reconnect Twitter";
            messageToSend = MessageFormat.format(messageToSend, raffleName, returnString, client.getDiscordName());
            discordMain.sendEmbedWebhook(client.getDiscordWebhook(), title, messageToSend, client.getDiscordName(), false);
            telegramService.sendMessage(client, messageToSend);

        } else if (returnString.contains("connect discord") || returnString.contains("re-connect your discord")) {
            returnString = "Reconnect Discord";
            messageToSend = MessageFormat.format(messageToSend, raffleName, returnString, client.getDiscordName());
            discordMain.sendEmbedWebhook(client.getDiscordWebhook(), title, messageToSend, client.getDiscordName(), false);
            telegramService.sendMessage(client, messageToSend);
        }
    }

    @Nullable
    private String getErrorString(Register register) {
        String returnString;
        if (register.getRegisterData() == null)
            returnString = register.getErrors().stream().map(Error::getMessage).collect(Collectors.joining(", "));
        else
            returnString = register.getRegisterData().getResultMd();
        if (returnString == null)
            returnString = register.getRegisterData().getValidationReason();

        return returnString;
    }

    @Transactional(readOnly = true)
    public TelegramRegisterResponse registerTelegram(Long telegramId, String telegramUserName, String apiKey) {
        TelegramRegisterResponse registerResponse = new TelegramRegisterResponse();

        if (!ValidationUtils.isValidApiKey(apiKey))
            return registerResponse.setErrorWithMessage("Key is not valid");

        Client client = clientRepository.findByRaffleKey(apiKey);
        if (client == null)
            return registerResponse.setErrorWithMessage("Couldn't register current key");

        if (telegramId.equals(Long.getLong(client.getTelegramId())))
            return registerResponse.setSuccess(true);

        client.setTelegramId(telegramId.toString());
        client.setTelegramUserName(telegramUserName);
        clientRepository.save(client);


        return registerResponse.setSuccess(true);
    }

}
