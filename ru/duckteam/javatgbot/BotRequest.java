package ru.duckteam.javatgbot;

public class BotRequest {
    private String message = new String();
    public BotRequest(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
