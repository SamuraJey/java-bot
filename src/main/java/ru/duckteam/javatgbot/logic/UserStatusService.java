package ru.duckteam.javatgbot.logic;

import java.util.HashMap;
import java.util.Map;

public class UserStatusService {
    private final Map<Long, UserStatus> userStatus = new HashMap<>();

    public void setUserStatus(Long chatId, String status) {
        UserStatus userStatus = new UserStatus();
        if (!(this.userStatus.get(chatId) == null)) {
            userStatus = this.userStatus.get(chatId);
        }

        userStatus.setUserCommand(status);
        this.userStatus.put(chatId, userStatus);
    }

    public void RemoveUserStatus(Long chatId) {
        userStatus.remove(chatId);
    }

    public UserStatus getUserData(Long chatId) {
            return userStatus.get(chatId);
    }
    // TODO return null, возвращать userData

    /*public boolean needDeleteStatus(Long chatId) {
        return userStatus.get(chatId).needDelete();
    }*/

}