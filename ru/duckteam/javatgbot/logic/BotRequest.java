package ru.duckteam.javatgbot.logic;

public final class BotRequest {
    private final String message;

    public BotRequest(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
