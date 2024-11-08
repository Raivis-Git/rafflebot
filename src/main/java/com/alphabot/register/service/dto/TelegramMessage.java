package com.alphabot.register.service.dto;

public class TelegramMessage {

    Long telegramId;
    String message;

    public TelegramMessage(Long telegramId, String message) {
        this.telegramId = telegramId;
        this.message = message;
    }

    public TelegramMessage() {
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TelegramMessage{" +
                "telegramId=" + telegramId +
                ", message='" + message + '\'' +
                '}';
    }
}
