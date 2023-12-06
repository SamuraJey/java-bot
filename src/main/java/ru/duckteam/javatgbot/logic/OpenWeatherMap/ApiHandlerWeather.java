package ru.duckteam.javatgbot.logic.OpenWeatherMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class ApiHandlerWeather {
    static JSONParserWeather jsonParser;
    private static final Logger LOGS = LoggerFactory.getLogger(ApiHandlerWeather.class);

    public String getResponse() throws URISyntaxException, IOException {

        URLHandlerWeather urlHandlerWeather = new URLHandlerWeather();
        String urlResponse = urlHandlerWeather.readUrl();
        JSONParserWeather jsonParser= new JSONParserWeather(urlResponse);
        String currentTemp = jsonParser.getValue("main", "temp");

        LOGS.info(currentTemp);
        return currentTemp;
    }
}
