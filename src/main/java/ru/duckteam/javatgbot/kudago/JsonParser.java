package ru.duckteam.javatgbot.kudago;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
    // KUDAGO API https://docs.kudago.com/api/
    private String string = "";
        public String methodName(String apiResponse)
        {
            try {
                // Преобразуем строку ответа в объект JSON
                JSONObject jsonResponse = new JSONObject(apiResponse);

                // Извлекаем массив результатов событий
                JSONArray results = jsonResponse.getJSONArray("results");
                // Итерируемся по массиву результатов и выводим названия событий
                for (int i = 0; i < results.length(); i++) {
                    JSONObject event = results.getJSONObject(i);
                    String title = event.getString("title");
                    string = string + (i + 1) +'.'+" "+ title + "\n";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return string;
        }
}

