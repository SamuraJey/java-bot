package ru.duckteam.javatgbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.duckteam.javatgbot.console.ConsoleAnswerWriter;
import ru.duckteam.javatgbot.console.ConsoleInputReader;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.EchoMessageHandler;

public class Bot extends TelegramLongPollingBot {
    
    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = botsApi.registerBot(new Bot());

//        InputReader reader = new ConsoleInputReader();
//        AnswerWriter writer = new ConsoleAnswerWriter();
//        MessageHandler handler = new EchoMessageHandler();
//
//        while(true){
//            BotRequest request = reader.getUserInput();
//            handler.handle(request,writer);
//        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        System.out.println(user.getFirstName() + " wrote " + msg.getText());
    }

    @Override
    public String getBotUsername() {
        return "AutoPosterForLections_bot";
    }

    public String getBotToken() {
        return "BOT_TOKEN";
    }

}
