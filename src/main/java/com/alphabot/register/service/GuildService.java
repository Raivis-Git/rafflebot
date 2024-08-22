package com.alphabot.register.service;

import com.alphabot.register.module.Guild;
import com.alphabot.register.repository.GuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuildService {

    @Autowired
    GuildRepository guildRepository;

    public Boolean checkIfGuildExists(String guildId) {
        return guildRepository.existsByGuildId(guildId);
    }

    public Guild create(String guildName, String guildId) {
        Guild guild = new Guild(guildName, guildId);
        return guildRepository.save(guild);
    }

    public Guild updateIfChanged(String guildName, String guildId) {
        Guild guild = guildRepository.findFirstByGuildId(guildId);
        if (guild == null || guild.getGuildName().equals(guildName))
            return guild;

        guild.setGuildName(guildName);
        return guildRepository.save(guild);
    }

    public Guild updateIfNewOrChanged(String guildName, String guildId) {
        Guild guild = guildRepository.findFirstByGuildId(guildId);
        if (guild == null)
            return create(guildName, guildId);

        guild.setGuildName(guildName);
        return guildRepository.save(guild);
    }

}
