package com.alphabot.register.discord;

import com.alphabot.register.module.Client;
import com.alphabot.register.repository.ClientRepository;
import jakarta.annotation.Nonnull;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Listeners extends ListenerAdapter {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DiscordMain discordMain;
    Logger LOGGER = LoggerFactory.getLogger(Listeners.class);

    @Override
    public void onReady(ReadyEvent event) {
        LOGGER.info("Discord JDA started successfully");
//        Create a slash command for a certain guild(group)
//        JDA jda = event.getJDA();
//        Guild guild = jda.getGuildById(1267649628484927520L);
//        Objects.requireNonNull(guild).updateCommands().addCommands(
//                Commands.slash("register", "Shows a popup to register for automatic raffles")
//        ).queue();

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("register")) {
            TextInput subject = TextInput.create("webhook", "Discord webhook", TextInputStyle.SHORT)
                    .setPlaceholder("Your discord webhook to get messaged to")
                    .setMinLength(10)
                    .setMaxLength(2000)
                    .setRequired(false)
                    .build();

            TextInput body = TextInput.create("apiKey", "Alphabot API key", TextInputStyle.SHORT)
                    .setPlaceholder("Your alphabot api key")
                    .setMinLength(10)
                    .setMaxLength(255)
                    .setRequired(true)
                    .build();

            Modal modal = Modal.create("raffleRegister", "Raffles registration")
                    .addComponents(ActionRow.of(subject), ActionRow.of(body))
                    .build();

            event.replyModal(modal).queue();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.startsWith("!acceptRaffles")) {
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Setup API and Webhook")
                    .setDescription("Select to participate in raffles or remove data from raffle.")
                    .setFooter("STONKS","https://cooked-canvas-111.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F5ed15b5b-b44a-45b0-98c0-d143a08d3e1c%2F%25D0%25B8%25D0%25B7%25D0%25BE%25D0%25B1%25D1%2580%25D0%25B0%25D0%25B6%25D0%25B5%25D0%25BD%25D0%25B8%25D0%25B5_2023-06-26_231126170.png?table=block&id=8d712934-2e06-493b-ba92-06aa72aac1cb&spaceId=f50a6e2c-d29a-4a77-9b8a-df749d5ff59d&width=2000&userId=&cache=v2")
                    .setColor(0x0000FF);

            MessageEmbed messageEmbedSetup = embedBuilder.build();
            MessageChannel messageChannelSetup = event.getChannel();

            Button primary = Button.primary("register", "Register for raffles");
            Button secondary = Button.danger("removeData", "Remove your data");
            LOGGER.info("send message embeds for setup API and Webhook");
            messageChannelSetup.sendMessageEmbeds(messageEmbedSetup).setActionRow(primary, secondary)
                    .queue();

            EmbedBuilder eb = new EmbedBuilder()
                    .setTitle("STONKS RAFFLE")
                    .setDescription("Note: Alphabot Premium is REQUIRED for this to work\n" +
                            "\n" +
                            "Setup Guide\n" +
                            "\n" +
                            "Create your own Discord server and generate a webhook. Save this webhook\n" +
                            "\n" +
                            "Head over to AlphaBot and generate a API Key. Make sure you save this API key.\n" +
                            "\n" +
                            "Interact with the bot above filling out the correct information, and you should get a \"Webhook Validation\" message.\n" +
                            "\n" +
                            "Once you have completed the steps, you now have an auto giveaway joiner for all the AlphaBots raffles.")
                    .setFooter("STONKS","https://cooked-canvas-111.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F5ed15b5b-b44a-45b0-98c0-d143a08d3e1c%2F%25D0%25B8%25D0%25B7%25D0%25BE%25D0%25B1%25D1%2580%25D0%25B0%25D0%25B6%25D0%25B5%25D0%25BD%25D0%25B8%25D0%25B5_2023-06-26_231126170.png?table=block&id=8d712934-2e06-493b-ba92-06aa72aac1cb&spaceId=f50a6e2c-d29a-4a77-9b8a-df749d5ff59d&width=2000&userId=&cache=v2")
                    .setColor(0xF3E5AB);

            MessageEmbed messageEmbed = eb.build();
            LOGGER.info("send message embeds for description");
            messageChannelSetup.sendMessageEmbeds(messageEmbed).queue();

        }
    }

    @Override
    public void onModalInteraction(@Nonnull ModalInteractionEvent event) {
        if (event.getModalId().equals("raffleRegister")) {
            String webhook = Objects.requireNonNull(event.getValue("webhook")).getAsString();
            String apiKey = Objects.requireNonNull(event.getValue("apiKey")).getAsString();

            Member member = event.getMember();
            if (member == null) {
                LOGGER.info("Registration failed!\n Couldn't retrieve discord user\n" + webhook + "\n" + apiKey);
                event.reply("Registration failed!\n Couldn't retrieve discord user").setEphemeral(true).queue();
                return;
            }

            User user = member.getUser();

            Client clientByDiscordId = clientRepository.findByDiscordId(member.getId());
            if (clientByDiscordId == null) {
                Client client = new Client(webhook, apiKey, member.getId(), user.getName());
                LOGGER.info("Creating new client: " + client);
                clientRepository.save(client);
            } else {
                LOGGER.info("Updating existing client: " + clientByDiscordId + "\n" +
                        webhook + "\n" +
                        apiKey + "\n" +
                        user.getName());
                clientByDiscordId.setDiscordWebhook(webhook);
                clientByDiscordId.setRaffleKey(apiKey);
                clientByDiscordId.setDiscordName(user.getName());
                clientRepository.save(clientByDiscordId);
            }

            discordMain.sendEmbedWebhook(webhook, "Webhook valid", "Your webhook registered successfully", true);
            event.reply("Registration successful!").setEphemeral(true).queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        String buttonId = event.getComponentId(); // Button ID

        // Respond to button clicks based on ID
        switch (buttonId) {
            case "register" -> {
                TextInput subject = TextInput.create("webhook", "Discord webhook", TextInputStyle.SHORT)
                        .setPlaceholder("Your discord webhook to get messaged to")
                        .setMinLength(10)
                        .setMaxLength(2000)
                        .setRequired(false)
                        .build();
                TextInput body = TextInput.create("apiKey", "Alphabot API key", TextInputStyle.SHORT)
                        .setPlaceholder("Your alphabot api key")
                        .setMinLength(10)
                        .setMaxLength(255)
                        .setRequired(true)
                        .build();
                Modal modal = Modal.create("raffleRegister", "Raffles registration")
                        .addComponents(ActionRow.of(subject), ActionRow.of(body))
                        .build();
                event.replyModal(modal).queue();
            }
            case "removeData" -> {
                Client client = clientRepository.findByDiscordId(event.getMember().getId());
                if (client != null)
                    clientRepository.delete(client);

                event.reply("Removed from raffle").setEphemeral(true).queue();
            }
        }
    }

}
