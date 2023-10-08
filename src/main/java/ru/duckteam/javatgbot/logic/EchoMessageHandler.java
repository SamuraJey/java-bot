package ru.duckteam.javatgbot.logic;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.MessageHandler;

public class EchoMessageHandler implements MessageHandler {
    public void handle(BotRequest request, AnswerWriter writer) {
        BotResponse response = new BotResponse(request.getUserName(),
                request.getUserId(),
                request.getChatId(),
                request.getMessageId());
        writer.writeAnswer(response, request.getBot());
    }

}
