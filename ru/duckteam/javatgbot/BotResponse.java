package ru.duckteam.javatgbot;

public class BotResponse {
    private String answer = new String();

    public BotResponse(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
