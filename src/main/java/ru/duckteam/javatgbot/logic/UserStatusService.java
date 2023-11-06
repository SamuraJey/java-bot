package ru.duckteam.javatgbot.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserStatusService {

    private final static String[] arrayCommands = {"/echo","/events","/start"};
    private final Map<Long, UserData> userStatus = new HashMap<>();
    //TODO вынести всб логику хранения состояния пользователей сюда

    public void setUserStatus(Long chatId, String status){
            UserData userData = new UserData();
            userData.setUserCommand(status);
            userData.createNewParameter();
            userStatus.put(chatId,userData);
    }

    public void RemoveUserStatus(Long chatId){
            userStatus.remove(chatId);
    }

    public String getUserStatus(Long chatId) {
        if (userStatus.containsKey(chatId)){
            return userStatus.get(chatId).getUserCommand();
        }
        return "";
    }

    public String getAnswer(Long chatId,String param) throws Exception {
        return userStatus.get(chatId).getAnswer(param);
    }

    public boolean needDeleteStatus(Long chatId){
        return userStatus.get(chatId).needDelete();
    }
    public boolean isEmpty(){
        return userStatus.isEmpty();
    }

    public boolean isCommand(String command){
        return Arrays.asList(arrayCommands).contains(command);
    }
}
