package ru.duckteam.javatgbot.logic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserStatus {
    private String userCommand = "";
    private List<String> params = new ArrayList<>();
    private int countQuestions = 0;
    private final static String[] arrayCommands = {"/echo","/events","/start"};

    public UserStatus(){
        params = new ArrayList<>();
    }
    public String getLocation(){
        return params.get(0);
    }
    public boolean getIsFree(){return Boolean.parseBoolean(params.get(1));}
    public void setUserCommand(String userCommand){
        this.userCommand = userCommand;
    }
    public String getUserCommand(){
        return userCommand;
    }
    public void addParam(String param){ params.add(param); }
    public int getCountParam(){ return params.size(); }
    public boolean IsCommand(String command){ return Arrays.asList(arrayCommands).contains(command); }
    public int getCountQuestions(){return countQuestions;}
    public int incrementCountQuestions(){return countQuestions++;}
    public void deleteUserCommand(){userCommand = "";}
    public void deleteParams(){
        params = new ArrayList<>();
        countQuestions = 0;
    }

}
