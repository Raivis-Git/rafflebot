package com.alphabot.register.discord;

import com.alphabot.register.module.Client;
import com.alphabot.register.repository.ClientRepository;
import jakarta.annotation.Nonnull;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Listeners extends ListenerAdapter {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("ready!!!!!!!!!!!!!");
        JDA jda = event.getJDA();
        Guild guild = jda.getGuildById(1267649628484927520L);
        Objects.requireNonNull(guild).updateCommands().addCommands(
                Commands.slash("register", "Shows a popup to register for automatic raffles")
        ).queue();

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
            EmbedBuilder eb = new EmbedBuilder()
                    .setTitle("Setup API and Webhook")
                    .setDescription("Select to participate in raffles or remove data from raffle.")
                    .setFooter("STONKS")
                    .setColor(0x0000FF);

            MessageEmbed messageEmbed = eb.build();
            MessageChannel messageChannel = event.getChannel();

            Button primary = Button.primary("register", "Register for raffles");
            Button secondary = Button.secondary("removeData", "Remove your data");

            messageChannel.sendMessageEmbeds(messageEmbed).setActionRow(primary, secondary)
                    .queue();
        }
    }

    @Override
    public void onModalInteraction(@Nonnull ModalInteractionEvent event) {
        if (event.getModalId().equals("raffleRegister")) {
            String webhook = Objects.requireNonNull(event.getValue("webhook")).getAsString();
            String apiKey = Objects.requireNonNull(event.getValue("apiKey")).getAsString();

            Member member = event.getMember();
            if (member == null) {
                event.reply("Registration failed!\n Couldn't retrieve discord user").setEphemeral(true).queue();
                return;
            }

            User user = member.getUser();

            Client clientByDiscordId = clientRepository.findByDiscordId(member.getId());
            if (clientByDiscordId == null)
                clientRepository.save(new Client(webhook, apiKey, member.getId(), user.getName()));
            else {
                clientByDiscordId.setDiscordWebhook(webhook);
                clientByDiscordId.setRaffleKey(apiKey);
                clientByDiscordId.setDiscordName(user.getName());
                clientRepository.save(clientByDiscordId);
            }

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

                event.reply("Removed from raffle").queue();
            }
        }
    }

}
