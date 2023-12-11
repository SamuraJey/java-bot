package ru.duckteam.javatgbot.logic.kudago;

import java.io.IOException;
import java.net.URISyntaxException;

public class ApiHandler {

    public String getResponse(String location, boolean isFree, long firstDayTimestamp, long secondDayTimestamp)
            throws IOException {

        URLHandler urlHandler = new URLHandler(location, isFree, firstDayTimestamp, secondDayTimestamp);
        String urlResponse = urlHandler.readUrl();

        JSONParser jsonParser = new JSONParser(urlResponse);
        String[] titles = jsonParser.getValue("results", "title");
        String[] descriptions = jsonParser.getValue("results", "description");
        String[] site_url = jsonParser.getValue("results", "site_url");

        for (int i = 0; i < titles.length; i++) {
            String firstLetter = titles[i].substring(0, 1).toUpperCase();
            String restOfWord = titles[i].substring(1);
            titles[i] = firstLetter + restOfWord;
        }
        StringBuilder result = new StringBuilder();
        // Пока сделал деление на 2, чтоб не вылезало за лимит от телеграма 4096
        // символов
        for (int i = 0; i < titles.length / 2; i++) { // TODO сделать нормальное ограничение на количество символов
            result.append(titles[i]).append("\n");
            result.append(descriptions[i]).append("\n");
            result.append(site_url[i]).append("\n\n");
        }
        return result.toString();
    }
}
