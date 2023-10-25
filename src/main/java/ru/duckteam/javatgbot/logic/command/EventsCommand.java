package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.BotCommand;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.UserStatusService;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

public class EventsCommand implements BotCommand {

    ApiHandler apiHandler = new ApiHandler();

    private static final String eventsString = "/events";

            // TODO Разделить Map на три отдельных класса, каждый из которых будет обрабатывать свою часть логики
    @Override
    public boolean needExecute(BotRequest request) {
        return eventsString.equals(request.getMessage());
    }

    @Override
    public void execute(BotRequest request, AnswerWriter writer){
        try {
            BotResponse response = new BotResponse(
                    request.getChatId(),
                    getApiAnswer());
            writer.writeAnswer(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getApiAnswer() throws Exception {
        return apiHandler.getResponse();
    }
}
