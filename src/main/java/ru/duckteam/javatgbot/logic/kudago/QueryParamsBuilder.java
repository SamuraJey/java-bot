package ru.duckteam.javatgbot.logic.kudago;

public class QueryParamsBuilder {
    private String lang = "";
    private int pageSize = 0;
    private String fields = "";
    private String expand = "";
    private String orderBy = "";
    private String textFormat = "";
    private String ids = "";
    private String location = "";
    private int actualSince = 0;
    private int actualUntil = 0;
    private String isFree = "";
    private String categories = "";
    private double lon = 0;
    private double lat = 0;
    private int radius = 0;


    public String build() {

        // Добавьте параметры запроса в соответствии со спецификацией
        String queryParams = "?lang=" + lang +
                "&page_size=" + pageSize +
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

    public QueryParamsBuilder lang(String lang) {
        this.lang = lang;
        return this;
    }

    public QueryParamsBuilder pageSize(int pageSize) { //page size is int?
        this.pageSize = pageSize;
        return this;
    }

    public QueryParamsBuilder fields(String fields) {
        this.fields = fields;
        return this;
    }

    public QueryParamsBuilder orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public QueryParamsBuilder expand(String expand){
        // string (необязательный)
        // Включить в выдачу более подробную информацию для указанных полей, можно несколько через запяту
        // Варианты: images place location dates participants
        this.expand = expand;
        return this;
    }

    public QueryParamsBuilder textFormat(String textFormat) {
        this.textFormat = textFormat;
        return this;
    }

    public QueryParamsBuilder ids(String ids){
        this.ids = ids;
        return this;
    }

    public QueryParamsBuilder location(String location) {
        this.location = location;
        return this;
    }

    public QueryParamsBuilder actualSince(int actualSince) {
        this.actualSince = actualSince;
        return this;
    }

    public QueryParamsBuilder actualUntil(int actualUntil) {
        this.actualUntil = actualUntil;
        return this;
    }

    public QueryParamsBuilder isFree(boolean isFree) {
        this.isFree = Boolean.toString(isFree);
        return this;
    }

    public QueryParamsBuilder categories(String categories){
        this.categories = categories;
        return this;
    }


    public QueryParamsBuilder longitude(double lon) {
        this.lon = lon;
        return this;
    }

    public QueryParamsBuilder latitude(double lat) {
        this.lat = lat;
        return this;
    }

    public QueryParamsBuilder radius(int radius) { //raduis is int?
        this.radius = radius;
        return this;
    }
}
