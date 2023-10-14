package ru.duckteam.javatgbot;

import ru.duckteam.javatgbot.kudago.KgMessage;
import ru.duckteam.javatgbot.logic.BotRequest;


public interface MessageHandler {
    void handle(BotRequest request, AnswerWriter writer, KgMessage message);
}