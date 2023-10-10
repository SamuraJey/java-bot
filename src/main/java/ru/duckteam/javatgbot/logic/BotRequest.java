package ru.duckteam.javatgbot.logic;

import org.telegram.telegrambots.meta.api.objects.User;

public final class BotRequest {
    private final User userName;
    private final Long userId;
    private final Long chatId;
    private final Integer messageId;

    public BotRequest(User userName, Long userId, Long chatId, Integer messageId) {
        this.userName = userName;
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
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
}
