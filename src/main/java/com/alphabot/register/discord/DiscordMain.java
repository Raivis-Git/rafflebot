package com.alphabot.register.discord;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DiscordMain {

    Logger LOGGER = LoggerFactory.getLogger(DiscordMain.class);

    public void sendEmbedWebhook(String url, String title, String description, String discordName, boolean isSuccess) {
        if (title == null)
            title = "Raffle registration";

        if (description == null)
            description = "Raffle registered successfully";

        int color = 0x00ff00;
        if (!isSuccess)
            color = 0xff0000;
        try (WebhookClient client = WebhookClient.withUrl(url)) {
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setColor(color)
                    .setDescription(description)
                    .setTitle(new WebhookEmbed.EmbedTitle(title, null))
                    .setFooter(new WebhookEmbed.EmbedFooter("Raffle Bot", "https://images.blur.io/_blur-prod/0x9f001721bb087fbbcd6fef2c140ed6892760e71b/724-69c69ff5da9d4454?w=1024"))
                    .build();
            client.send(embed)
                    .thenAccept((message) -> System.out.printf("Message with embed has been sent [%s]%n", message.getId()));


            description = description.replace("\n", " ");
            LOGGER.info("Sent " + (isSuccess ? "SUCCESS" : "FAILED") + " webhook embed to: " + discordName + ": With description: " + description);
        } catch (Exception ignore) {}
    }

    // For testing purposes
    public void sendEmbedWebhook2(String url, String title, String description) {
        if (title == null)
            title = "Raffle registration";

        if (description == null)
            description = "Raffle regsitered successfully";

        try (WebhookClient client = WebhookClient.withUrl(url)) {
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setColor(0x00ff00)
//                .setThumbnailUrl("https://imagedelivery.net/tfbbUDELTH59IbQ61fGnYg/2dbc5b00-dfc1-4036-df8a-f03b33cf5700/small")
//                .setImageUrl("https://imagedelivery.net/tfbbUDELTH59IbQ61fGnYg/2dbc5b00-dfc1-4036-df8a-f03b33cf5700/small")
                    .setDescription(description)
//                .setAuthor(
//                        new WebhookEmbed.EmbedAuthor("Arturka",
//                                "https://cooked-canvas-111.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F5ed15b5b-b44a-45b0-98c0-d143a08d3e1c%2F%25D0%25B8%25D0%25B7%25D0%25BE%25D0%25B1%25D1%2580%25D0%25B0%25D0%25B6%25D0%25B5%25D0%25BD%25D0%25B8%25D0%25B5_2023-06-26_231126170.png?table=block&id=8d712934-2e06-493b-ba92-06aa72aac1cb&spaceId=f50a6e2c-d29a-4a77-9b8a-df749d5ff59d&width=1910&userId=&cache=v2",
//                                "https://scryde.io/page/nostalgia/"))
                    .setTitle(new WebhookEmbed.EmbedTitle(title, "https://scryde.io/page/nostalgia/"))
                    .setFooter(new WebhookEmbed.EmbedFooter("Raffle Bot", "https://images.blur.io/_blur-prod/0x9f001721bb087fbbcd6fef2c140ed6892760e71b/724-69c69ff5da9d4454?w=1024"))
                    .build();
            client.send(embed)
                    .thenAccept((message) -> System.out.printf("Message with embed has been sent [%s]%n", message.getId()));
        }
    }

    public static void main(String[] args) {
        DiscordMain discordMain = new DiscordMain();
        discordMain.sendEmbedWebhook2("https://discord.com/api/webhooks/1272966992093118578/srvtZU0Jdmvltcqr3bEiGOpKGhEyMF3hFRvc1jAcZ9lr-WsSGSOEqOhsh8EGG7DxDiAR",
                "Raffle registration successful", "Registered to: \n" + "Bitcoin Chickens x THE N3TWORK (Public) (GTD)");
    }

}
