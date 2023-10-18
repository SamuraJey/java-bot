package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.Handler;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

import java.util.Map;


public class MessageHandler implements Handler {
    private static final Logger LOGS = LoggerFactory.getLogger(MessageHandler.class);
    private final Map<String, Integer> dictionary;
    private final ApiHandler apiHandler = new ApiHandler();
    private Integer currentMode = 1;

    public MessageHandler() {
        dictionary = Map.of(
                "/echo", 1,
                "/events", 2
        );
    }

    @Override
    public void handle(BotRequest request, AnswerWriter writer) {
        /*
        Done_TODO Сейчас бот при выборе режима /events сразу после команды отправляет все ивенты.
        Done_TODO Или, что бы он отправлял ивенты сразу после команды, но переходил после этого в режим /echo
        */
        BotResponse response = null;
        if (request.getMessage().isCommand()) {
            currentMode = dictionary.get(request.getMessage().getText());
        } else if (request.getMessage().isCommand() && currentMode == null) {
            LOGS.error("Ошибка при получении команды1");
            return;
        }

        switch (currentMode) {
            case 1: // /echo command.
                if (request.getMessage().getText().equals("/echo")) {
                    response = new BotResponse(request.getUserName(),
                            request.getUserId(),
                            request.getChatId(),
                            request.getMessageId(),
                            request.getMessage(),
                            currentMode,
                            "Включен режим echo.");
                    writer.writeAnswer(response);
                    LOGS.info("Получена команда messageID=%s Username=%s Text=%s".formatted(request.getMessageId(),
                            request.getUserName(), request.getMessage().getText()));
                    return;
                }
                response = new BotResponse(request.getUserName(),
                        request.getUserId(),
                        request.getChatId(),
                        request.getMessageId(),
                        request.getMessage(),
                        currentMode,
                        request.getMessage().getText());
                writer.writeAnswer(response);
                LOGS.info("Получено сообщение messageID=%s Username=%s Text=%s".formatted(request.getMessageId(),
                        request.getUserName(), request.getMessage().getText()));
                return;
            case 2: // /events command.

                try {
                    response = new BotResponse(request.getUserName(),
                            request.getUserId(),
                            request.getChatId(),
                            request.getMessageId(),
                            request.getMessage(),
                            currentMode,
                            apiHandler.getResponse());
                    writer.writeAnswer(response);
                    setCurrentMode(1);
                    LOGS.info("Получено сообщение messageID=%s Username=%s Text=%s".formatted(request.getMessageId(),
                            request.getUserName(), request.getMessage().getText()));
                } catch (Exception e) {
                    LOGS.error("Ошибка при получении ответа от API", e);
                }
                return;
            default:
                LOGS.error("Ошибка при получении команды");
        }

//        LOGS.info("Получено сообщение ID=%s %s %s".formatted(request.getMessageId(), request.getUserName(), request.getMessage().getText()));
    }

    public void setCurrentMode(Integer mode) {
        currentMode = mode;
    }

}
