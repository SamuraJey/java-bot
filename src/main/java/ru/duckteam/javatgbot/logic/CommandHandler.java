package ru.duckteam.javatgbot.logic;

public class CommandHandler {
    private boolean isEcho = false;
    private boolean isEvents = true;
    public void handle(String command) {
        switch (command) {
            case "/echo":
                isEvents = false;
                isEcho = !isEcho;
                break;
            case "/events":
                isEcho = false;
                isEvents = !isEvents;
                break;
            default:
                break;
        }
    }

    public boolean getIsEcho() {return isEcho;}

    public boolean getIsEvents() {return isEvents;}

}
