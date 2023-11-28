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
    private final static List<String> questions = List.of(
            "Ты выбрал режим событий, выбери город в котром будем искать их, Екатеринбург или Москва?",
            "Хорошо, теперь давай определимся, нужно ли показать платные места, ответь да или нет");
    private final static int lengthQuestions = questions.size();
    private final static List<String[]> expectedAnswers = List.of(new String[]{"Екатеринбург", "Москва"},
            new String[]{"Да", "Нет"});
    private final static String errorAnswer = "Введи еще раз, я не понял";
    private final static Map<String,String> paramForApi = Map.of("Екатеринбург","ekb",
            "Москва","msk",
            "Да","true" ,
            "Нет","false");
    private static final ApiHandler apiHandler = new ApiHandler();
    private static final Logger LOGS = LoggerFactory.getLogger(MessageHandler.class);

    private final UserStatusService userStatusService;

    public EventsCommand(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @Override
    public boolean needExecute(String message, UserStatus userStatus) {
        if(userStatus == null || !userStatus.getUserCommand().equals(eventsString) || userStatus.IsCommand(message)) {
            return eventsString.equals(message);
        }
        return true;
    }

    @Override
    public void execute(String message, Long chatId, AnswerWriter writer, UserStatus userStatus){
        try {
            BotResponse response = new BotResponse(
                    chatId,
                    getApiAnswer(userStatus, message, chatId));
            writer.writeAnswer(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getApiAnswer(UserStatus userStatus, String message, Long chatId) {
        // TODO .trim().equalsIgnoreCase();

        if (userStatus.getCountQuestions() == 0){
            return questions.get(userStatus.incrementCountQuestions());
        }
        else {
            String[] answers = expectedAnswers.get(userStatus.getCountQuestions()-1);
            for (String answer : answers) {
                if (message.trim().equalsIgnoreCase(answer)) {
                    userStatus.addParam(paramForApi.get(answer));
                    if (userStatus.getCountParam() == lengthQuestions) {
                        String location = userStatus.getLocation();
                        boolean isFree = userStatus.getIsFree();
                        userStatusService.RemoveUserStatus(chatId);
                        try {
                            return apiHandler.getResponse(location,isFree);
                        } catch (Exception e) {
                            LOGS.error("Error during handle [%s] by user [%s]".formatted(message, chatId), e);
                            return "Что то пошло не так";
                        }
                    } else {
                        return questions.get(userStatus.incrementCountQuestions());
                    }
                }
            }
            return errorAnswer;
        }
        /*if (userData.arrayParamIsEmpty() && message.equals("/events")) {
            return "Ты выбрал режим событий, выбери город в котром будем искать их, Екатеринбург или Москва?";
        }

        if (userData.arrayParamIsEmpty()){
            if (message.equals("Екатеринбург")) {
                userData.addParam("ekb");
            }
            else if(message.equals("Москва")){
                userData.addParam("msk");
            }
            else {
                return "Введи еще раз, я такого города не знаю";
            }

            return "Хорошо, теперь давай определимся, нужно ли показать платные места, ответь да или нет";
        }

        if (userData.getCountParam() == 1){
            if (message.equals("да")) {
                userData.addParam("true");
            }
            else if(message.equals("нет")){
                userData.addParam("false");
            }
            else {
                return "Введи еще раз, я не понял";
            }
            try {
                return apiHandler.getResponse(userData.getLocation(),userData.getIsFree());
            }
            catch (Exception e){
                LOGS.error("Error during handle [%s] by user [%s]".formatted(message,chatId), e);
                return "Что то пошло не так";
            }

        }

        return "Что то пошло не так";*/
    }

    @Override
    public String getNameCommand() {
        return eventsString;
    }
}
