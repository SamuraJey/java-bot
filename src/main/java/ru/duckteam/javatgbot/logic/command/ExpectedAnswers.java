package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.logic.UserStatus;

public interface ExpectedAnswers {
    boolean hasOtherQuestions();

    boolean needSetParam(int currentQuestion);

    String getQuestions(UserStatus userStatus);

    void setParam(String param, UserStatus userStatus);
}
