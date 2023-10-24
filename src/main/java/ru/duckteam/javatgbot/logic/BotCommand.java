package ru.duckteam.javatgbot.logic;

import ru.duckteam.javatgbot.AnswerWriter;

public interface BotCommand {
    boolean needExecute(BotRequest request);
    void execute(BotRequest request, AnswerWriter writer);
}
