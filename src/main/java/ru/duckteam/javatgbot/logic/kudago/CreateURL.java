package ru.duckteam.javatgbot.logic.kudago;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;


public class CreateURL {
    private static final long UNIX_DAY = 86400;

    private static final Logger LOGS = LoggerFactory.getLogger(CreateURL.class);

    public static String getUrl() {
        // Если запустить программу отсюда, то она выдаст нам URL с запросом к апи кудаго
        // KUDAGO API https://docs.kudago.com/api/
        String baseUrl = "https://kudago.com";
        String version = "v1.4";
        String endpoint = String.format("/public-api/%s/events/", version);
        // Пользовательские значения
        long currentTimestamp = Instant.now().getEpochSecond();
        long prevDayTimestamp = currentTimestamp - 1000L;
        long nextWeekTimestamp = currentTimestamp + UNIX_DAY * 3;

        /*
            id - идентификатор
        publication_date - дата публикации
        dates - даты проведения
        title - название
        short_title - короткое название
        slug - слаг
        place - место проведения
        description - описание
        body_text - полное описание
        location - город проведения
        categories - список категорий
        tagline - тэглайн
        age_restriction - возрастное ограничение
        price - стоимость
        is_free - бесплатное ли событие
        images - картинки
        favorites_count - сколько пользователей добавило событие в избранное
        comments_count - число комментариев к событию
        site_url - адрес события на сайте kudago.com
        tags - тэги события
        participants - агенты события
         */
        String lang = "ru";
        String page_size = "";
        String fields = "id,publication_date,dates,title,short_title,slug,place,description," +
                "location,categories,price,is_free," +
                "site_url";
        String expand = "";
        String orderBy = "date";
        String textFormat = "plain";
        String ids = "";
        String location = "ekb";
        String actualSince = String.valueOf(prevDayTimestamp);
        String actualUntil = String.valueOf(nextWeekTimestamp);
        String isFree = "";
        String categories = "";
        String lon = "";
        String lat = "";
        String radius = "";
        String url = "";
        Builder builder = new Builder(lang,fields,orderBy,textFormat,location,actualSince,actualUntil);

        try {
            URI uri = new URI(baseUrl + endpoint)
                    .resolve(builder.buildQueryParams()).normalize();

            url = uri.toString();
            System.out.println("URL: " + uri);
        } catch (URISyntaxException e) {
            LOGS.error("Error while building URL: ", e);
//            e.printStackTrace();
        }

        return url;
    }
}