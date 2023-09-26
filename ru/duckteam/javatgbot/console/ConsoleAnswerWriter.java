package ru.duckteam.javatgbot.console;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.BotResponse;

public class ConsoleAnswerWriter implements AnswerWriter {
    public void writeAnswer(BotResponse response) {

        System.out.println("Ответ: " + response.getAnswer());
    }
}
