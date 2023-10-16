package ru.duckteam.javatgbot.kudago;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {
    public String[] getValue(JSONObject jsonObject, String key) {

        JSONArray resultsJSONArray = jsonObject.getJSONArray("results");
        String[] retArr = new String[resultsJSONArray.length()];
        for (int i = 0; i < resultsJSONArray.length(); i++) {
            JSONObject event = resultsJSONArray.getJSONObject(i);
            String keyValue = event.getString(key);
            retArr[i] = keyValue;
        }
        return retArr;
    }
}