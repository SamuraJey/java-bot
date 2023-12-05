package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UserStatusService {
    private final Map<Long, UserStatus> userStatusMap = new HashMap<>();
    private static final Logger LOGS = LoggerFactory.getLogger(UserStatusService.class);

    public void setUserStatus(Long chatId, String status) {
        UserStatus userStatus = userStatusMap.getOrDefault(chatId, new UserStatus());
        userStatus.setUserCommand(status);
        userStatusMap.put(chatId, userStatus);
    }

    public void removeUserStatus(Long chatId) {
        LOGS.info("remove userStatus [%s]".formatted(chatId));
        userStatusMap.remove(chatId);
    }

    public void clearUserStatus(Long chatId) {
        UserStatus userStatus = userStatusMap.get(chatId);
        if (userStatus != null) {
            userStatus.deleteParams();
            userStatus.deleteUserCommand();
        }
    }

    public UserStatus getUserData(Long chatId) {
        return userStatusMap.get(chatId);
    }
}