package ru.duckteam.javatgbot.kudago;

public class KgMessage {
    public static String getMessage() throws Exception { //  Если запустить программу отсюда, то она выдаст нам URL все называния событий
        JsonParser jsonparser = new JsonParser();
        //CreaterURL url = new CreaterURL();
        UnpakerURL unpaker = new UnpakerURL(UrlCreator.getUrl());
        return jsonparser.methodName(unpaker.readUrl());
    }
}
