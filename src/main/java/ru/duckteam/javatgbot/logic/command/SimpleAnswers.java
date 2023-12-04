package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.logic.UserStatus;

import java.util.Arrays;
import java.util.Map;

public class SimpleAnswers implements  ExpectedAnswers {
    int startIndex = 0;
    int lastIndex = 1;
    private static final String[] questions =
            {"Ты выбрал режим событий, выбери город в котром будем искать их, Екатеринбург или Москва?",
            "Хорошо, теперь давай определимся, нужно ли показать платные места, ответь да или нет"};
    private boolean invalidMessage = false;
    private static final String errorAnswer = "Введи еще раз, я не понял";
    private static final String[] expectedAnswers = {"Екатеринбург", "Москва", "Да", "Нет"};
    private static final Map<String, String> paramForApi = Map.of("Екатеринбург", "ekb",
            "Москва", "msk",
            "Нет", "true",
            "Да", "false");
    private int currentQuestion;

    @Override
    public boolean needSetParam(int currentQuestion) {
        this.currentQuestion = currentQuestion;
        return startIndex + 1 <= currentQuestion && currentQuestion <= lastIndex + 1;
    }
    @Override
    public boolean hasOtherQuestions() {
        return (startIndex <= currentQuestion && currentQuestion <= lastIndex) || invalidMessage;
    }

    @Override
    public String getQuestions(UserStatus userStatus) {

        if (invalidMessage){
            invalidMessage = false;
            return errorAnswer;
        }
        userStatus.plusOneCountQuestions();
        return questions[currentQuestion];
    }

    @Override
    public void setParam(String param, UserStatus userStatus) {

        int indFirstExpectedAnswer = (userStatus.getCountQuestions()- 1) * 2;
        String firstExpectedAnswer = expectedAnswers[indFirstExpectedAnswer];
        String secondExpectedAnswer = expectedAnswers[indFirstExpectedAnswer + 1];

        if (firstExpectedAnswer.trim().equalsIgnoreCase(param)) {
            userStatus.addParam(paramForApi.get(firstExpectedAnswer));
        } else {
            if (secondExpectedAnswer.trim().equalsIgnoreCase(param)) {
                userStatus.addParam(paramForApi.get(secondExpectedAnswer));
            } else {
                invalidMessage = true;
            }
        }
    }
}
