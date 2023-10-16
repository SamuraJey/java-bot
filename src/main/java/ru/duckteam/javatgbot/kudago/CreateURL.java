package ru.duckteam.javatgbot.kudago;


import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;


public class CreateURL {
    private static final long UNIX_DAY = 86400;

    public static String getUrl() {
        // Если запустить программу отсюда, то она выдаст нам URL с запросом к апи кудаго
        // KUDAGO API https://docs.kudago.com/api/
        String baseUrl = "https://kudago.com";
        String version = "v1.4";
        String endpoint = String.format("/public-api/%s/events/", version);
        // Пользовательские значения
        long currentTimestamp = Instant.now().getEpochSecond();
        long prevDayTimestamp = currentTimestamp - UNIX_DAY;
        long nextWeekTimestamp = currentTimestamp + UNIX_DAY * 6;

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

        try {
            URI uri = new URI(baseUrl + endpoint)
                    .resolve(buildQueryParams(lang, page_size, fields, expand, orderBy, textFormat,
                            ids, location, actualSince, actualUntil,
                            isFree, categories, lon, lat, radius)).normalize();

            url = uri.toString();
            System.out.println("URL: " + uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return url;
    }

    private static String buildQueryParams(String lang, String page_size, String fields, String expand, String orderBy,
                                           String textFormat, String ids, String location, String actualSince,
                                           String actualUntil, String isFree, String categories, String lon, String lat,
                                           String radius) {

        // Добавьте параметры запроса в соответствии со спецификацией
        String queryParams = "?lang=" + lang +
                "&page_size=" + page_size +
                "&fields=" + fields +
                "&expand=" + expand +
                "&order_by=" + orderBy +
                "&text_format=" + textFormat +
                "&ids=" + ids +
                "&location=" + location +
                "&actual_since=" + actualSince +
                "&actual_until=" + actualUntil +
                "&is_free=" + isFree +
                "&categories=" + categories +
                "&lon=" + lon +
                "&lat=" + lat +
                "&radius=" + radius;

        return queryParams;
    }
}