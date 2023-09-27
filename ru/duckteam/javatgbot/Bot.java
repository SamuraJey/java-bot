package ru.duckteam.javatgbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageId;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();
        botsApi.registerBot(bot);
//        InputReader reader = new ConsoleInputReader();
//        AnswerWriter writer = new ConsoleAnswerWriter();
//        MessageHandler handler = new EchoMessageHandler();
//
//        while(true){
//            BotRequest request = reader.getUserInput();
//            handler.handle(request,writer);
//        }
    }


    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        User user = update.getMessage().getFrom();
        Long userId = user.getId();
        copyMessage(userId,message.getMessageId());
        //sendText(userId,message.getText());
        System.out.println(user.getFirstName() + " wrote " + message.toString());
    }

    @Override
    public String getBotUsername() {
        return "EchoBot";
    }

    public String getBotToken() {
        return "BOT_TOKEN";
    }

    public void copyMessage(Long who, Integer msgId){
        CopyMessage cm = CopyMessage.builder().fromChatId(who.toString()).chatId(who.toString()).messageId(msgId).build();
        try {
            MessageId execute = execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
