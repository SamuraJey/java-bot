package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.Handler;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageHandler implements Handler {
    private static final Logger LOGS = LoggerFactory.getLogger(MessageHandler.class);
    private final List<BotCommand> commands;
    private final UserStatusService userStatus;
    public MessageHandler(List<BotCommand> commands) {
        this.commands = commands;
        userStatus = new UserStatusService();
    }
    // TODO создавать команды внутри коструктора или возможно передавать как параметр


    @Override
    public void handle(BotRequest request, AnswerWriter writer) {
        UserData userData = userStatus.getUserData(request.getChatId());
        for (BotCommand command : commands) {
            if (command.needExecute(request.getMessage(),userData)) {
                userStatus.setUserStatus(request.getChatId(), command.getNameCommand());
                command.execute(request.getMessage(), request.getChatId(),writer,userData);
                break;
            }
        }
        LOGS.info("Получено сообщение ID=%s %s".formatted(request.getChatId(), request.getMessage()));
    }
}