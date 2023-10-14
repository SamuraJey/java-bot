package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.MessageHandler;
import ru.duckteam.javatgbot.kudago.KgMessage;

public class EchoMessageHandler implements MessageHandler {
    private static final Logger LOGS = LoggerFactory.getLogger(EchoMessageHandler.class);

    public void handle(BotRequest request, AnswerWriter writer, KgMessage message) {
        BotResponse response = null;
        try {
            response = new BotResponse(request.getUserName(),
                    request.getUserId(),
                    request.getChatId(),
                    request.getMessageId(), message.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        writer.writeAnswer(response);


        LOGS.info("Получено сообщение ID=%s %s".formatted(request.getMessageId(), request.getUserName()));
    }

}
