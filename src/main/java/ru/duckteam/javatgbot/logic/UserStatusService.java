package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserStatusService {
    private static final Logger LOG = LoggerFactory.getLogger(UserStatusService.class);

    private final static String[] arrayCommands = {"/echo", "/events", "/start"};
    private final Map<Long, UserData> userStatus = new HashMap<>();
    //TODO вынести всб логику хранения состояния пользователей сюда
    private final ApiHandler apiHandler = new ApiHandler();

    public void setUserStatus(Long chatId, String status) {
        UserData userData = new UserData();
        userData.setUserCommand(status);
        userData.createNewParameter();
        userStatus.put(chatId, userData);
    }

    public void RemoveUserStatus(Long chatId) {
        userStatus.remove(chatId);
    }

    public String getUserStatus(Long chatId) {
        if (userStatus.containsKey(chatId)) {
            return userStatus.get(chatId).getUserCommand();
        }
        return "";
    }
    // TODO return null, возвращать userData

    // TODO botName.trim().equalsIgnoreCase();
    public String getAnswer(String param, Long chatId) {

        UserData userData = userStatus.get(chatId);
        if (userData.arrayParamIsEmpty() && param.equals("/events")) {
            return "Ты выбрал режим событий, выбери город в котром будем искать их, Екатеринбург или Москва?";
        }

        if (userData.arrayParamIsEmpty()) {
            if (param.equalsIgnoreCase("Екатеринбург")) {
                userData.addParam("ekb");
            }
            else if (param.equalsIgnoreCase("Москва")) {
                userData.addParam("msk");
            }
            else {
                return "Введи еще раз, я такого города не знаю";
            }

            return "Хорошо, теперь давай определимся, нужно ли показать платные места, ответь да или нет";
        }

        if (userData.getCountParam() == 1) {
            if (param.equalsIgnoreCase("да")) {
                userData.addParam("true");
            }
            else if (param.equalsIgnoreCase("нет")) {
                userData.addParam("false");
            }
            else {
                return "Введи еще раз, я не понял";
            }
            try {
                return apiHandler.getResponse(userData.getLocation(), userData.getIsFree());
            }
            catch (Exception e) {
                LOG.error("Error during handle [%s] by user [%s]".formatted(param, chatId), e);
                return "Что то пошло не так";
            }

        }

        return "Что то пошло не так";
    }
    // TODO вынести в EventsCommand

    public boolean needDeleteStatus(Long chatId) {
        return userStatus.get(chatId).needDelete();
    }

    public boolean isEmpty() {
        return userStatus.isEmpty();
    }
    // TODO убрать IsEmpty

    public boolean isCommand(String command) {
        return Arrays.asList(arrayCommands).contains(command);
    }
}
