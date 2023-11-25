package ru.duckteam.javatgbot.logic;

import ru.duckteam.javatgbot.AnswerWriter;

public interface BotCommand {
    boolean needExecute(String message,UserData userData);
    void execute(String message,Long chatId,AnswerWriter writer,UserData userData);
    String getNameCommand();
}
