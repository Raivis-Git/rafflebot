package com.alphabot.register.controller.dto;

public class RequestRaffleData {

    Raffle raffle;
    User user;

    public Raffle getRaffle() {
        return raffle;
    }

    public void setRaffle(Raffle raffle) {
        this.raffle = raffle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
