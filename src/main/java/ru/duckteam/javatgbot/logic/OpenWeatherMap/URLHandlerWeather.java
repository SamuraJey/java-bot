package ru.duckteam.javatgbot.logic.OpenWeatherMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.Secret;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class URLHandlerWeather {
    private static final Logger LOGS = LoggerFactory.getLogger(URLHandlerWeather.class);
    private static URI uriAddress;

    public URLHandlerWeather(double longitude, double latitude) {
        // Если запустить программу отсюда, то она выдаст нам URL с запросом к апи опенвезер
        // API Doc: https://openweathermap.org/current
        String baseUrl = "https://api.openweathermap.org/";
        String endpoint = "data/2.5/weather/";
        // Пользовательские значения
        String lat = String.valueOf(longitude);
        String lon = String.valueOf(latitude);
        String appid = Secret.getWeatherApiKey();
        String units = "metric";
        String lang = "ru";

        QueryParamsBuilderWeather builder = new QueryParamsBuilderWeather();

        builder.lat(lat)
                .lon(lon)
                .appId(appid)
                .units(units)
                .lang(lang);

        try {
            uriAddress = new URI(baseUrl + endpoint).resolve(builder.build()).normalize();
            LOGS.info(String.valueOf(uriAddress));
        } catch (URISyntaxException e) {
            LOGS.error("Error while building URL: ", e);
        }

    }


    public String readUrl() throws IOException {
        try (InputStream inputStream = uriAddress.toURL().openStream()) {
            byte[] bytes = inputStream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }
}



































/*package ru.duckteam.javatgbot.logic.OpenWeatherMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.Secret;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class URLHandlerWeather {
    private static final Logger LOGS = LoggerFactory.getLogger(URLHandlerWeather.class);
    private static String urlString;
    public URLHandlerWeather() {

        String baseUrl = "https://api.openweathermap.org/";
        String endpoint = "data/2.5/weather/";
        // Пользовательские значения
        //long currentTimestamp = Instant.now().getEpochSecond();
        String lat = "56.8519";
        String lon = "60.6122";
        String appid = Secret.getWeatherApiKey();
        String units = "metric";
        String lang = "ru";
        QueryParamsBuilderWeather builder = new QueryParamsBuilderWeather(lat,lon,appid).units(units).lang(lang);

        try {
            URI uri = new URI(baseUrl + endpoint)
                    .resolve(builder.build()).normalize();
            urlString = uri.toString();
            LOGS.info(urlString);
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
}*/