package ru.duckteam.javatgbot.logic.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.BotCommand;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.OpenWeatherMap.ApiHandlerWeather;
import ru.duckteam.javatgbot.logic.UserStatus;
import ru.duckteam.javatgbot.logic.UserStatusService;
import ru.duckteam.javatgbot.logic.command.answers.WeatherSimpleAnswers;

import java.util.List;

public class WeatherCommand implements BotCommand {
    private static final String weatherString = "/weather";
    private static final Logger LOGS = LoggerFactory.getLogger(WeatherCommand.class);
    private static final ApiHandlerWeather apiHandlerWeather = new ApiHandlerWeather();
    private final UserStatusService userStatusService;
    private final List<ExpectedAnswers> expectedAnswers = List.of(new WeatherSimpleAnswers());


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
                getApiAnswer(userStatus, message, chatId));
        writer.writeAnswer(response);
    }

    private String getApiAnswer(UserStatus userStatus, String message, Long chatId) {

        for (ExpectedAnswers expectedAnswer : expectedAnswers) {
            if (expectedAnswer.needSetParam(userStatus.getWeatherCountQuestions())) {
                expectedAnswer.setParam(message, userStatus);
            }
            if (expectedAnswer.hasOtherQuestions()) {
                return expectedAnswer.getQuestions(userStatus);
            }
        }
        double latitude = userStatus.getUserLatitude();
        double longitude = userStatus.getUserLongitude();
        userStatusService.clearUserStatus(chatId);
        try {
            String[] args = apiHandlerWeather.getResponse(latitude, longitude);
            return "Сейчас " + args[0] + "\n" + "Температура воздуха " + args[1] + "\n" + "Ощущается как " + args[2];
        } catch (Exception e) {
            LOGS.error("Error during handle [%s] by user [%s]".formatted(message, chatId), e);
            return "Что то пошло не так";
        }
    }
}
