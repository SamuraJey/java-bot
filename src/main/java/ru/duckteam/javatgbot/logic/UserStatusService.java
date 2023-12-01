package ru.duckteam.javatgbot.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


// TODO Сделать так, что бы таймер сбрасывался при получении нового сообщения. Пока он сбрасывается всега по истечению времени
public class UserStatusService {
    private final Map<Long, UserStatus> userStatusMap = new HashMap<>();
    private final Map<Long, ScheduledExecutorService> userTimerMap = new HashMap<>();

    public void setUserStatus(Long chatId, String status) {
        UserStatus userStatus = userStatusMap.getOrDefault(chatId, new UserStatus());
        userStatus.setUserCommand(status);
        userStatusMap.put(chatId, userStatus);

        ScheduledExecutorService timer = userTimerMap.get(chatId);
        if (timer != null) {
            timer.shutdown();
        }

        timer = Executors.newSingleThreadScheduledExecutor();
        timer.schedule(() -> removeUserStatus(chatId), 10, TimeUnit.SECONDS);
        userTimerMap.put(chatId, timer);
    }

    public void removeUserStatus(Long chatId) {
        userStatusMap.remove(chatId);
        ScheduledExecutorService timer = userTimerMap.remove(chatId);
        if (timer != null) {
            timer.shutdown();
        }
    }

    public void clearUserStatus(Long chatId) {
        UserStatus userStatus = userStatusMap.get(chatId);
        if (userStatus != null) {
            userStatus.deleteUserCommand();
            userStatus.deleteParams();
        }
    }

    public void resetUserTimer(Long chatId) {
        ScheduledExecutorService timer = userTimerMap.get(chatId);
        if (timer != null) {
            timer.shutdown();
        }

        timer = Executors.newSingleThreadScheduledExecutor();
        timer.schedule(() -> removeUserStatus(chatId), 10, TimeUnit.SECONDS);
        userTimerMap.put(chatId, timer);
    }

    public UserStatus getUserData(Long chatId) {
        return userStatusMap.get(chatId);
    }
}