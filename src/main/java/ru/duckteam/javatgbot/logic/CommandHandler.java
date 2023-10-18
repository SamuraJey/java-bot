package ru.duckteam.javatgbot.logic;

import java.util.Map;

@Deprecated
public class CommandHandler {

    private Integer currentMode = 1;

    private final Map<String, Integer> dictionary;

    public CommandHandler() {
        dictionary = Map.of(
                "/echo", 1,
                "/events", 2
        );
    }
    public void handleCommand(String command) {

        switch (command) {
            case "/echo":
                currentMode = dictionary.get(command);
                break;
            case "/events":
                currentMode = dictionary.get(command);
                break;
            default:
                break;
        }
    }

    public Integer getCurrentMode() {
        return currentMode;
    }

}
