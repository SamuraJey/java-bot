package ru.duckteam.javatgbot;

import ru.duckteam.javatgbot.logic.BotRequest;


public interface Handler {
    void handle(BotRequest request, AnswerWriter writer);
}