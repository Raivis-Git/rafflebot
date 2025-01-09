package com.alphabot.register.module;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "discordWebhook", unique = true, length = 2000)
    private String discordWebhook;

    @Column(name = "raffleKey", nullable = false, unique = true)
    private String raffleKey;

    @Column(name = "discordId", unique = true)
    private String discordId;

    @Column(name = "telegramId", unique = true)
    private String telegramId;

    @Column(name = "telegramUserName")
    private String telegramUserName;

    @Column(name = "discordName")
    private String discordName;

    @Column(name = "groupId")
    private String groupId;

    @Column(name = "sendToTelegram", columnDefinition = "boolean default false", nullable = false)
    private Boolean sendToTelegram;

    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "subscriptionEndDate")
    private LocalDateTime subscriptionEndDate;

    public Client() {
    }

    public Client(String discordWebhook, String raffleKey, String discordId, String discordName, String groupId, LocalDateTime subscriptionEndDate) {
        this.discordWebhook = discordWebhook;
        this.raffleKey = raffleKey;
        this.discordId = discordId;
        this.discordName = discordName;
        this.groupId = groupId;
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public Client(String discordWebhook, String raffleKey) {
        this.discordWebhook = discordWebhook;
        this.raffleKey = raffleKey;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", discordWebhook='" + discordWebhook + '\'' +
                ", raffleKey='" + raffleKey + '\'' +
                ", discordId='" + discordId + '\'' +
                ", discordName='" + discordName + '\'' +
                ", groupId='" + groupId + '\'' +
                ", created=" + created +
                ", subscriptionTill=" + subscriptionEndDate +
                '}';
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(LocalDateTime subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public Boolean getSendToTelegram() {
        return sendToTelegram;
    }

    public void setSendToTelegram(Boolean sendToTelegram) {
        this.sendToTelegram = sendToTelegram;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getTelegramUserName() {
        return telegramUserName;
    }

    public void setTelegramUserName(String telegramUserName) {
        this.telegramUserName = telegramUserName;
    }
}
