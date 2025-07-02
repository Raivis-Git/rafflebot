package com.alphabot.register.discord;

import com.alphabot.register.module.Client;
import com.alphabot.register.module.Guild;
import com.alphabot.register.repository.ClientRepository;
import com.alphabot.register.service.GuildService;
import com.alphabot.register.util.*;
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
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.*;

import java.util.Objects;

@Component
public class Listeners extends ListenerAdapter {

  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private GuildService guildService;
  @Autowired
  private DiscordMain discordMain;
  Logger LOGGER = LoggerFactory.getLogger(Listeners.class);

  @Override
  public void onReady(@NotNull ReadyEvent event) {
    LOGGER.info("Discord JDA started successfully");
  }

  @Override
  public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
    if (event.getName().equals("register")) {
      TextInput discordWebhookInput = TextInput.create("webhook", "Discord webhook", TextInputStyle.SHORT)
          .setPlaceholder("Your discord webhook to get messaged to")
          .setMinLength(10)
          .setMaxLength(2000)
          .setRequired(false)
          .build();

      TextInput alphaBotApiKeyInput = TextInput.create("apiKey", "Alphabot API key", TextInputStyle.SHORT)
          .setPlaceholder("Your alphabot api key")
          .setMinLength(10)
          .setMaxLength(255)
          .setRequired(true)
          .build();

      Modal modal = Modal.create("raffleRegister", "Raffles registration")
          .addComponents(ActionRow.of(alphaBotApiKeyInput), ActionRow.of(discordWebhookInput))
          .build();

      event.replyModal(modal).queue();
    }
  }

  @Override
  public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    String message = event.getMessage().getContentRaw();
    if (message.startsWith("!acceptRafflesENRULV")) {
      MessageChannel messageChannelSetup = event.getChannel();
      sendFunctionalMessage(messageChannelSetup);
      sendDescriptionMessage(messageChannelSetup, "Raffle Bot",
          """
              Note: Alphabot Premium is required for this setup.
                                          
              Setup Instructions:
                                          
              Create a Server and Webhook:
               1.Create your own Discord server.
              Go to the server settings and generate a webhook.
              Save the webhook URL, as you'll need it in the next step.
                                          
              Obtain an API Key from AlphaBot:
               2.Visit the AlphaBot website and generate your API key.
              Make sure to save this API key for the bot setup.
                                          
              Configure the Bot:
               3.Interact with the bot, providing the webhook URL and API key.
              After completing these steps, the bot will automatically participate in all raffles hosted by AlphaBot that match your server and Discord role requirements.
                                          
                                          
              Note: For detailed information and a visual guide, refer to the video tutorial below.
                            
              ----------------------------------------------
                            
              Iestatīšanas instrukcija:
                1. Izveido serveri un webhook:
              Izveido savu Discord serveri.
                            
              Atver servera iestatījumus un izveido webhook.
                            
              Saglabā webhook saiti — tā būs nepieciešama nākamajā solī.
                            
                2. Iegūsti API atslēgu no AlphaBot:
              Apmeklē AlphaBot mājaslapu un izveido savu API atslēgu.
                            
              Saglabā to — tā būs vajadzīga bota konfigurēšanai.
                            
                3. Konfigurē botu:
              Sazinies ar botu un norādi savu webhook saiti un API atslēgu.
                            
              Pēc šo soļu izpildes bots automātiski piedalīsies visos AlphaBot izlozēs, kas atbilst tava servera un Discord lomu prasībām.
                            
              Piezīme: Lai iegūtu sīkāku informāciju un vizuālu pamācību, skaties video zemāk.
                            
              ----------------------------------------------
                                    
              Настройка бота для розыгрышей
                                          
              Требуется: Alphabot Premium
                                          
              Инструкции по настройке:
                                          
              Создание сервера и вебхука:
                1.Создайте свой собственный сервер в Discord.
              Перейдите в настройки сервера и создайте вебхук.
              Сохраните URL вебхука, так как он понадобится на следующем шаге.
                                          
              Получение API-ключа от AlphaBot:
                2.Перейдите на сайт AlphaBot и сгенерируйте свой API-ключ.
              Обязательно сохраните этот ключ для настройки бота.
                                          
              Настройка бота:
                3.Взаимодействуйте с ботом, предоставив ему URL вебхука и API-ключ.
              После завершения этих шагов бот будет автоматически участвовать во всех розыгрышах, проводимых AlphaBot, в которых вы соответствуете требованиям сервера и роли в Discord.
                                          
              Подробную информацию и наглядное руководство можно найти в видео ниже.""");

    } else if (message.startsWith("!acceptRafflesEN")) {
      MessageChannel messageChannelSetup = event.getChannel();
      sendFunctionalMessage(messageChannelSetup);
      sendDescriptionMessage(messageChannelSetup, "Raffle Bot",
          """
              Note: Alphabot Premium is required for this setup.
                                          
              Setup Instructions:
                                          
              Create a Server and Webhook:
               1.Create your own Discord server.
              Go to the server settings and generate a webhook.
              Save the webhook URL, as you'll need it in the next step.
                                          
              Obtain an API Key from AlphaBot:
               2.Visit the AlphaBot website and generate your API key.
              Make sure to save this API key for the bot setup.
                                          
              Configure the Bot:
               3.Interact with the bot, providing the webhook URL and API key.
              After completing these steps, the bot will automatically participate in all raffles hosted by AlphaBot that match your server and Discord role requirements.
                                          
                                          
              Note: For detailed information and a visual guide, refer to the video tutorial below.
              """);

    } else if (message.startsWith("!acceptRafflesRU")) {
      MessageChannel messageChannelSetup = event.getChannel();
      sendFunctionalMessage(messageChannelSetup);
      sendDescriptionMessage(messageChannelSetup, "Raffle Bot",
          """
              Настройка бота для розыгрышей
                                          
              Требуется: Alphabot Premium
                                          
              Инструкции по настройке:
                                          
              Создание сервера и вебхука:
                1.Создайте свой собственный сервер в Discord.
              Перейдите в настройки сервера и создайте вебхук.
              Сохраните URL вебхука, так как он понадобится на следующем шаге.
                                          
              Получение API-ключа от AlphaBot:
                2.Перейдите на сайт AlphaBot и сгенерируйте свой API-ключ.
              Обязательно сохраните этот ключ для настройки бота.
                                          
              Настройка бота:
                3.Взаимодействуйте с ботом, предоставив ему URL вебхука и API-ключ.
              После завершения этих шагов бот будет автоматически участвовать во всех розыгрышах, проводимых AlphaBot, в которых вы соответствуете требованиям сервера и роли в Discord.
                                          
              Подробную информацию и наглядное руководство можно найти в видео ниже.""");

    } else if (message.startsWith("!acceptRafflesLV")) {
      MessageChannel messageChannelSetup = event.getChannel();
      sendFunctionalMessage(messageChannelSetup);
      sendDescriptionMessage(messageChannelSetup, "Raffle Bot",
          """
              Iestatīšanas instrukcija:
                1. Izveido serveri un webhook:
              Izveido savu Discord serveri.
                            
              Atver servera iestatījumus un izveido webhook.
                            
              Saglabā webhook saiti — tā būs nepieciešama nākamajā solī.
                            
                2. Iegūsti API atslēgu no AlphaBot:
              Apmeklē AlphaBot mājaslapu un izveido savu API atslēgu.
                            
              Saglabā to — tā būs vajadzīga bota konfigurēšanai.
                            
                3. Konfigurē botu:
              Sazinies ar botu un norādi savu webhook saiti un API atslēgu.
                            
              Pēc šo soļu izpildes bots automātiski piedalīsies visos AlphaBot izlozēs, kas atbilst tava servera un Discord lomu prasībām.
                            
              Piezīme: Lai iegūtu sīkāku informāciju un vizuālu pamācību, skaties video zemāk.""");

    } else if (message.startsWith("!testtesttest")) {
      MessageChannel messageChannelSetup = event.getChannel();
      sendFunctionalMessage(messageChannelSetup);
      sendDescriptionMessage(messageChannelSetup, "Raffle Bot",
          """
              Note: Alphabot Premium is REQUIRED for this to work
                                          
              Setup Guide:

              Create your own Discord server and generate a webhook. Save this webhook

              Head over to AlphaBot and generate an API Key. Make sure you save this API key.

              Interact with the bot above filling out the correct information, and you should get a "Webhook Valid" message.

              Once you have completed the steps, you now have an auto giveaway joiner for all the AlphaBots raffles.
              """);
    }
  }

  @Override
  public void onModalInteraction(@Nonnull ModalInteractionEvent event) {
    if (event.getModalId().equals("raffleRegister")) {
      String webhook = Objects.requireNonNull(event.getValue("webhook")).getAsString();
      String apiKey = Objects.requireNonNull(event.getValue("apiKey")).getAsString();

      if (!ValidationUtils.isValidWebhookUrl(webhook)) {
        LOGGER.info("Registration failed! Webhook is not valid! " + webhook);
        event.reply("Registration failed! Webhook is not valid!").setEphemeral(true).queue();
        return;
      }

      if (!ValidationUtils.isValidApiKey(apiKey)) {
        LOGGER.info("Registration failed! Api key is not valid! " + apiKey);
        event.reply("Registration failed! Api key is not valid!").setEphemeral(true).queue();
        return;
      }

      Member member = event.getMember();
      if (member == null) {
        LOGGER.info("Registration failed! Couldn't retrieve discord user" + webhook + "\n" + apiKey);
        event.reply("Registration failed! Couldn't retrieve discord user").setEphemeral(true).queue();
        return;
      }

      User user = member.getUser();
      String guildName = member.getGuild().getName();
      String guildId = member.getGuild().getId();
      String discordName;

      Guild guild = guildService.updateIfNewOrChanged(guildName, guildId);

      Client clientByDiscordId = clientRepository.findByDiscordId(member.getId());
      if (clientByDiscordId == null) {
        Client client = new Client(webhook, apiKey, member.getId(), user.getName(), guildId, guild.getSubscriptionEndDate());
        LOGGER.info("Creating new client: " + client);
        clientRepository.save(client);
        discordName = client.getDiscordName();
      } else {
        LOGGER.info("Updating existing client: " + clientByDiscordId + "\n" +
            "webhook: " + webhook + "\n" +
            "apikey: " + apiKey + "\n" +
            "name: " + user.getName() + "\n" +
            "guild: " + guildName);

        if (!webhook.isEmpty())
          clientByDiscordId.setDiscordWebhook(webhook);

        clientByDiscordId.setRaffleKey(apiKey);
        clientByDiscordId.setDiscordName(user.getName());
//                clientByDiscordId.setSubscriptionEndDate(guild.getSubscriptionEndDate());
        clientRepository.save(clientByDiscordId);

        LOGGER.info("webhook: " + webhook + "\n" +
            "apikey: " + apiKey + "\n" +
            "name: " + user.getName() + "\n" +
            "guild: " + guildName);
        discordName = clientByDiscordId.getDiscordName();
      }
      try {
        discordMain.sendEmbedWebhook(webhook, "Webhook valid", "Your webhook registered successfully",
            discordName, true);
        event.reply("Registration successful!").setEphemeral(true).queue();
      } catch (Exception e) {
        event.reply("Webhook URL is invalid").setEphemeral(true).queue();
      }

    } else if (event.getModalId().equals("testtesttest")) {

      String webhook = Objects.requireNonNull(event.getValue("webhook")).getAsString();
      String apiKey = Objects.requireNonNull(event.getValue("apiKey")).getAsString();

      Member member = event.getMember();
      if (member == null) {
        LOGGER.info("Registration failed!\n Couldn't retrieve discord user\n" + webhook + "\n" + apiKey);
        event.reply("Registration failed!\n Couldn't retrieve discord user").setEphemeral(true).queue();
        return;
      }

      User user = member.getUser();
      String guildName = member.getGuild().getName();
      String guildId = member.getGuild().getId();

      Guild guild = guildService.updateIfNewOrChanged(guildName, guildId);

      Client clientByDiscordId = clientRepository.findByDiscordId(member.getId());
      if (clientByDiscordId == null) {
        Client client = new Client(webhook, apiKey, member.getId(), user.getName(), guildId, guild.getSubscriptionEndDate());
        LOGGER.info("Creating new client: " + client);
        clientRepository.save(client);
      } else {
        LOGGER.info("Updating existing client: " + clientByDiscordId + "\n" +
            webhook + "\n" +
            apiKey + "\n" +
            user.getName() + "\n" +
            guildName);
        clientByDiscordId.setDiscordWebhook(webhook);
        clientByDiscordId.setRaffleKey(apiKey);
        clientByDiscordId.setDiscordName(user.getName());
//                clientByDiscordId.setSubscriptionEndDate(guild.getSubscriptionEndDate());
        clientRepository.save(clientByDiscordId);
      }

    }
  }

  @Override
  public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
    String buttonId = event.getComponentId(); // Button ID

    // Respond to button clicks based on ID
    switch (buttonId) {
      case "register" -> {
        Member member = event.getMember();
        if (member == null) {
          event.reply("Couldn't retrieve member data").setEphemeral(true).queue();
          return;
        }
        String discordWebhook = "Discord webhook";
        String raffleKey = "Alphabot API Key";
        Client client = getExistingClientData(member.getId());
        if (client != null) {
          discordWebhook = client.getDiscordWebhook();
          raffleKey = client.getRaffleKey();
        }

        TextInput subject = createTextInput("webhook", "Discord webhook",
            "Your discord webhook to get messaged to", 50, 200,
            false, discordWebhook);

        TextInput body = createTextInput("apiKey", "Alphabot API key",
            "Your alphabot api key", 10, 255,
            true, raffleKey);

        Modal modal = Modal.create("raffleRegister", "Raffles registration")
            .addComponents(
                ActionRow.of(subject),
                ActionRow.of(body))
            .build();
        event.replyModal(modal).queue();
      }
      case "removeData" -> {
        Client client = clientRepository.findByDiscordId(event.getMember().getId());
        if (client != null)
          clientRepository.delete(client);

        event.reply("Removed from raffle").setEphemeral(true).queue();
      }
      case "enableSendErrorsToTelegram" -> {
        Client client = clientRepository.findByDiscordId(event.getMember().getId());
        if (client == null) {
          event.reply("You are not registered for raffles").setEphemeral(true).queue();
          break;
        }
        if (client.getSendToTelegram()) {
          event.reply("Already enabled").setEphemeral(true).queue();
          break;
        }
        client.setSendToTelegram(true);
        clientRepository.save(client);
        event.reply("Your error messages will be sent to telegram").setEphemeral(true).queue();
      }
      case "disableSendErrorsToTelegram" -> {
        Client client = clientRepository.findByDiscordId(event.getMember().getId());
        if (client == null) {
          event.reply("You are not registered for raffles").setEphemeral(true).queue();
          break;
        }

        if (!client.getSendToTelegram()) {
          event.reply("Already disabled").setEphemeral(true).queue();
          break;
        }

        client.setSendToTelegram(false);
        clientRepository.save(client);
        event.reply("Your will no longer receive error messages on telegram").setEphemeral(true).queue();
      }
      case "testtesttest" -> {
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

        Modal modal = Modal.create("testtesttest", "Raffles registration")
            .addComponents(ActionRow.of(subject), ActionRow.of(body))
            .build();
        event.replyModal(modal).queue();
      }
    }
  }

  private void sendFunctionalMessage(MessageChannel messageChannelSetup) {
    if (messageChannelSetup == null) {
      LOGGER.info("Message channel is null");
      return;
    }

    EmbedBuilder embedBuilder = new EmbedBuilder()
        .setTitle("Setup API and Webhook")
        .setDescription("""
            Select "Register for raffles" to participate in raffles or "Remove your data" to remove your data from raffle participation.
            """)
        .setFooter("Raffle Bot", "https://images.blur.io/_blur-prod/0x9f001721bb087fbbcd6fef2c140ed6892760e71b/724-69c69ff5da9d4454?w=1024")
        .setColor(0xF3E5AB);

    MessageEmbed messageEmbedSetup = embedBuilder.build();

    Button registerButton = Button.primary("register", "Register for raffles");
    Button removeDataButton = Button.danger("removeData", "Remove your data");
    Button enableSendErrorsTelegramButton = Button.secondary("enableSendErrorsToTelegram", "Enable errors telegram");
    Button disableSendErrorsTelegramButton = Button.secondary("disableSendErrorsToTelegram", "Disable errors telegram");
    LOGGER.info("send message embeds for setup API and Webhook to: " + messageChannelSetup.getName());
    messageChannelSetup.sendMessageEmbeds(messageEmbedSetup).setActionRow(registerButton, removeDataButton, enableSendErrorsTelegramButton, disableSendErrorsTelegramButton)
        .queue();
  }

  private void sendDescriptionMessage(MessageChannel messageChannelSetup, String title, String description) {
    if (messageChannelSetup == null) {
      LOGGER.info("Message channel is null");
      return;
    }
    EmbedBuilder descriptionEmbedBuilder = new EmbedBuilder()
        .setTitle(title)
        .setDescription(description)
        .setFooter("Raffle Bot", "https://images.blur.io/_blur-prod/0x9f001721bb087fbbcd6fef2c140ed6892760e71b/724-69c69ff5da9d4454?w=1024")
        .setColor(0xF3E5AB);

    MessageEmbed messageEmbed = descriptionEmbedBuilder.build();
    LOGGER.info("send message embeds for description");
    messageChannelSetup.sendMessageEmbeds(messageEmbed).queue();
  }

  private Client getExistingClientData(String clientId) {
    if (clientId == null) {
      return new Client("", "");
    }
    Client client = clientRepository.findByDiscordId(Objects.requireNonNull(clientId));
    if (client == null)
      return null;
    if (client.getDiscordWebhook() == null)
      client.setDiscordWebhook("");
    if (client.getRaffleKey() == null)
      client.setRaffleKey("");
    return client;
  }

  private TextInput createTextInput(String id, String label, String placeholder, int minLength, int maxLength, boolean required, String value) {
    TextInput.Builder textInputBuilder = TextInput.create(id, label, TextInputStyle.SHORT)
        .setPlaceholder(placeholder)
        .setMinLength(minLength)
        .setMaxLength(maxLength)
        .setRequired(required);

    if (StringUtils.hasText(value)
        && value.length() > minLength && value.length() < maxLength)
      textInputBuilder.setValue(value);

    return textInputBuilder.build();
  }
}
