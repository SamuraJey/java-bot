package ru.duckteam.javatgbot.logic.kudago;

public class Builder {
    private final String lang;
    private final static String page_size = "";
    private final String fields;
    private final static String expand = "";
    private final String orderBy;
    private final String textFormat;
    private final static String ids = "";
    private final String location;
    private final String actualSince;
    private final String actualUntil;
    private final static String isFree = "";
    private final static String categories = "";
    private final static String lon = "";
    private final static String lat = "";
    private final static String radius = "";


    public Builder(String lang,String fields,String orderBy,String textFormat,String location,String actualSince,String actualUntil)
    {
        this.lang = lang;
        this.fields = fields;
        this.orderBy = orderBy;
        this.textFormat = textFormat;
        this.location = location;
        this.actualSince = actualSince;
        this.actualUntil = actualUntil;
    }



    public String buildQueryParams() {

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
