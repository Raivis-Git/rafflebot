package com.alphabot.register.module;

import jakarta.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "discordWebhook", unique = true, length = 2000)
    String discordWebhook;

    @Column(name = "raffleKey", nullable = false, unique = true)
    String raffleKey;

    @Column(name = "discordId", unique = true)
    String discordId;

    @Column(name = "discordName")
    String discordName;

    public Client() {
    }

    public Client(String discordWebhook, String raffleKey, String discordId, String discordName) {
        this.discordWebhook = discordWebhook;
        this.raffleKey = raffleKey;
        this.discordId = discordId;
        this.discordName = discordName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscordWebhook() {
        return discordWebhook;
    }

    public void setDiscordWebhook(String discordWebhook) {
        this.discordWebhook = discordWebhook;
    }

    public String getRaffleKey() {
        return raffleKey;
    }

    public void setRaffleKey(String raffleKey) {
        this.raffleKey = raffleKey;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(String discordName) {
        this.discordName = discordName;
    }
}
