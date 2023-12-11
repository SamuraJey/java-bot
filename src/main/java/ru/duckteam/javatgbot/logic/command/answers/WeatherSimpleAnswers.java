package ru.duckteam.javatgbot.logic.command.answers;

import ru.duckteam.javatgbot.logic.UserStatus;
import ru.duckteam.javatgbot.logic.command.ExpectedAnswers;

import java.util.Map;

public class WeatherSimpleAnswers implements ExpectedAnswers {

    public static final String question = "Ты выбрал режим погоды, выбери город в котором будем смотреть погоду, Екатеринбург или Москва?";
    private static final String errorAnswer = "Введи еще раз, я не понял";
    private static final String[] expectedAnswers = {"Екатеринбург", "Москва"};

    private static final Map<String, double[]> paramForApi = Map.of(
            "Екатеринбург", new double[]{56.838011, 60.597474},
            "Москва", new double[]{55.755864, 37.617698}
    );
    int startIndex = 0;
    int lastIndex = 1;
    private int currentQuestion;
    private boolean invalidMessage = false;

    @Override
    public boolean hasOtherQuestions() {
        return currentQuestion == startIndex || invalidMessage;
    }

    @Override
    public boolean needSetParam(int currentQuestion) {
        this.currentQuestion = currentQuestion;
        return currentQuestion == lastIndex;
    }

    @Override
    public String getQuestions(UserStatus userStatus) {
        if (invalidMessage) {
            invalidMessage = false;
            return errorAnswer;
        }
        userStatus.plusOneWeatherCountQuestions();
        return question;
    }

    @Override
    public void setParam(String param, UserStatus userStatus) {

        String firstExpectedAnswer = expectedAnswers[0];
        String secondExpectedAnswer = expectedAnswers[1];

        if (firstExpectedAnswer.trim().equalsIgnoreCase(param)) {
            userStatus.setCityCoordinates(paramForApi.get(firstExpectedAnswer));
        } else {
            if (secondExpectedAnswer.trim().equalsIgnoreCase(param)) {
                userStatus.setCityCoordinates(paramForApi.get(secondExpectedAnswer));
            } else {
                invalidMessage = true;
            }
        }
    }
}
