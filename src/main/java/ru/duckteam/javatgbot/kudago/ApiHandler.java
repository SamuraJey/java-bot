package ru.duckteam.javatgbot.kudago;

import org.json.JSONObject;

public class ApiHandler {
    final JSONGetterFromURL getterFromURL = new JSONGetterFromURL();
    final JSONParser jsonParser = new JSONParser();

    public String getResponse() throws Exception { //  Если запустить программу отсюда, то она выдаст нам URL все называния событий


        //CreateURL url = new CreateURL();
        UnpackerURL unpacker = new UnpackerURL(CreateURL.getUrl());
        String urlResponse = unpacker.readUrl();
        JSONObject JSONResponse = getterFromURL.getJSONObject(urlResponse);
        String[] titles = jsonParser.getValue(JSONResponse, "title");
        String[] descriptions = jsonParser.getValue(JSONResponse, "description");
        String[] site_url = jsonParser.getValue(JSONResponse, "site_url");


        for (int i = 0; i < titles.length; i++) {
            String firstLetter = titles[i].substring(0, 1).toUpperCase();
            String restOfWord = titles[i].substring(1);
            titles[i] = firstLetter + restOfWord;
        }
        StringBuilder result = new StringBuilder();
        // Пока сделал деление на 2, чтоб не вылезало за лимит от телеграма 4096 символов
        for (int i = 0; i < titles.length / 2; i++) { // TODO сделать нормальное ограничение на количество символов
            result.append(titles[i]).append("\n");
            result.append(descriptions[i]).append("\n");
            result.append(site_url[i]).append("\n\n");
        }

        String mergedString = result.toString();
        System.out.println(mergedString);
        return mergedString;
    }
}
