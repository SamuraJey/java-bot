package ru.duckteam.javatgbot.logic;


import java.util.ArrayList;
import java.util.List;

public class UserData {
    private String userCommand;
    private List<String> params = new ArrayList<>();
    private final static int countParam = 2;

    public boolean needDelete(){
        return params.size() == countParam;
    }
    public String getLocation(){
        return params.get(0);
    }

    public boolean getIsFree(){
        return Boolean.parseBoolean(params.get(1));
    }

    public void setUserCommand(String userCommand){
        this.userCommand = userCommand;
    }

    public void createNewParameter(){
        params = new ArrayList<>();
    }

    public String getUserCommand(){
        return userCommand;
    }

    public boolean arrayParamIsEmpty(){ return params.isEmpty(); }

    public void addParam(String param){ params.add(param); }

    public int getCountParam(){ return params.size(); }
}
