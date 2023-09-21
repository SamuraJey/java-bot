package ru.duckteam.javatgbot;

public class ConsoleAnswerWriter implements AnswerWriter {

    @Override
    public void writeAnswer(BotResponse response) {

        System.out.println("Ответ: " + response.getAnswer());
    }
}
