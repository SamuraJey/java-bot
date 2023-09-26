package ru.duckteam.javatgbot.logic;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.MessageHandler;

public class EchoMessageHandler implements MessageHandler {
    public void handle(BotRequest request, AnswerWriter writer) {
        String message = request.getMessage();
        BotResponse response = new BotResponse(message);
        writer.writeAnswer(response);
    }

}
