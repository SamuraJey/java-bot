package ru.duckteam.javatgbot;

public class EchoMessageHandler implements MessageHandler {

    @Override
    public void handle(BotRequest request, AnswerWriter writer) {
        String message = request.getMessage();
        BotResponse response = new BotResponse(message);
        writer.writeAnswer(response);
    }

}
