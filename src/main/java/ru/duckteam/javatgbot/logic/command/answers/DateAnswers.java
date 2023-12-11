package ru.duckteam.javatgbot.logic.command.answers;

import ru.duckteam.javatgbot.logic.UserStatus;
import ru.duckteam.javatgbot.logic.command.ExpectedAnswers;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateAnswers implements ExpectedAnswers {

    private static final String[] questions =
            {"Напиши день, с которого начать искать события, в формате дд.мм.гггг",
                    "Теперь до какого дня искать, в том же формате"};
    private static final String errorAnswer = "Введи еще раз, я не понял";
    private static final int startIndex = 2;
    private static final int lastIndex = 3;
    private static final Pattern pattern =
            Pattern.compile("(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)");

    private boolean invalidMessage = false;
    private int currentQuestion;

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
        } else {
            invalidMessage = true;
        }
    }

    public boolean isValid(String date) {
        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {
            matcher.reset();

            if (matcher.find()) {
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if ("31".equals(day)) {
                    return Arrays.asList(new String[]{"1", "01", "3", "03", "5", "05", "7", "07", "8", "08", "10", "12"}).contains(month);

                } else if ("2".equals(month) || "02".equals(month)) {
                    if (year % 4 == 0) {
                        if (year % 100 == 0) {
                            if (year % 400 == 0) {
                                return Integer.parseInt(day) <= 29;
                            }
                            return Integer.parseInt(day) <= 28;
                        }
                        return Integer.parseInt(day) <= 29;
                    } else {
                        return Integer.parseInt(day) <= 28;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}