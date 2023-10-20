package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.Handler;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

import java.util.Map;

import static org.apache.commons.io.IOUtils.writer;


public class MessageHandler implements Handler {
    private static final Logger LOGS = LoggerFactory.getLogger(MessageHandler.class);
    private final Map<String, String> dictionary;
    //private Integer currentMode = 1;

    public MessageHandler() throws Exception {

        ApiHandler apiHandler = new ApiHandler();
        dictionary = Map.of(
                "/echo","Включен режим echo." ,
                "/events", apiHandler.getResponse(),
                "/A" ,"TEST"
        );
    }

    @Override
    public void handle(BotRequest request,AnswerWriter writer) {
        /*
        Done_TODO Сейчас бот при выборе режима /events сразу после команды отправляет все ивенты.
        Done_TODO Или, что бы он отправлял ивенты сразу после команды, но переходил после этого в режим /echo
        TODO Сейчас если один пользователь переключает режим, то он меняется у всех пользователей, надо как-то исправлять.
        */

        var key = request.getMessage().getText();
        var chatId = request.getMessage().getChatId().toString();
        BotResponse response = null;

        if (dictionary.containsKey(key)) {
            response = new BotResponse(request.getUserName(),
                    request.getUserId(),
                    request.getChatId(),
                    request.getMessageId(),
                    request.getMessage(),
                    dictionary.get(key));
            writer.writeAnswer(response);
        } else {
            response = new BotResponse(request.getUserName(),
                    request.getUserId(),
                    request.getChatId(),
                    request.getMessageId(),
                    request.getMessage(),
                    request.getMessage().getText());
            writer.writeAnswer(response);
            }

        LOGS.info("Получено сообщение ID=%s %s %s".formatted(request.getMessageId(), request.getUserName(), request.getMessage().getText()));


        /*if (request.getMessage().isCommand()) {

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
            case 3:
                response = new BotResponse(request.getUserName(),
                        request.getUserId(),
                        request.getChatId(),
                        request.getMessageId(),
                        request.getMessage(),
                        currentMode,
                        "TEST");
                writer.writeAnswer(response);
                setCurrentMode(3);
                LOGS.info("Получено сообщение messageID=%s Username=%s Text=%s".formatted(request.getMessageId(),
                        request.getUserName(), request.getMessage().getText()));
                LOGS.info("Current mode is %s".formatted(currentMode));
                return;
            default:
                LOGS.error("Ошибка при получении команды")*/
        }

//        LOGS.info("Получено сообщение ID=%s %s %s".formatted(request.getMessageId(), request.getUserName(), request.getMessage().getText()));
    }

    //public void setCurrentMode(Integer mode) {
   //     currentMode = mode;
    //}

//}
