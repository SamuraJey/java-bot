package ru.duckteam.javatgbot.logic;

import org.telegram.telegrambots.meta.api.objects.Message;

public final class BotResponse {

    private final String userName;
    private final Long userId;
    private final Long chatId;
    private final Integer messageId;

    private final Message message;

    public BotResponse(String userName, Long userId, Long chatId, Integer messageId, Message message) {
        this.userName = userName;
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getChatId() {
        return chatId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public Message getMessage() {
        return message;
    }

}

