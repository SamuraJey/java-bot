package ru.duckteam.javatgbot.kudago;

import org.json.JSONObject;

public class TEST {
    public static void main(String[] args) throws Exception { //  Если запустить программу отсюда, то она выдаст нам URL все называния событий
        JSONGetterFromURL jsonparser = new JSONGetterFromURL();
        JSONParser jsonparser_new = new JSONParser();

        //CreateURL url = new CreateURL();
        UnpackerURL unpacker = new UnpackerURL(CreateURL.getUrl());
        String urlResponse = unpacker.readUrl();
        JSONObject JSONResponse = jsonparser.methodName(urlResponse);
        String[] titles = jsonparser_new.getValue(JSONResponse, "title");
        for (String title : titles) {
            System.out.println(title);
        }
        String[] descriptions = jsonparser_new.getValue(JSONResponse, "description");
        for (String description : descriptions) {
            System.out.println(description);
        }

    }
}
