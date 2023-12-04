package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.logic.UserStatus;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAnswers implements ExpectedAnswers {

    int startIndex = 2;
    int lastIndex = 3;
    private static final String[] questions =
            {"Напиши день, с которого начать искать события, в формате дд.мм.гггг",
                    "Теперь до какого дня искать, в том же формате"};
    private boolean invalidMessage = false;
    private static final String errorAnswer = "Введи еще раз, я не понял";
    private int currentQuestion;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public boolean hasOtherQuestions() {
        return (startIndex <= currentQuestion && currentQuestion <= lastIndex) || invalidMessage;
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

        userStatus.plusOneCountQuestions();
        return questions[currentQuestion - 2];
    }

    @Override
    public void setParam(String param, UserStatus userStatus) {
        if (isValid(param)) {
            userStatus.addParam(param);
        }
        else {
            invalidMessage = true;
        }
    }
    public boolean isValid(String dateStr) {
        try {
            dateFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
