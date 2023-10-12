package ru.duckteam.javatgbot;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONPARSER {
    // KUDAGO API https://docs.kudago.com/api/
        public void methodName(String apiResponse)
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
                    System.out.println(title);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}

