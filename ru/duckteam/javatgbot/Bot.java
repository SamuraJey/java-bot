package ru.duckteam.javatgbot;

import ru.duckteam.javatgbot.console.ConsoleAnswerWriter;
import ru.duckteam.javatgbot.console.ConsoleInputReader;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.EchoMessageHandler;

public class Bot {
    
    public static void main(String[] args) {

        InputReader reader = new ConsoleInputReader();
        AnswerWriter writer = new ConsoleAnswerWriter();
        MessageHandler handler = new EchoMessageHandler();

        while(true){
            BotRequest request = reader.getUserInput();
            handler.handle(request,writer);
        }
    }
}
