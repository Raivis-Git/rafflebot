package com.alphabot.register.controller.dto;

public class TelegramRegister {
    Long telegramId;
    String telegramUserName;
    String raffleKey;

    public TelegramRegister(Long telegramId, String raffleKey) {
        this.telegramId = telegramId;
        this.raffleKey = raffleKey;
    }

    public TelegramRegister() {
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getTelegramUserName() {
        return telegramUserName;
    }

    public void setTelegramUserName(String telegramUserName) {
        this.telegramUserName = telegramUserName;
    }

    public String getRaffleKey() {
        return raffleKey;
    }

    public void setRaffleKey(String raffleKey) {
        this.raffleKey = raffleKey;
    }
}
