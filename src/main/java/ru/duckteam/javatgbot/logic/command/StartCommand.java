package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.*;

public class StartCommand implements BotCommand {
    private static final String startString = "/start";
    private final UserStatusService userStatusService;

    public StartCommand(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @Override
    public boolean needExecute(String message, UserStatus userStatus, Long chatId) {
        if (userStatus == null) {
            userStatusService.setUserStatus(chatId, startString);
            return true;
        }

        if (startString.equals(message)) {
            userStatusService.setUserStatus(chatId, startString);
            userStatusService.clearUserStatus(chatId);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void execute(String message, Long chatId, AnswerWriter writer, UserStatus userStatus) {
        BotResponse response = new BotResponse(
                chatId,
                getAnswer(message));
        writer.writeAnswer(response);
    }

    private String getAnswer(String message) {
        if (message.equals(startString)) {
            return "Напиши /echo или /events или /weather";
        }
        return "Напиши /start";
    }
}
