package ru.duckteam.javatgbot.logic.command.answers;

import ru.duckteam.javatgbot.logic.UserStatus;
import ru.duckteam.javatgbot.logic.command.ExpectedAnswers;

import java.util.Map;
import java.util.Set;

public class WeatherSimpleAnswers implements ExpectedAnswers {

    public static final String questions = "Ты выбрал режим погоды, выбери город в котром будем сомтреть погоду, Екатеринбург или Москва?";
    private static final String errorAnswer = "Введи еще раз, я не понял";
    private static final Set<String> expectedAnswers = Set.of("Екатеринбург", "Москва");
    ;
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
        return (startIndex <= currentQuestion && currentQuestion < lastIndex) || invalidMessage;
    }

    @Override
    public boolean needSetParam(int currentQuestion) {
        this.currentQuestion = currentQuestion;
        return startIndex + 1 <= currentQuestion && currentQuestion <= lastIndex + 1;
    }

    @Override
    public String getQuestions(UserStatus userStatus) {
        if (invalidMessage) {
            invalidMessage = false;
            return errorAnswer;
        }
        userStatus.plusOneWeatherCountQuestions();
        return questions;
    }

    @Override
    public void setParam(String param, UserStatus userStatus) {
        double[] answer = paramForApi.get(param);
        // TODO Сделать обработку неккоректного ввода
        // Если мы у мапы запрашиваем несущствующий ключ, она вернет null, наверное как-то через это можно сделать??
        if (answer == null) {
            invalidMessage = true;
            this.getQuestions(userStatus);
        }
        double latitude = answer[0];
        double longitude = answer[1];
        userStatus.setUserLatitude(latitude);
        userStatus.setUserLongitude(longitude);
    }
}
