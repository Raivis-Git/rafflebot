package com.alphabot.register.util;

public class ValidationUtils {
    public static boolean isValidWebhookUrl(String webhook) {
        return webhook.matches("https://discord(?:app)?\\.com/api/webhooks/\\d+/[\\w-]+$");
    }

    public static boolean isValidApiKey(String apiKey) {
        return apiKey.matches("^(?=.{30,64}$)u[a-zA-Z\\d]{8}-[a-zA-Z\\d]+$");
    }
}
