package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UserStatusService {
    private final Map<Long, UserData> userStatus = new HashMap<>();

    public void setUserStatus(Long chatId, String status) {
        UserData userData = new UserData();
        if (!(userStatus.get(chatId) == null)) {
            userData = userStatus.get(chatId);
        }

        userData.setUserCommand(status);
        userStatus.put(chatId, userData);
    }

    public void RemoveUserStatus(Long chatId) {
        userStatus.remove(chatId);
    }

    public UserData getUserData(Long chatId) {
            return userStatus.get(chatId);
    }
    // TODO return null, возвращать userData

    public boolean needDeleteStatus(Long chatId) {
        return userStatus.get(chatId).needDelete();
    }
}