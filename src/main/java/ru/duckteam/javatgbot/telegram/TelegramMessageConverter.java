package ru.duckteam.javatgbot.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.duckteam.javatgbot.MessageConverter;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.CommandHandler;

public class TelegramMessageConverter implements MessageConverter {

    private final CommandHandler commandHandler = new CommandHandler();
    @Override
    public BotRequest convertMessage(Update update) {
        var message = update.getMessage();
        var userName = message.getFrom();
        var userId = userName.getId();
        var chatId = message.getChatId();
        var messageId = message.getMessageId();

        if (message.isCommand()) {
            commandHandler.handle(message.getText());
            // We don't want to echo commands, so we exit
        }
        return new BotRequest(userName, userId, chatId, messageId, message,commandHandler.getIsEcho(),commandHandler.getIsEvents());
    }
}
