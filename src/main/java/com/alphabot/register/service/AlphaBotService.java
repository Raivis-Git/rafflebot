package com.alphabot.register.service;

import com.alphabot.register.config.ConfigLoader;
import com.alphabot.register.discord.DiscordMain;
import com.alphabot.register.integration.alphabot.Alphabot;
import com.alphabot.register.integration.alphabot.dto.Error;
import com.alphabot.register.integration.alphabot.dto.Register;
import com.alphabot.register.module.Client;
import com.alphabot.register.repository.ClientRepository;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class AlphaBotService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private Alphabot alphabot;
    @Autowired
    private ConfigLoader configLoader;
    @Autowired
    private DiscordMain discordMain;

    Logger LOGGER = LoggerFactory.getLogger(AlphaBotService.class);

    public AlphaBotService() {
    }

    public void registerRaffle(String slug, String raffleName) {
        try {
            Collection<Client> clientList = clientRepository.findBySubscriptionEndDateAfter(LocalDateTime.now());

            for (Client client : clientList) {
                try {
                    Register register = alphabot.registerRaffle(slug, client.getRaffleKey());
                    if (register.getSuccess()) {
                        discordMain.sendEmbedWebhook(client.getDiscordWebhook(), "Raffle registration successful",
                                "Registered to: \n" + raffleName, client.getDiscordName(), true);
                        continue;
                    }

//              If failed send embed msg to webhook
                    String returnString = getErrorString(register);
                    if (returnString == null)
                        continue;

                    returnString = returnString.toLowerCase().trim();

                    sendMessageIfSpecificErrorFound(raffleName, client, returnString);

                } catch (Exception ignore) {
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    private void sendMessageIfSpecificErrorFound(String raffleName, Client client, String returnString) {
        if (returnString.contains("invalid api")) {
            returnString = "Refresh API Key";
            discordMain.sendEmbedWebhook(client.getDiscordWebhook(), "Raffle registration failed",
                    "Raffle: \n" + raffleName + "\n Reason: " + returnString + "\n", client.getDiscordName(), false);
        } else if (returnString.contains("connect twitter") || returnString.contains("re-connect your twitter")) {
            returnString = "Reconnect Twitter";
            discordMain.sendEmbedWebhook(client.getDiscordWebhook(), "Raffle registration failed",
                    "Raffle: \n" + raffleName + "\n Reason: " + returnString + "\n", client.getDiscordName(), false);
        } else if (returnString.contains("connect discord") || returnString.contains("re-connect your discord")) {
            returnString = "Reconnect Discord";
            discordMain.sendEmbedWebhook(client.getDiscordWebhook(), "Raffle registration failed",
                    "Raffle: \n" + raffleName + "\n Reason: " + returnString + "\n", client.getDiscordName(), false);
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
}
