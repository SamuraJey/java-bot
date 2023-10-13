package ru.duckteam.javatgbot.kudago;


import java.net.URI;
import java.net.URISyntaxException;

public class CreateURL {
    public static String getUrl() {
        // Если запустить программу отсюда, то она выдаст нам URL с запросом к апи кудаго
        // KUDAGO API https://docs.kudago.com/api/
        String baseUrl = "https://kudago.com";
        String version = "v1.4";
        String endpoint = String.format("/public-api/%s/events/", version);
        // Пользовательские значения
        String lang = "ru";
        String fields = "title";
        String expand = "location";
        String orderBy = "date";
        String textFormat = "plain";
        String ids = "";
        String location = "ekb";
        String actualSince = "1696533466";
        String actualUntil = "1697138267";
        String isFree = "";
        String categories = "";
        String lon = "";
        String lat = "";
        String radius = "";
        String url = "";

        try {
            URI uri = new URI(baseUrl + endpoint)
                    .resolve(buildQueryParams(lang, fields, expand, orderBy, textFormat, ids, location, actualSince, actualUntil, isFree, categories, lon, lat, radius))
                    .normalize();

            url = uri.toString();
            System.out.println("URL: " + uri.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return url;
    }

    private static String buildQueryParams(String lang, String fields, String expand, String orderBy, String textFormat, String ids, String location, String actualSince, String actualUntil, String isFree, String categories, String lon, String lat, String radius) {
        StringBuilder queryParams = new StringBuilder();

        // Добавьте параметры запроса в соответствии со спецификацией
        queryParams.append("?lang=").append(lang)
                .append("&fields=").append(fields)
                .append("&expand=").append(expand)
                .append("&order_by=").append(orderBy)
                .append("&text_format=").append(textFormat)
                .append("&ids=").append(ids)
                .append("&location=").append(location)
                .append("&actual_since=").append(actualSince)
                .append("&actual_until=").append(actualUntil)
                .append("&is_free=").append(isFree)
                .append("&categories=").append(categories)
                .append("&lon=").append(lon)
                .append("&lat=").append(lat)
                .append("&radius=").append(radius);

        return queryParams.toString();
    }
}