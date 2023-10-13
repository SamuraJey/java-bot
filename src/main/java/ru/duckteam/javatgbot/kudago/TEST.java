package ru.duckteam.javatgbot.kudago;

public class TEST {
    public static void main(String[] args) throws Exception { //  Если запустить программу отсюда, то она выдаст нам URL все называния событий
        JSONPARSER jsonparser = new JSONPARSER();
        //CreateURL url = new CreateURL();
        UnpakerURL unpaker = new UnpakerURL(CreateURL.getUrl());
        jsonparser.methodName(unpaker.readUrl());
    }
}
