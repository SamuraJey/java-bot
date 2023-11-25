package ru.duckteam.javatgbot.logic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserData {
    private String userCommand = "";
    private List<String> params = new ArrayList<>();
    private int countParam = 0;
    private final static String[] arrayCommands = {"/echo","/events","/start"};

    public UserData(){
        params = new ArrayList<>();
    }
    public boolean needDelete(){return params.size() == countParam;}
    public String getLocation(){
        return params.get(0);
    }

    public boolean getIsFree(){

        return Boolean.parseBoolean(params.get(1));
    }

    public void setUserCommand(String userCommand){
        this.userCommand = userCommand;
    }

    public String getUserCommand(){
        return userCommand;
    }

    public boolean arrayParamIsEmpty(){ return params.isEmpty(); }

    public void addParam(String param){ params.add(param); }

    public int getCountParam(){ return params.size(); }
    public boolean IsCommand(String command){ return Arrays.asList(arrayCommands).contains(command); }
}
