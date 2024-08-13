package com.alphabot.register.integration.alphabot;

import com.alphabot.register.config.ConfigLoader;
import com.alphabot.register.integration.alphabot.dto.Raffle;
import com.alphabot.register.integration.alphabot.dto.RaffleData;
import com.alphabot.register.integration.alphabot.dto.Register;
import com.alphabot.register.integration.alphabot.dto.RegisterRequest;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

@Component
public class Alphabot {

    Logger LOGGER = LoggerFactory.getLogger(Alphabot.class);
    Gson gson = new Gson();
    ConfigLoader configLoader = new ConfigLoader();
    private final String authenticationKey = configLoader.getProperty("alphabot.authentication");

    public Raffle getLatestRaffles(String pageSize) {
        // Create an instance of HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create the HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.alphabot.app/v1/raffles?filter=unregistered&status=active&pageNum=0&pageSize=" + pageSize))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + authenticationKey)
                .GET().build();

        // Send the request and get the response
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            LOGGER.info("raffle retrieval successful");
            return gson.fromJson(response.body(), Raffle.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    // Get all Raffles
    public Set<RaffleData> getAllRaffles() {

        // Create an instance of HttpClient
        HttpClient client = HttpClient.newHttpClient();

        Set<RaffleData> raffleData = new HashSet<>();
        boolean lastPage = false;
        int pageNum = 0;
        while(!lastPage) {

            // Create the HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.alphabot.app/v1/raffles?filter=unregistered&status=active&pageNum=" + pageNum + "&pageSize=50"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + authenticationKey)
                    .GET().build();

            // Send the request and get the response
                Raffle raffle = null;
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                raffle = gson.fromJson(response.body(), Raffle.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LOGGER.info("raffle retrieval successful " + pageNum);

            if (raffle == null) {
                pageNum++;
                continue;
            }

            if (raffle.getData() != null)
                raffleData.addAll(raffle.getData().getRaffles());

            if (raffle.getFinalPage() == null || raffle.getFinalPage())
                lastPage = true;

            pageNum++;

        }

        LOGGER.info("Retrieved " + raffleData.size() + " raffles");

        return raffleData;

    }

    // Register a Raffle
    public Register registerRaffle(String slug) {
        return registerRaffle(slug, authenticationKey);
    }

    public Register registerRaffle(String slug, String clientKey) {
        // Create an instance of HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Create an instance of the request data
        RegisterRequest requestData = new RegisterRequest(slug);

        // Convert the request data to JSON using Gson
        Gson gson = new Gson();
        String jsonInputString = gson.toJson(requestData);

        // Create the HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.alphabot.app/v1/register"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + clientKey.trim())
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                .build();

        LOGGER.info("POST register raffle");
        // Send the request and get the response
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), Register.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Test for Registers all raffles
    public void run() {
        int registered = 0;
        int failedRegisters = 0;
        Set<RaffleData> raffleDataSet = getAllRaffles();
        for (RaffleData raffleData : raffleDataSet) {
            Register register = registerRaffle(raffleData.getSlug());
            if (register.getSuccess())
                registered++;
            failedRegisters++;
        }

        LOGGER.info("Registered raffles: " + registered + "/n" + "Failed registers: " + failedRegisters);
    }

    public static void main(String[] args) {
//        Alphabot alphabot = new Alphabot();
//        Set<RaffleData> raffle = alphabot.getRaffles();
//        System.out.println(raffle.toString());
//
//        Register register = alphabot.registerRaffle("producers-x-elixir-games-bvuain");
//        System.out.println(register);

//        alphabot.run();
    }
}
