package ru.duckteam.javatgbot.logic;

import org.telegram.telegrambots.meta.api.objects.Message;

public final class BotResponse {

    private final String userName;
    private final Long userId;
    private final Long chatId;
    private final Integer messageId;
    private final Message message;
    private final String responeString;
    //private final Integer commandId;

    public BotResponse(String userName, Long userId, Long chatId, Integer messageId, Message message,String responeString) {
        this.userName = userName;
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
        this.message = message;
        //this.commandId = commandId;
        this.responeString = responeString;
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

    //public Integer getCommandId() {
        //return commandId;
    //}

}

