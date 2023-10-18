package ru.duckteam.javatgbot.telegram;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.MessageConverter;
import ru.duckteam.javatgbot.command.BaseCommand;
import ru.duckteam.javatgbot.command.StartCommand;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.MessageHandler;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;


public class Bot extends TelegramLongPollingCommandBot implements AnswerWriter {
    private final String botName;
    private final MessageConverter reader = new TelegramMessageConverter();
    private final MessageHandler handler = new MessageHandler();
    private final ApiHandler apiHandler = new ApiHandler();

    public Bot(String apiKey, String botName, BaseCommand startCommand) {
        super(apiKey);
        registerAll(startCommand);
        this.botName = botName;
    }



    @Override
    public String getBotUsername() {
        return botName;
    }

    private void copyMessage(BotResponse response) {
        CopyMessage cm = CopyMessage.builder()
                .fromChatId(response.getUserId())  //We copy from the user
                .chatId(response.getUserId())      //And send it back to him
                .messageId(response.getMessageId())            //Specifying what message
                .build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendText(BotResponse response) {
        SendMessage sm = SendMessage.builder()
                .chatId(response.getChatId().toString()) //Who are we sending a message to
                .text(response.getResponeString()).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }


    @Override
    public void writeAnswer(BotResponse response) {
        sendText(response);
    }

    // Зачем нам этот метод?
    private void sendText(BotResponse response, String s) {
        SendMessage sm = SendMessage.builder()
                .chatId(response.getChatId().toString()) //Who are we sending a message to
                .text(s).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        BotRequest request = reader.convertMessage(update);
        handler.handle(request, this);//,isEcho,isEvents);
    }

    @Override
    public boolean filter(Message message) {
        return super.filter(message);
    }
}
