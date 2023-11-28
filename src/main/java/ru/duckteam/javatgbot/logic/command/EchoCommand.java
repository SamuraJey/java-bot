package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.logic.*;
import ru.duckteam.javatgbot.AnswerWriter;

public class EchoCommand implements BotCommand {
    private static final String echoString = "/echo";

    @Override
    public boolean needExecute(String message, UserStatus userStatus) {
        if(userStatus == null || !userStatus.getUserCommand().equals(echoString) || userStatus.IsCommand(message)) {
            return echoString.equals(message);
        }
        return true;
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
        if (message.equals(echoString)){
            return "Выбран режим echo!";
        }
        return message;
    }

    public String getNameCommand() {
        return echoString;
    }
}
