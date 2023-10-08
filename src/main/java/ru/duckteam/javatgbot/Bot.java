package ru.duckteam.javatgbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.EchoMessageHandler;
import ru.duckteam.javatgbot.telegram.TelegramAnswerWriter;
import ru.duckteam.javatgbot.telegram.TelegramInputReader;

public class Bot extends TelegramLongPollingBot {
    public static void main1(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = botsApi.registerBot(new Bot());
    }

    public Bot() {
        super(Secret.getApiKey());
    }

    public void onUpdateReceived(Update update) {
        InputReader reader = new TelegramInputReader();
        AnswerWriter writer = new TelegramAnswerWriter();
        MessageHandler handler = new EchoMessageHandler();
        Message msg = update.getMessage();
        User user = msg.getFrom();
        BotRequest request = reader.getUserInput(update, this);
        handler.handle(request, writer);

//        System.out.println(user.getFirstName() + user.getLastName());
        System.out.printf("Получено сообщение ID=%s\n%s %s написал: %s\n", msg.getMessageId(), user.getFirstName(),
                user.getLastName(), msg.getText());
    }

    @Override
    public String getBotUsername() {
        return Secret.getBotName();
    }
}
