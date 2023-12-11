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
        if (parentKey.equalsIgnoreCase("weather")) {
            return jsonResponse.getJSONArray(parentKey)
                    .getJSONObject(0)
                    .getString(key);
        } else if (key.isEmpty()) {
            return jsonResponse.getString(key);
        } else {
            return jsonResponse.getJSONObject(parentKey)
                    .getBigDecimal(key)
                    .toString();
        }
    }
}