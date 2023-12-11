package ru.duckteam.javatgbot.logic.OpenWeatherMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class ApiHandlerWeather {
    private static final Logger LOGS = LoggerFactory.getLogger(ApiHandlerWeather.class);

    public String[] getResponse(double longitude, double latitude) throws URISyntaxException, IOException {

        URLHandlerWeather urlHandlerWeather;
        urlHandlerWeather = new URLHandlerWeather(longitude, latitude);
        String urlResponse = urlHandlerWeather.readUrl();
        JSONParserWeather jsonParser = new JSONParserWeather(urlResponse);
        // JSON weather block
        //String weatherId = jsonParser.getValue("weather", "id");
        //String weatherMain = jsonParser.getValue("weather", "main");
        String weatherDescription = jsonParser.getValue("weather", "description");

        // JSON wind block
        //String windSpeed = jsonParser.getValue("wind", "speed");
        //String windDeg = jsonParser.getValue("wind", "deg"); // Wind direction, IDK why in api it`s "deg"

        // JSON clouds block
        //String cloudsAll = jsonParser.getValue("clouds", "all");

        // JSON main block
        String mainTemperatureCurrent = jsonParser.getValue("main", "temp");
        String mainTemperatureFeelsLike = jsonParser.getValue("main", "feels_like");
        //String mainPressure = jsonParser.getValue("main", "pressure");
        //String mainHumidity = jsonParser.getValue("main", "humidity");

        // JSON entities without blocks
        //String visibility = jsonParser.getValue("visibility", "");

        // TODO Понять и решить, что возвращать.
        LOGS.info(weatherDescription, mainTemperatureCurrent, mainTemperatureFeelsLike);
        // TODO Нормальный ретерн чего нибулдь
        return new String[]{weatherDescription, mainTemperatureCurrent, mainTemperatureFeelsLike};
    }
}



























/*package ru.duckteam.javatgbot.logic.OpenWeatherMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class ApiHandlerWeather {
    //    private final JSONParserWeather jsonParser;
    private static final Logger LOGS = LoggerFactory.getLogger(ApiHandlerWeather.class);

    public String getResponse() throws URISyntaxException, IOException {

        URLHandlerWeather urlHandlerWeather = new URLHandlerWeather();
        String urlResponse = urlHandlerWeather.readUrl();
        LOGS.info(urlResponse);
        JSONParserWeather jsonParser = new JSONParserWeather(urlResponse);
        // JSON weather block
        //String weatherId = jsonParser.getValue("weather", "id");
        //String weatherMain = jsonParser.getValue("weather", "main");
        //String weatherDescription = jsonParser.getValue("weather", "description");

        // JSON wind block
        //String windSpeed = jsonParser.getValue("wind", "speed");
        //String windDeg = jsonParser.getValue("wind", "deg"); // Wind direction, IDK why in api it`s "deg"

        // JSON clouds block
        //String cloudsAll = jsonParser.getValue("clouds", "all");

        // JSON main block
        String mainTemperatureCurrent = jsonParser.getValue("main", "temp");
        //String mainTemperatureFeelsLike = jsonParser.getValue("main", "feels_like");
        //String mainPressure = jsonParser.getValue("main", "pressure");
        //String mainHumidity = jsonParser.getValue("main", "humidity");

        // JSON entities without blocks
        //String visibility = jsonParser.getValue("visibility", "");

        // TODO Понять и решить, что возвращать.
        LOGS.info(mainTemperatureCurrent);
        // TODO Нормальный ретерн чего нибулдь
        return mainTemperatureCurrent;
    }

}*/
