package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.*;

public class EventsCommand implements BotCommand {
    private static final String eventsString = "/events";

            // TODO Разделить Map на три отдельных класса, каждый из которых будет обрабатывать свою часть логики
    @Override
    public boolean needExecute(BotRequest request,UserStatusService userStatus) {
        if(userStatus.isEmpty() || !userStatus.getUserStatus(request.getChatId()).equals(eventsString) || userStatus.isCommand(request.getMessage())) {
            if (eventsString.equals(request.getMessage())){
                userStatus.setUserStatus(request.getChatId(), eventsString);
            }
            return eventsString.equals(request.getMessage());
        }
        return true;
    }

    @Override
    public void execute(BotRequest request, AnswerWriter writer,UserStatusService userStatus){
        try {
            BotResponse response = new BotResponse(
                    request.getChatId(),
                    getApiAnswer(request.getMessage(), request.getChatId(), userStatus));
            writer.writeAnswer(response);

            if (userStatus.needDeleteStatus(request.getChatId())){
                userStatus.RemoveUserStatus(request.getChatId());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getApiAnswer(String param,Long chatId,UserStatusService userStatus) throws Exception {
        return userStatus.getAnswer(chatId,param);
    }
}
