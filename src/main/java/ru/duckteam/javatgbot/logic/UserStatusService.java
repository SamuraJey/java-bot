package ru.duckteam.javatgbot.logic;

import java.util.HashMap;
import java.util.Map;

public class UserStatusService {
    private final Map<Long, String> userStatus = new HashMap<>();
    //TODO вынести всб логику хранения состояния пользователей сюда

    public void setUserStatusStatus(Long chatId, String status){
        if (userStatus.containsKey(chatId)){
            userStatus.replace(chatId,status);
        }
        else {
           userStatus.put(chatId,status);
        }
    }

    public void RemoveUserStatus(Long chatId){
            userStatus.remove(chatId);
    }

    public String getUserStatus(Long chatId) {
        return userStatus.get(chatId);
    }
}
