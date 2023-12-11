package ru.duckteam.javatgbot.logic.kudago;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class URLHandler {
    private static final Logger LOGS = LoggerFactory.getLogger(URLHandler.class);
    private URI uriAddress;

    public URLHandler(String location, boolean isFree, long firstDayTimestamp, long secondDayTimestamp) {
        // Если запустить программу отсюда, то она выдаст нам URL с запросом к апи
        // кудаго
        String baseUrl = "https://kudago.com";
        String version = "v1.4";
        String endpoint = String.format("/public-api/%s/events/", version);
        // Пользовательские значения
        //long currentTimestamp = Instant.now().getEpochSecond();
        String lang = "ru";
        String fields = "id,publication_date,dates,title,short_title,slug,place,description," +
                "location,categories,price,is_free," +
                "site_url";
        String orderBy = "date";
        String textFormat = "plain";
//        String location = "ekb";
//        long actualSince = firstDayTimestamp;
//        long actualUntil = secondDayTimestamp;
        QueryParamsBuilder builder = new QueryParamsBuilder()
                .lang(lang)
                .fields(fields)
                .orderBy(orderBy)
                .textFormat(textFormat)
                .location(location)
                .isFree(isFree)
                .actualSince((int) firstDayTimestamp)
                .actualUntil((int) secondDayTimestamp);
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