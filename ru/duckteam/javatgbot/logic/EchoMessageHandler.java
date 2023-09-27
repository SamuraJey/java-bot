package ru.duckteam.javatgbot.logic;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.MessageHandler;

public class EchoMessageHandler implements MessageHandler {
    public void handle(BotRequest request, AnswerWriter writer) {
        String[] message = request.getMessageDetails();
        BotResponse response = new BotResponse(message[0], message[1], message[2], message[3]);
        writer.writeAnswer(response, request.getBot());
    }

}
