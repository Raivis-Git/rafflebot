package com.alphabot.register.discord;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import org.springframework.stereotype.Component;

@Component
public class DiscordMain {

    public void sendEmbedWebhook(String url, String title, String description, boolean isSuccess) {
        if (title == null)
            title = "Raffle registration";

        if (description == null)
            description = "Raffle regsitered successfully";

        int color = 0x00ff00;
        if (!isSuccess)
            color = 0xff0000;

        WebhookClient client = WebhookClient.withUrl(url);
        WebhookEmbed embed = new WebhookEmbedBuilder()
                .setColor(color)
                .setDescription(description)
                .setTitle(new WebhookEmbed.EmbedTitle(title, "https://scryde.io/page/nostalgia/"))
                .setFooter(new WebhookEmbed.EmbedFooter("STONKS", "https://cooked-canvas-111.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F5ed15b5b-b44a-45b0-98c0-d143a08d3e1c%2F%25D0%25B8%25D0%25B7%25D0%25BE%25D0%25B1%25D1%2580%25D0%25B0%25D0%25B6%25D0%25B5%25D0%25BD%25D0%25B8%25D0%25B5_2023-06-26_231126170.png?table=block&id=8d712934-2e06-493b-ba92-06aa72aac1cb&spaceId=f50a6e2c-d29a-4a77-9b8a-df749d5ff59d&width=2000&userId=&cache=v2"))
                .build();
        client.send(embed)
                .thenAccept((message) -> System.out.printf("Message with embed has been sent [%s]%n", message.getId()));
    }

    public void sendEmbedWebhook2(String url, String title, String description) {
        if (title == null)
            title = "Raffle registration";

        if (description == null)
            description = "Raffle regsitered successfully";

        WebhookClient client = WebhookClient.withUrl(url);
        WebhookEmbed embed = new WebhookEmbedBuilder()
                .setColor(0x00ff00)
                .setThumbnailUrl("https://imagedelivery.net/tfbbUDELTH59IbQ61fGnYg/2dbc5b00-dfc1-4036-df8a-f03b33cf5700/small")
                .setImageUrl("https://imagedelivery.net/tfbbUDELTH59IbQ61fGnYg/2dbc5b00-dfc1-4036-df8a-f03b33cf5700/small")
                .setDescription(description)
//                .setAuthor(
//                        new WebhookEmbed.EmbedAuthor("Arturka",
//                                "https://cooked-canvas-111.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F5ed15b5b-b44a-45b0-98c0-d143a08d3e1c%2F%25D0%25B8%25D0%25B7%25D0%25BE%25D0%25B1%25D1%2580%25D0%25B0%25D0%25B6%25D0%25B5%25D0%25BD%25D0%25B8%25D0%25B5_2023-06-26_231126170.png?table=block&id=8d712934-2e06-493b-ba92-06aa72aac1cb&spaceId=f50a6e2c-d29a-4a77-9b8a-df749d5ff59d&width=1910&userId=&cache=v2",
//                                "https://scryde.io/page/nostalgia/"))
                .setTitle(new WebhookEmbed.EmbedTitle(title, "https://scryde.io/page/nostalgia/"))
                .setFooter(new WebhookEmbed.EmbedFooter("Alphabot", "https://pbs.twimg.com/profile_images/1818602280901390336/VJgtZ6_5_200x200.jpg"))
                .build();
        client.send(embed)
                .thenAccept((message) -> System.out.printf("Message with embed has been sent [%s]%n", message.getId()));
    }

    public static void main(String[] args) {
        DiscordMain discordMain = new DiscordMain();
        discordMain.sendEmbedWebhook2("https://discord.com/api/webhooks/1270376525665144905/IgI47wT43umGRmeBz1ihfj38upYAVk3YOvP_LVUnAiue4OYMY8V4d86TTkqMJQzSvHDs",
                "Raffle registration successful", "Registered to: \n" + "Bitcoin Chickens x THE N3TWORK (Public) (GTD)");
    }

}
