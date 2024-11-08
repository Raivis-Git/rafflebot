package com.alphabot.register.repository;

import com.alphabot.register.module.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByDiscordId(String discordId);

    Client findByRaffleKey(String raffleKey);

    Collection<Client> findBySubscriptionEndDateAfter(LocalDateTime date);

    Client findByTelegramId(String telegramId);

    Client existsByTelegramIdAndRaffleKey(String telegramId, String raffleKey);

}
