package ru.duckteam.javatgbot.logic.OpenWeatherMap;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParserWeather {
    private final JSONObject jsonResponse;

    public JSONParserWeather(String apiResponse) {
        JSONObject jsonResponse = null;
        try {
            // Преобразуем строку ответа в объект JSON
            jsonResponse = new JSONObject(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.jsonResponse = jsonResponse;
    }
    public String getValue(String parentKey, String key) {

        String retArr = new String();
        JSONObject object = jsonResponse.getJSONObject(parentKey);
        retArr = object.getBigDecimal(key).toString();
        return retArr;
    }
}