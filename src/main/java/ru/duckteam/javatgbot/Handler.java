package ru.duckteam.javatgbot;

import ru.duckteam.javatgbot.logic.BotRequest;


public interface Handler {
    // Саша, если ты хочешь поменять интерфейс, то не меняй этот интерфейс, а наследуй от него и делай новый.
    // Нам же не зря про это на лекции рассказали.
    void handle(BotRequest request, AnswerWriter writer);
}