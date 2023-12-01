package ru.duckteam.javatgbot.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Deprecated
//Это фигня которю я попытасля сделать
public class UserTimer {

    private final long CLEANUP_DELAY = 1000; //* 60 * 60 * 24 * 7; // 1 week in milliseconds
    private Timer timer;
    private UserStatusService userStatusService;
    private Long chatId;

    private Map lastVisited;


    public UserTimer(UserStatusService userStatusService, Long chatId) {
        this.userStatusService = userStatusService;
        this.chatId = chatId;
//        this.startCleanupTimer(userStatusService, chatId);
        lastVisited = new HashMap();
    }

    public void timerIfNeeded() {
        if (userStatusService.getUserData(chatId) != null) {


        }
    }

    public void startCleanupTimer(UserStatusService userStatusService, Long chatId) {

        timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                userStatusService.clearUserStatus(chatId);
            }
        };
        timer.schedule(task, CLEANUP_DELAY);
    }
}
