package com.alphabot.register.module;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Guild {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "guildName")
    private String guildName;

    @Column(name = "guildId", unique = true)
    private String guildId;

    @Column(name = "subscriptionEndDate")
    private LocalDateTime subscriptionEndDate;

    public Guild() {
    }

    public Guild(String guildName, String guildId) {
        this.guildName = guildName;
        this.guildId = guildId;
        this.subscriptionEndDate = LocalDateTime.now().plusHours(12);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public LocalDateTime getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(LocalDateTime subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }
}
