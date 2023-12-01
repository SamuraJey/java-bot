package ru.duckteam.javatgbot.logic;

import java.util.HashMap;
import java.util.Map;

public class UserStatusService {
    private final Map<Long, UserStatus> userStatusMap = new HashMap<>();

    public void setUserStatus(Long chatId, String status) {
        UserStatus userStatus = new UserStatus();
        if (!(this.userStatusMap.get(chatId) == null)) {
            userStatus = this.userStatusMap.get(chatId);
        }

        userStatus.setUserCommand(status);
        this.userStatusMap.put(chatId, userStatus);
    }

    public void removeUserStatus(Long chatId) {
        userStatusMap.remove(chatId);
    }

    public void clearUserStatus(Long chatId){
        UserStatus userStatus = userStatusMap.get(chatId);
        userStatus.deleteUserCommand();
        userStatus.deleteParams();
    }

    public UserStatus getUserData(Long chatId) {
            return userStatusMap.get(chatId);
    }
}