package ru.duckteam.javatgbot.logic.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.*;
import ru.duckteam.javatgbot.logic.OpenWeatherMap.ApiHandlerWeather;

public class WeatherCommand implements BotCommand {
    private static final String weatherString = "/weather";
    private static final Logger LOGS = LoggerFactory.getLogger(WeatherCommand.class);
    private final UserStatusService userStatusService;
    private final ApiHandlerWeather apiHandlerWeather = new ApiHandlerWeather();



    public WeatherCommand(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }
    @Override
    public boolean needExecute(String message, UserStatus userStatus, Long chatId) {

        if (userStatus == null) {
            return false;
        }

        if (!userStatus.getUserCommand().equals(weatherString) || userStatus.IsCommand(message)) {
            if (weatherString.equals(message)) {
                userStatusService.clearUserStatus(chatId);
                userStatusService.setUserStatus(chatId, weatherString);
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public void execute(String message, Long chatId, AnswerWriter writer, UserStatus userStatus) {
        BotResponse response = new BotResponse(
                chatId,
                getApiAnswer(message, chatId));
        writer.writeAnswer(response);
    }

    private String getApiAnswer(String message, Long chatId) {
        userStatusService.clearUserStatus(chatId);
        try {
            return "Темпиратура воздуха сейчас " + apiHandlerWeather.getResponse();
        } catch (Exception e) {
            LOGS.error("Error during handle [%s] by user [%s]".formatted(message, chatId), e);
            return "Что то пошло не так";
        }
    }
}
