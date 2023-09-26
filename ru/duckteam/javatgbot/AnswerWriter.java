package ru.duckteam.javatgbot;

import ru.duckteam.javatgbot.logic.BotResponse;

public interface AnswerWriter {
    void writeAnswer(BotResponse response);
}
