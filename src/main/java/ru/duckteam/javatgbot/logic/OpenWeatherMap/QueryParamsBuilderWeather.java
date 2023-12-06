package ru.duckteam.javatgbot.logic.OpenWeatherMap;

public class QueryParamsBuilderWeather {
    private String lat = "";
    private String lon = "";
    private String appid = "";
    //private String dt = "";
    private String mode = "";
    private String units = "";
    private String lang = "ru";

    public QueryParamsBuilderWeather(String lat, String lon, String appid){
        this.lat = lat;
        this.lon = lon;
        this.appid = appid;
    }
    public String build() {

        // Добавьте параметры запроса в соответствии со спецификацией
        String queryParams = "?lat=" + lat +
                "&lon=" + lon +
                "&mode=" + mode +
                "&appid=" + appid +
                "&units=" + units +
                "&lang=" + lang;

        return queryParams;
    }

    public QueryParamsBuilderWeather mode(String exclude) {
        this.mode = mode;
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
