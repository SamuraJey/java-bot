package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class UserTimer {

    private final long CLEANUP_DELAY = 1000 * 60 * 60 * 24 * 7; // 1 week in milliseconds
    private static final Timer timer = new Timer();
    private UserStatusService userStatusService;
    private Map<Long, TimerTask> userTimerTask;
    private static final Logger LOGS = LoggerFactory.getLogger(UserTimer.class);

    public UserTimer(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
        userTimerTask = new HashMap<Long, TimerTask>();
    }

    public void startCleanupTimer(Long chatId) {

        if (userStatusService.getUserData(chatId) == null) {
            return;
        }

        LOGS.info("request [%s]".formatted(chatId));
        if (userTimerTask.get(chatId) != null) {
            LOGS.info("delete task [%s]".formatted(chatId));
            TimerTask task = userTimerTask.get(chatId);
            task.cancel();
        }

        TimerTask task = new TimerTask() {
            public void run() {
                userStatusService.removeUserStatus(chatId);
                userTimerTask.remove(chatId);
            }
        };
        timer.schedule(task, CLEANUP_DELAY);
        userTimerTask.put(chatId, task);
        LOGS.info("set task [%s]".formatted(chatId));
    }
}
