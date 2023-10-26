package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.logic.BotCommand;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.UserStatusService;

public class EchoCommand implements BotCommand {
    private static final String echoString = "/echo";
    @Override
    public boolean needExecute(BotRequest request, UserStatusService userStatus) {
        if(userStatus.isEmpty() || !userStatus.getUserStatus(request.getChatId()).equals(echoString) || userStatus.isCommand(request.getMessage())) {
            if (echoString.equals(request.getMessage())){
                userStatus.setUserStatus(request.getChatId(), echoString);
            }
            return echoString.equals(request.getMessage());
        }
        return true;
    }

    @Override
    public void execute(BotRequest request, AnswerWriter writer,UserStatusService userStatus) {
        BotResponse response = new BotResponse(
                request.getChatId(),
                getAnswer(request));
        writer.writeAnswer(response);
    }

    private String getAnswer(BotRequest request) {
        if (request.getMessage().equals(echoString)){
            return "Выбран режим echo!";
        }
        return request.getMessage();
    }
}
