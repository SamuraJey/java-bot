package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.Handler;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

public class MessageHandler implements Handler{
    private static final Logger LOGS = LoggerFactory.getLogger(MessageHandler.class);

    public void handle(BotRequest request, AnswerWriter writer, ApiHandler apiHandler)//,boolean isEvents,boolean isEcho)
    {

        BotResponse response = null;
        try {
            response = new BotResponse(request.getUserName(),
                    request.getUserId(),
                    request.getChatId(),
                    request.getMessageId(),
                    request.getMessage(),
                    request.getIsEcho(),
                    request.getIsEvents(),
                    apiHandler.getResponse());
        } catch (Exception e) {
            LOGS.error("Ошибка при получении ответа от API");
        }
        writer.writeAnswer(response);

        LOGS.info("Получено сообщение ID=%s %s".formatted(request.getMessageId(), request.getUserName()));
    }

    @Override
    public void handle(BotRequest request, AnswerWriter writer, ApiHandler apiHandler, boolean isEcho, boolean isEvents) {

    }
}
