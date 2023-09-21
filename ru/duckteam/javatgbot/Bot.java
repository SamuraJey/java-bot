package ru.duckteam.javatgbot;

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
