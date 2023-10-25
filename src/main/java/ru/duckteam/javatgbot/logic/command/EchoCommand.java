package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.logic.BotCommand;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.UserStatusService;

public class EchoCommand implements BotCommand {
    private static final String echoString = "/echo";
    @Override
    public boolean needExecute(BotRequest request) {
        return echoString.equals(request.getMessage());
    }

    @Override
    public void execute(BotRequest request, AnswerWriter writer) {
        BotResponse response = new BotResponse(
                request.getChatId(),
                getAnswer(request));
        writer.writeAnswer(response);
    }

    private String getAnswer(BotRequest request) {
        return request.getMessage();
    }
}
