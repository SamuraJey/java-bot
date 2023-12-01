package ru.duckteam.javatgbot.logic;

import ru.duckteam.javatgbot.AnswerWriter;

public interface BotCommand {
    boolean needExecute(String message, UserStatus userStatus, Long chatId);
    void execute(String message, Long chatId, AnswerWriter writer, UserStatus userStatus);
    String getNameCommand();
}
