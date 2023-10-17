package ru.duckteam.javatgbot.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.MessageConverter;
import ru.duckteam.javatgbot.MessageHandler;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.EchoMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bot extends TelegramLongPollingBot implements AnswerWriter {
    private final String botName;
    private final MessageConverter reader = new TelegramMessageConverter();
    private final MessageHandler handler = new EchoMessageHandler();
    private final ApiHandler apiHandler = new ApiHandler();
    private boolean isEcho = false;

    private boolean isEvents = true;

    private static final Logger LOGS = LoggerFactory.getLogger(Bot.class);

    public Bot(String apiKey, String botName) {
        super(apiKey);
        this.botName = botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        BotRequest request = reader.convertMessage(update);
        if (request.getMessage().isCommand()) {
            commandHandler(request.getMessage().getText());
            return;                                     // We don't want to echo commands, so we exit
        }
        handler.handle(request, this);
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

    public void sendText(BotResponse response, String text) {
        SendMessage sm = SendMessage.builder()
                .chatId(response.getChatId().toString()) //Who are we sending a message to
                .text(text).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }

//        LOGS.info("Получено сообщение ID=%s %s".formatted(response.getMessageId(), response.getUserName()));
    }

//    private void scream(BotResponse response) {
//        if (response.getMessage().hasText()) {
//            sendText(response, response.getMessage().getText().toUpperCase());
//        }
//    }

    @Override
    public void writeAnswer(BotResponse response) {
        if (isEcho) {
            copyMessage(response);
        } else if (isEvents) {
//            ApiHandler apiHandler = new ApiHandler();
            try {
                String answer = apiHandler.getResponse();
                sendText(response, answer);
            } catch (Exception e) {
                LOGS.error("Ошибка при получении ответа от API");
                //e.printStackTrace();
            }
        } else {
            sendText(response, "Что-то пошло не так.");
        }
    }

    private void commandHandler(String command) {
        switch (command) {
            case "/echo":
                isEvents = false;
                isEcho = !isEcho;
                break;
            case "/events":
                isEcho = false;
                isEvents = !isEvents;
                break;
            default:
                break;
        }
    }
}
