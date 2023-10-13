package ru.duckteam.javatgbot.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.duckteam.javatgbot.MessageConverter;
import ru.duckteam.javatgbot.logic.BotRequest;

public class TelegramMessageConverter implements MessageConverter {
    @Override
    public BotRequest convertMessage(Update update) {
        var message = update.getMessage();
        var userName = message.getFrom();
        var userId = userName.getId();
        var chatId = message.getChatId();
        var messageId = message.getMessageId();

        return new BotRequest(userName, userId, chatId, messageId, message);
    }
}
