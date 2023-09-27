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

    public BotResponse(String userName, String userId, String chatId, String messageId) {
        this.userName = userName;
        this.userId = Long.valueOf(userId);
        this.chatId = Long.valueOf(chatId);
        this.messageId = Integer.valueOf(messageId);
    }

    public String[] getAnswerDetails(){
        final String[] answerDetails = new String[4];
        answerDetails[0] = userName; // String
        answerDetails[1] = userId.toString(); // Long
        answerDetails[2] = chatId.toString(); // Long
        answerDetails[3] = messageId.toString(); // Integer

        return answerDetails;
    }
}

