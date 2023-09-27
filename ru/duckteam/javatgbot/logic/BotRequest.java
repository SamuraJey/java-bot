package ru.duckteam.javatgbot.logic;

import org.telegram.telegrambots.meta.api.objects.User;
import ru.duckteam.javatgbot.Bot;

public final class BotRequest {
//    private final String message;
    private final User userName;
    private final Long userId;
    private final Long chatId;
    private final Integer messageId;
    private final Bot bot;

//    public BotRequest(String message){
//        this.message = message;
//    }

    public BotRequest(User userName, Long userId, Long chatId, Integer messageId, Bot bot) {
        this.userName = userName;
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
        this.bot = bot;
    }

    public String[] getMessageDetails(){
        final String[] msgDetails = new String[4];
        msgDetails[0] = userName.getUserName(); // String
        msgDetails[1] = userId.toString(); // Long
        msgDetails[2] = chatId.toString(); // Long
        msgDetails[3] = messageId.toString(); // Integer

        return msgDetails;
    }

    public Bot getBot(){
        return bot;
    }
}
