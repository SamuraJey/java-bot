package ru.duckteam.javatgbot.logic;

public final class BotResponse {

    private final String userName;
    private final Long userId;
    private final Long chatId;
    private final Integer messageId;

    public BotResponse(String userName, Long userId, Long chatId, Integer messageId) {
        this.userName = userName;
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
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

}

