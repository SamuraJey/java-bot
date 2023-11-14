package ru.duckteam.javatgbot.logic;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

public final class BotRequest {
    private final Long chatId;
    private final String message;


    public BotRequest(Long chatId, String message) {
        this.chatId = chatId;
        this.message = message;

    }

    public Long getChatId() {
        return chatId;
    }

    public String getMessage() {

        return message;
        // DTO - Data transfer object
    }


}
