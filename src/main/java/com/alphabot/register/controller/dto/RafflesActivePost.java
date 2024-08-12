package com.alphabot.register.controller.dto;

public class RafflesActivePost {

    String event;
    Long timestamp;
    String hash;
    RequestRaffleData data;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public RequestRaffleData getData() {
        return data;
    }

    public void setData(RequestRaffleData data) {
        this.data = data;
    }
}
