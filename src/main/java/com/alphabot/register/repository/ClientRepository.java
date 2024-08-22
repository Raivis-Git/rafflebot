package com.alphabot.register.repository;

import com.alphabot.register.module.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByDiscordId(String discordId);

    List<Client> findBySubscriptionEndDateAfter(LocalDateTime date);

}
