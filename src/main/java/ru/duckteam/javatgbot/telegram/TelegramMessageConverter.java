package ru.duckteam.javatgbot.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.duckteam.javatgbot.MessageConverter;
import ru.duckteam.javatgbot.logic.BotRequest;

public class TelegramMessageConverter implements MessageConverter {

    @Override
    public BotRequest convertMessage(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String textMessage = message.getText();

        return new BotRequest(chatId, textMessage);
    }
}
