package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.*;

public class StartCommand implements BotCommand {
    private static final String startString = "/start";

    @Override
    public boolean needExecute(String message, UserData userData) {
        if (userData == null) {
            return true;
        }
        return userData.getUserCommand().equals(startString);
    }

    @Override
    public void execute(String message, Long chatId, AnswerWriter writer,UserData userData){
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
