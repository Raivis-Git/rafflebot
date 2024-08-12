package com.alphabot.register.integration.alphabot.dto;

import java.util.ArrayList;
import java.util.List;

public class Raffle {

    public class Data {
        List<RaffleData> raffles = new ArrayList<>();
        Boolean finalPage;

        public List<RaffleData> getRaffles() {
            return raffles;
        }

        public Boolean getFinalPage() {
            return finalPage;
        }
    }

    Boolean success;
    Data data;

    List<Error> errors;

    public Raffle() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public Data getData() {
        return data;
    }

    public Boolean getFinalPage() {
        return this.data.getFinalPage();
    }
}

