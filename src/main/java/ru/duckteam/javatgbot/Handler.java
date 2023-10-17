package ru.duckteam.javatgbot;

import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;


public interface Handler {
    void handle(BotRequest request, AnswerWriter writer, ApiHandler apiHandler, boolean isEcho, boolean isEvents);
}