package com.alphabot.register.service;

import com.alphabot.register.config.ConfigLoader;
import com.alphabot.register.discord.DiscordMain;
import com.alphabot.register.integration.alphabot.Alphabot;
import com.alphabot.register.integration.alphabot.dto.Error;
import com.alphabot.register.integration.alphabot.dto.Register;
import com.alphabot.register.module.Client;
import com.alphabot.register.repository.ClientRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public AlphaBotService() {
    }

    public void registerRaffle(String slug, String raffleName) {
        List<Client> clientList =  clientRepository.findAll();

        for (Client client : clientList) {
            System.out.println(client.getDiscordWebhook());
            try {
                Register register = alphabot.registerRaffle(slug, client.getRaffleKey());
                if (register.getSuccess())
                    discordMain.sendEmbedWebhook(client.getDiscordWebhook(), "Raffle registration successful", "Registered to: \n" + raffleName, true);

                else {
                    String returnString;
                    if (register.getRegisterData() == null)
                        returnString = register.getErrors().stream().map(Error::getMessage).collect(Collectors.joining(", "));
                    else
                        returnString = register.getRegisterData().getResultMd();

                    if (returnString == null)
                        returnString = register.getRegisterData().getValidationReason();

                    discordMain.sendEmbedWebhook(client.getDiscordWebhook(), "Raffle registration failed",
                            "Raffle: \n" + raffleName + "\n Reason: " + returnString + "\n", false);
                }
            } catch (Exception ignore){}
        }

    }

    public Boolean verifyHash(String hashToVerify) {
        if (hashToVerify == null)
            return false;

        String alphabotAuthentication = configLoader.getAlphabotAuthentication();
        if (alphabotAuthentication == null)
            return true;

        String authenticationHash = DigestUtils.sha256Hex(alphabotAuthentication);
        return authenticationHash.equals(hashToVerify);
    }

}
