package ru.duckteam.javatgbot.logic;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

public final class BotRequest {
    private final User userName;
    private final Long userId;
    private final Long chatId;
    private final Integer messageId;
    private final Message message;


    public BotRequest(User userName, Long userId, Long chatId, Integer messageId, Message message) {
        this.userName = userName;
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
        this.message = message;

    }

    public String getUserName() {
        return userName.getUserName();
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
