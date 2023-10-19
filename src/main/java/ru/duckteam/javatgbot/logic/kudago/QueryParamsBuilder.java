package ru.duckteam.javatgbot.logic.kudago;

public class QueryParamsBuilder {
    private final String lang;
    private String page_size = "";
    private final String fields;
    private String expand = "";
    private final String orderBy;
    private final String textFormat;
    private String ids = "";
    private final String location;
    private final String actualSince;
    private final String actualUntil;
    private String isFree = "";
    private String categories = "";
    private String lon = "";
    private String lat = "";
    private String radius = "";


    public QueryParamsBuilder(String lang, String fields, String orderBy, String textFormat, String location, String actualSince, String actualUntil)
    {
        this.lang = lang;
        this.fields = fields;
        this.orderBy = orderBy;
        this.textFormat = textFormat;
        this.location = location;
        this.actualSince = actualSince;
        this.actualUntil = actualUntil;
    }



    public String build() {

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

    public QueryParamsBuilder isFree(boolean isFree){
        this.isFree = Boolean.toString(isFree);
        return this;
    }

    public QueryParamsBuilder page_size(String page_size){ //page size is int?
        this.page_size = page_size;
        return this;
    }

    public QueryParamsBuilder expand(String expand){
        this.expand = expand;
        return this;
    }

    public QueryParamsBuilder ids(String ids){
        this.ids = ids;
        return this;
    }

    public QueryParamsBuilder categories(String categories){
        this.categories = categories;
        return this;
    }

    public QueryParamsBuilder lon(String lon){
        this.lon = lon;
        return this;
    }

    public QueryParamsBuilder lat(String lat){
        this.lat = lat;
        return this;
    }

    public QueryParamsBuilder radius(String radius){ //raduis is int?
        this.radius = radius;
        return this;
    }
}
