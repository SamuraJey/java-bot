package ru.duckteam.javatgbot;

import ru.duckteam.javatgbot.logic.BotRequest;

public interface MessageHandler {
    void handle(BotRequest request, AnswerWriter writer);
}