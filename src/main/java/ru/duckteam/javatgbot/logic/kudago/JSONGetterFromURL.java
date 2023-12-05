package ru.duckteam.javatgbot.logic.kudago;

import org.json.JSONObject;

public class JSONGetterFromURL {
    // KUDAGO API https://docs.kudago.com/api/
    public JSONObject getJSONObject(String apiResponse) {

        JSONObject jsonResponse = null;
        try {
            // Преобразуем строку ответа в объект JSON
            jsonResponse = new JSONObject(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}
