package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.*;

public class StartCommand implements BotCommand {
    private static final String startString = "/start";

    @Override
    public boolean needExecute(String message, UserStatus userStatus) {
        if (userStatus == null) {
            return true;
        }
        return userStatus.getUserCommand().equals(startString);
    }

    @Override
    public void execute(String message, Long chatId, AnswerWriter writer, UserStatus userStatus){
        try {
            BotResponse response = new BotResponse(
                    chatId,
                    getAnswer(message));
            writer.writeAnswer(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAnswer(String message) {
        if (message.equals(startString)){
            return "Напиши /echo или /events";
        }
        return "Напиши /start";
    }
    public String getNameCommand() {
        return startString;
    }
}
