package ru.duckteam.javatgbot.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.MessageConverter;
import ru.duckteam.javatgbot.MessageHandler;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.EchoMessageHandler;

public class Bot extends TelegramLongPollingBot implements AnswerWriter {
    private final String botName;
    private final MessageConverter reader = new TelegramMessageConverter();
    private final MessageHandler handler = new EchoMessageHandler();

    public Bot(String apiKey, String botName) {
        super(apiKey);
        this.botName = botName;
    }

    public void onUpdateReceived(Update update) {
        BotRequest request = reader.convertMessage(update);
        handler.handle(request, this);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void writeAnswer(BotResponse response) {
        //String userName = response.getUserName();
        long userId = response.getUserId();
        //long chatId = response.getChatId();
        int messageId = response.getMessageId();

        CopyMessage cm = CopyMessage.builder()
                .fromChatId(userId)  //We copy from the user
                .chatId(userId)      //And send it back to him
                .messageId(messageId)            //Specifying what message
                .build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
