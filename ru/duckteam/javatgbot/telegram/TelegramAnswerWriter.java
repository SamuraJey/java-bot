package ru.duckteam.javatgbot.telegram;

import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.Bot;
import ru.duckteam.javatgbot.logic.BotResponse;


public class TelegramAnswerWriter implements AnswerWriter {
    public void writeAnswer(BotResponse response, Bot bot) {
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
            bot.execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

