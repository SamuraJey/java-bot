package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.Handler;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

public class MessageHandler implements Handler{
    private static final Logger LOGS = LoggerFactory.getLogger(MessageHandler.class);
    private final CommandHandler commandHandler = new CommandHandler();

    private final ApiHandler apiHandler = new ApiHandler();

    @Override
    public void handle(BotRequest request, AnswerWriter writer)//,boolean isEvents,boolean isEcho)
    {
        /*
        TODO Сейчас бот при выборе режима /events сразу после команды отправляет все ивенты.
        TODO Нужно сделать так, чтобы бот отправлял ивенты только после получения сообщения (не команды) от пользователя.
        TODO Или, что бы он отправлял ивенты сразу после команды, но переходил после этого в режим /echo
        */
        BotResponse response = null;
        if(request.getMessage().isCommand()) {
            commandHandler.handleCommand(request.getMessage().getText());
        }
        else if (request.getMessage().isCommand() && commandHandler.getCurrentMode() == null) {
            LOGS.error("Ошибка при получении команды");
        }

        if(commandHandler.getCurrentMode() == 1)
        {
            response = new BotResponse(request.getUserName(),
                    request.getUserId(),
                    request.getChatId(),
                    request.getMessageId(),
                    request.getMessage(),
                    request.getCommandId(),
                    request.getMessage().getText());
            writer.writeAnswer(response);
            LOGS.info("Получена команда ID=%s %s".formatted(request.getMessageId(), request.getUserName()));
            return;
        }
        else if(commandHandler.getCurrentMode() == 2)
        {
            try {
                response = new BotResponse(request.getUserName(),
                        request.getUserId(),
                        request.getChatId(),
                        request.getMessageId(),
                        request.getMessage(),
                        request.getCommandId(),
                        apiHandler.getResponse());
                writer.writeAnswer(response);

            } catch (Exception e) {
                LOGS.error("Ошибка при получении ответа от API");
            }
            return;
        }

        LOGS.info("%s".formatted(commandHandler.getCurrentMode()));
        LOGS.info("Получено сообщение ID=%s %s".formatted(request.getMessageId(), request.getUserName()));
    }
}
