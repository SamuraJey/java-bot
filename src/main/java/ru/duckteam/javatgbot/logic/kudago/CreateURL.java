package ru.duckteam.javatgbot.logic.kudago;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;

public class CreateURL {
    private static final long UNIX_DAY = 86400;

    private static final Logger LOGS = LoggerFactory.getLogger(CreateURL.class);

    public static String getUrl(String location, boolean isFree, long firstDayTimestamp, long secondDayTimestamp) {
        // Если запустить программу отсюда, то она выдаст нам URL с запросом к апи
        // кудаго
        // KUDAGO API https://docs.kudago.com/api/
        String baseUrl = "https://kudago.com";
        String version = "v1.4";
        String endpoint = String.format("/public-api/%s/events/", version);
        // Пользовательские значения
        long currentTimestamp = Instant.now().getEpochSecond();
        long prevDayTimestamp = currentTimestamp - 1000L;
        long nextDayTimestamp = currentTimestamp + UNIX_DAY;
        long nextWeekTimestamp = currentTimestamp + UNIX_DAY;

        String lang = "ru";
        String fields = "id,publication_date,dates,title,short_title,slug,place,description," +
                "location,categories,price,is_free," +
                "site_url";
        String orderBy = "date";
        String textFormat = "plain";
        // String location = "ekb";
        String actualSince = String.valueOf(prevDayTimestamp);
        String actualUntil = String.valueOf(nextDayTimestamp);
        String url = "";
        QueryParamsBuilder builder = new QueryParamsBuilder()
                .lang(lang)
                .fields(fields)
                .orderBy(orderBy)
                .textFormat(textFormat)
                .location(location)
                .isFree(isFree)
                .actualSince(Integer.parseInt(actualSince))
                .actualUntil(Integer.parseInt(actualUntil));
        // TODO разобраться почему так парсится из лонг в стриг и в инт
        try {
            URI uri = new URI(baseUrl + endpoint)
                    .resolve(builder.build()).normalize();

            url = uri.toString();
        } catch (URISyntaxException e) {
            LOGS.error("Error while building URL: ", e);
        }

        return url;
    }
}