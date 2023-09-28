package ru.duckteam.javatgbot.logic;

import org.telegram.telegrambots.meta.api.objects.User;

public final class BotResponse {
    //    private final String message;
    private final String userName;
    private final Long userId;
    private final Long chatId;
    private final Integer messageId;

//    public BotRequest(String message){
//        this.message = message;
//    }

    public BotResponse(String userName, Long userId, Long chatId, Integer messageId) {
        this.userName = userName;
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
    }

   /* public String[] getAnswerDetails(){
        final String[] answerDetails = new String[4];
        answerDetails[0] = userName; // String
        answerDetails[1] = userId.toString(); // Long
        answerDetails[2] = chatId.toString(); // Long
        answerDetails[3] = messageId.toString(); // Integer

        return answerDetails;
    }*/

    public String getUserName(){
        return userName;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getChatId(){
        return chatId;
    }

    public Integer getMessageId(){
        return messageId;
    }

}

