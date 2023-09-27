package ru.duckteam.javatgbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
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
        Bot bot = new Bot();
        BotSession botSession = botsApi.registerBot(new Bot());
        botsApi.registerBot(bot);
        //       bot.sendText(userId,"Hello World!");
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
        Message message = update.getMessage();
        User user = update.getMessage().getFrom();
        Long id = user.getId();
        sendText(id,message.getText());
        System.out.println(user.getFirstName() + " wrote " + message.getText());
    }

    @Override
    public String getBotUsername() {
        return "EchoBot";
    }

    public String getBotToken() {
        return "BOT_TOKEN";
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
