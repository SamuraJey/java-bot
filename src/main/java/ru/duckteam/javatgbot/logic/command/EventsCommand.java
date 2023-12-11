package ru.duckteam.javatgbot.logic.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.*;
import ru.duckteam.javatgbot.logic.command.answers.DateAnswers;
import ru.duckteam.javatgbot.logic.command.answers.SimpleAnswers;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

import java.util.List;

public class EventsCommand implements BotCommand {
    private static final String eventsString = "/events";
    private static final ApiHandler apiHandler = new ApiHandler();
    private static final Logger LOGS = LoggerFactory.getLogger(MessageHandler.class);
    private final UserStatusService userStatusService;
    private final List<ExpectedAnswers> expectedAnswers = List.of(new SimpleAnswers(), new DateAnswers());

    public EventsCommand(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @Override
    public boolean needExecute(String message, UserStatus userStatus, Long chatId) {

        if (userStatus == null) {
            return false;
        }

        if (!userStatus.getUserCommand().equals(eventsString) || userStatus.IsCommand(message)) {
            if (eventsString.equals(message)) {
                userStatusService.clearUserStatus(chatId);
                userStatusService.setUserStatus(chatId, eventsString);
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

            if (expectedAnswer.needSetParam(userStatus.getCountQuestions())) {
                expectedAnswer.setParam(message, userStatus);
            }

            if (expectedAnswer.hasOtherQuestions()) {
                return expectedAnswer.getQuestions(userStatus);
            }
        }

        String location = userStatus.getLocation();
        boolean isFree = userStatus.getIsFree();
        long firstDayTimestamp = userStatus.getFirstDayTimestamp();
        long secondDayTimestamp = userStatus.getSecondDayTimestamp();
        userStatusService.clearUserStatus(chatId);
        //userStatusService.setUserStatus(chatId,"/start");

        try {
            LOGS.info("loc [%s] free [%s] firstday [%s] secondday [%s]".formatted(location, isFree, firstDayTimestamp, secondDayTimestamp));
            return apiHandler.getResponse(location, isFree, firstDayTimestamp, secondDayTimestamp);
        } catch (Exception e) {
            LOGS.error("Error during handle [%s] by user [%s]".formatted(message, chatId), e);
            return "Что то пошло не так";
        }
    }
}
