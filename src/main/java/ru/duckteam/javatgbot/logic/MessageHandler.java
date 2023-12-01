package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.Handler;

import java.util.List;

public class MessageHandler implements Handler {
    private static final Logger LOGS = LoggerFactory.getLogger(MessageHandler.class);
    private final List<BotCommand> commands;
    private final UserStatusService userStatusService;

    public MessageHandler(UserStatusService userStatusService, List<BotCommand> commands) {
        this.commands = commands;
        this.userStatusService = userStatusService;
    }


    @Override
    public void handle(BotRequest request, AnswerWriter writer) {
        UserStatus userStatus = userStatusService.getUserData(request.getChatId());

        for (BotCommand command : commands) {
            if (command.needExecute(request.getMessage(), userStatus, request.getChatId())) {
                userStatusService.setUserStatus(request.getChatId(), command.getNameCommand());
//                userStatusService.startCleanupTimer(request.getChatId());
                command.execute(request.getMessage(), request.getChatId(), writer, userStatus);
                break;
            }
        }
        LOGS.info("Получено сообщение ID=%s %s".formatted(request.getChatId(), request.getMessage()));
    }
}