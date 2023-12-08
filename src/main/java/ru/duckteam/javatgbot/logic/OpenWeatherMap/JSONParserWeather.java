package ru.duckteam.javatgbot.logic.OpenWeatherMap;

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
        if (jsonResponse == null) {
            return null;
        }
        String retValue;
        if (parentKey.equalsIgnoreCase("weather")) {
            retValue = jsonResponse.
                    getJSONArray(parentKey).
                    getJSONObject(0).
                    getString(key);
        } else if (key.equalsIgnoreCase("")) {
            retValue = jsonResponse.getString(key);
        } else {
            retValue = jsonResponse.
                    getJSONObject(parentKey).
                    getString(key);
        }
        return retValue;
    }
}