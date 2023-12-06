package ru.duckteam.javatgbot.logic.OpenWeatherMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class URLHandlerWeather {
    private static final Logger LOGS = LoggerFactory.getLogger(URLHandlerWeather.class);
    private static String urlString;
    public URLHandlerWeather() {
        // Если запустить программу отсюда, то она выдаст нам URL с запросом к апи
        // кудаго
        String baseUrl = "https://api.openweathermap.org/";
        String endpoint = "/data/2.5/weather/";
        // Пользовательские значения
        //long currentTimestamp = Instant.now().getEpochSecond();
        String lat = "56.8519";
        String lon = "60.6122";
        String appid = "391afcebb5cb7ca5acca77dee9ad5b28";
        //String dt = "1643803200";
        String exclude = "";
        String units = "";
        String lang = "ru";
        String url = "";
        QueryParamsBuilderWeather builder = new QueryParamsBuilderWeather(lat,lon,appid);

        try {
            URI uri = new URI(baseUrl + endpoint)
                    .resolve(builder.build()).normalize();
            urlString = uri.toString();
            LOGS.info(url);
        } catch (URISyntaxException e) {
            LOGS.error("Error while building URL: ", e);
        }

    }

    public String readUrl() throws URISyntaxException, IOException {
        BufferedReader reader = null;
        try {
            URI url = new URI(urlString);
            reader = new BufferedReader(new InputStreamReader(url.toURL().openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}