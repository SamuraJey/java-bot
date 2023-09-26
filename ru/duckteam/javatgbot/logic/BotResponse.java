package ru.duckteam.javatgbot.logic;

public final class BotResponse {
    private final String answer;

    public BotResponse(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
