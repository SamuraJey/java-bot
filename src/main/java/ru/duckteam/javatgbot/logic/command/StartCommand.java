package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.BotCommand;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.UserStatusService;

public class StartCommand implements BotCommand {
    private static final String startString = "/start";

    public boolean needExecute(BotRequest request, UserStatusService userStatus) {
        return userStatus.isEmpty();
    }

    @Override
    public void execute(BotRequest request, AnswerWriter writer, UserStatusService userStatus) {
        BotResponse response = new BotResponse(
                request.getChatId(),
                getAnswer(userStatus,request));
        writer.writeAnswer(response);
    }

    private String getAnswer(UserStatusService userStatus,BotRequest request) {
        if (request.getMessage().equals(startString)){
            userStatus.setUserStatus(request.getChatId(), startString);
            return "write /echo or /events";
        }
        return "write /start";
    }
}
