package ru.duckteam.javatgbot.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.duckteam.javatgbot.Bot;
import ru.duckteam.javatgbot.InputReader;
import ru.duckteam.javatgbot.logic.BotRequest;

public class TelegramInputReader implements InputReader {
    @Override
    public BotRequest getUserInput(Update update, Bot bot) {
        var message = update.getMessage();
        var userName = message.getFrom();
        var userId = userName.getId();
        var chatId = message.getChatId();
        var messageId = message.getMessageId();

        return new BotRequest(userName, userId, chatId, messageId, bot);
    }
}
