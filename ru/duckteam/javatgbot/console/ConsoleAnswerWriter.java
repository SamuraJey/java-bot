package ru.duckteam.javatgbot.console;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.Bot;
import ru.duckteam.javatgbot.logic.BotResponse;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;


public class ConsoleAnswerWriter implements AnswerWriter {
    public void writeAnswer(BotResponse response, Bot bot) {
        String[] answerDetails = response.getAnswerDetails();
        String userName = answerDetails[0];
        long userId = Long.parseLong(answerDetails[1]);
        long chatId = Long.parseLong(answerDetails[2]);
        int messageId = Integer.parseInt(answerDetails[3]);

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
