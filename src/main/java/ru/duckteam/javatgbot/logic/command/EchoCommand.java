package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.logic.*;
import ru.duckteam.javatgbot.AnswerWriter;

public class EchoCommand implements BotCommand {
    private static final String echoString = "/echo";
    private final UserStatusService userStatusService;

    public EchoCommand(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @Override
    public boolean needExecute(String message, UserStatus userStatus, Long chatId) {
        if (userStatus == null || !userStatus.getUserCommand().equals(echoString) || userStatus.IsCommand(message)) {
            if (echoString.equals(message)) {
                userStatusService.clearUserStatus(chatId);
                userStatusService.setUserStatus(chatId, echoString);
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public void execute(String message, Long chatId, AnswerWriter writer, UserStatus userStatus) {
        BotResponse response = new BotResponse(
                    chatId,
                    getAnswer(message));
        writer.writeAnswer(response);
    }

    private String getAnswer(String message) {

        if (message == null){
            return "Можно отпралять только текстовые сообшения!";
        }

        if (message.equals(echoString)) {
            return "Выбран режим echo!";
        }
        return message;
    }
}
