package ru.duckteam.javatgbot.logic;

import org.telegram.telegrambots.meta.api.objects.Message;

public final class BotResponse {

    private final String userName;
    private final Long userId;
    private final Long chatId;
    private final Integer messageId;
    private final Message message;
    private final String responeString;
    private final boolean isEcho;
    private final boolean isEvents;

    public BotResponse(String userName, Long userId, Long chatId, Integer messageId, Message message, boolean isEvents, boolean isEcho, String responeString) {
        this.userName = userName;
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
        this.message = message;
        this.responeString = responeString;
        this.isEcho = isEcho;
        this.isEvents = isEvents;
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

    public String getResponeString() {return responeString;}

    public boolean getIsEcho() {return isEcho;}

    public boolean getIsEvents() {return isEvents;}
}

