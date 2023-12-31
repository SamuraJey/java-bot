package ru.duckteam.javatgbot.logic.OpenWeatherMap;

public class QueryParamsBuilderWeather {
    private String lat = "";
    private String lon = "";
    private String appid = "";
    private String mode = "";
    private String units = "";
    private String lang = "ru";

    public QueryParamsBuilderWeather() {
    }

    public String build() {
        // Добавьте параметры запроса в соответствии со спецификацией
        return "?lat=" + lat + "&lon=" + lon + "&mode=" + mode + "&appid=" + appid + "&units=" + units + "&lang=" + lang;
    }

    public QueryParamsBuilderWeather lat(String lat) {
        this.lat = lat;
        return this;
    }

    public QueryParamsBuilderWeather lon(String lon) {
        this.lon = lon;
        return this;
    }

    public QueryParamsBuilderWeather mode(String mode) {
        this.mode = mode;
        return this;
    }

    public QueryParamsBuilderWeather appId(String appid) {
        this.appid = appid;
        return this;
    }


    public QueryParamsBuilderWeather units(String units) {
        this.units = units;
        return this;
    }

    public QueryParamsBuilderWeather lang(String lang) {
        this.lang = lang;
        return this;
    }

}
