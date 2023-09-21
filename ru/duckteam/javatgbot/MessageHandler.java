package ru.duckteam.javatgbot;

public interface MessageHandler {
    void handle(BotRequest request, AnswerWriter writer);
}