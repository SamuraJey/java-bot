package ru.duckteam.javatgbot.logic;

import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private String userCommand;
    private List<String> params = new ArrayList<>();
    private final static int count = 2;
    private final ApiHandler apiHandler = new ApiHandler();
    public String getAnswer(String param) throws Exception {

        if (params.isEmpty() && param.equals("/events")) {
            return "Ты выбрал режим событий, выбери город в котром будем искать их, Екатеринбург или Москва?";
        }

        if (params.isEmpty()){
            if (param.equals("Екатеринбург")) {
                params.add("ekb");
            }
            else if(param.equals("Москва")){
                params.add("msk");
            }
            else {
                return "Введи еще раз, я такого города не знаю";
            }

            return "Хорошо, теперь давай определимся, нужно ли показать платные места, ответь да или нет";
        }

        if (params.size() == 1){
            if (param.equals("да")) {
                params.add("true");
            }
            else if(param.equals("нет")){
                params.add("false");
            }
            else {
                return "Введи еще раз, я не понял";
            }

            return apiHandler.getResponse(this.getLocation(),this.getIsFree());
        }

        return "Что то пошло не так";
    }

    public boolean needDelete(){
        return params.size() == count;
    }
    private String getLocation(){
        return params.get(0);
    }

    private boolean getIsFree(){
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

}
