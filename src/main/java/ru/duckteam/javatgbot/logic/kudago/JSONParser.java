package ru.duckteam.javatgbot.logic.kudago;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {

    private final JSONObject jsonResponse;

    public JSONParser(String apiResponse) {
        JSONObject jsonResponse = null;
        try {
            // Преобразуем строку ответа в объект JSON
            jsonResponse = new JSONObject(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.jsonResponse = jsonResponse;
    }

    public String[] getValue(String parentKey, String key) {
        if (jsonResponse == null) {
            return null;
        }
        JSONArray resultsJSONArray = jsonResponse.getJSONArray(parentKey);
        String[] retArr = new String[resultsJSONArray.length()];
        for (int i = 0; i < resultsJSONArray.length(); i++) {
            JSONObject event = resultsJSONArray.getJSONObject(i);
            retArr[i] = event.getString(key);
        }
        return retArr;
    }
}