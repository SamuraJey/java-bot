package ru.duckteam.javatgbot.logic.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.*;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

import java.util.List;
import java.util.Map;

public class EventsCommand implements BotCommand {
    private static final String eventsString = "/events";
    private static final List<String> questions = List.of(
            "Ты выбрал режим событий, выбери город в котром будем искать их, Екатеринбург или Москва?",
            "Хорошо, теперь давай определимся, нужно ли показать платные места, ответь да или нет");
    private static final int lengthQuestions = questions.size();
    private static final List<String> expectedAnswers = List.of("Екатеринбург", "Москва", "Да", "Нет");
    private static final String errorAnswer = "Введи еще раз, я не понял";
    private static final Map<String, String> paramForApi = Map.of("Екатеринбург", "ekb",
            "Москва", "msk",
            "Нет", "true",
            "Да", "false");
    private static final ApiHandler apiHandler = new ApiHandler();
    private static final Logger LOGS = LoggerFactory.getLogger(MessageHandler.class);
    private final UserStatusService userStatusService;

    public EventsCommand(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @Override
    public boolean needExecute(String message, UserStatus userStatus, Long chatId) {
        if (userStatus == null || !userStatus.getUserCommand().equals(eventsString) || userStatus.IsCommand(message)) {
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
        try {
            BotResponse response = new BotResponse(
                    chatId,
                    getApiAnswer(userStatus, message, chatId));
            writer.writeAnswer(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getApiAnswer(UserStatus userStatus, String message, Long chatId) {

        if (userStatus.getCountQuestions() == 0) {
            return questions.get(userStatus.incrementCountQuestions());
        }

        int indFirstExpectedAnswer = (userStatus.getCountQuestions() - 1) * 2;
        String firstExpectedAnswer = expectedAnswers.get(indFirstExpectedAnswer);
        String secondExpectedAnswer = expectedAnswers.get(indFirstExpectedAnswer + 1);

        if (firstExpectedAnswer.trim().equalsIgnoreCase(message)) {
            userStatus.addParam(paramForApi.get(firstExpectedAnswer));
        } else {
            if (secondExpectedAnswer.trim().equalsIgnoreCase(message)) {
                userStatus.addParam(paramForApi.get(secondExpectedAnswer));
            } else {
                return errorAnswer;
            }
        }

        if (userStatus.getCountParam() == lengthQuestions) {
            String location = userStatus.getLocation();
            boolean isFree = userStatus.getIsFree();
            userStatusService.clearUserStatus(chatId);
            try {
                LOGS.info("loc[%s] free [%s]".formatted(location, isFree));
                return apiHandler.getResponse(location, isFree);
            } catch (Exception e) {
                LOGS.error("Error during handle [%s] by user [%s]".formatted(message, chatId), e);
                return "Что то пошло не так";
            }
        } else {
            return questions.get(userStatus.incrementCountQuestions());
        }
    }
}
