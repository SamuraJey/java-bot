package ru.duckteam.javatgbot.logic;

public final class BotResponse {

    private final String userName;
    private final Long userId;
    private final Long chatId;
    private final Integer messageId;
    private final String message;

    public BotResponse(String userName, Long userId, Long chatId, Integer messageId,String message) {
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

    public String getMessage(){
        return message;
    }

}

