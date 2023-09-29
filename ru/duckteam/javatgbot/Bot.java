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
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = botsApi.registerBot(new Bot());
    }

    public void onUpdateReceived(Update update) {
        InputReader reader = new TelegramInputReader();
        AnswerWriter writer = new TelegramAnswerWriter();
        MessageHandler handler = new EchoMessageHandler();
        Message msg = update.getMessage();
        User user = msg.getFrom();
        System.out.println(user.getFirstName() + " wrote " + msg.getText());
        BotRequest request = reader.getUserInput(update, this);
        handler.handle(request, writer);
    }

    @Override
    public String getBotUsername() {
        Secret secret = new Secret();
        return secret.getBotName();

    }

    public String getBotToken() {
        Secret secret = new Secret();
        return secret.getApiKey();
    }
}
