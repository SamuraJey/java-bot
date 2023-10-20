package ru.duckteam.javatgbot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.duckteam.javatgbot.telegram.Bot;

public class Main {
    public static void main(String[] args) throws Exception {
        Bot bot = new Bot(Secret.getApiKey(), Secret.getBotName());
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
