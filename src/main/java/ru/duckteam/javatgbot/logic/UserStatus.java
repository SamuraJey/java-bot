package ru.duckteam.javatgbot.logic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserStatus {
    private final static String[] arrayCommands = {"/echo", "/events", "/start", "/weather"};
    private String userCommand = "";
    private List<String> params;
    private int countQuestions = 0;

    private int weatherCountQuestions = 0;

    private String weatherCity;
    private double userLongitude;

    private double userLatitude;

    public UserStatus() {
        params = new ArrayList<>();

    }

    public String getLocation() {
        return params.get(0);
    }

    public boolean getIsFree() {
        return Boolean.parseBoolean(params.get(1));
    }

    public long getFirstDayTimestamp() {
        return FromDateInLong(params.get(2));
    }

    public long getSecondDayTimestamp() {
        return FromDateInLong(params.get(3));
    }

    public int getCountQuestions() {
        return countQuestions;
    }

    public String getUserCommand() {
        return userCommand;
    }

    public void setUserCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    public void addParam(String param) {
        params.add(param);
    }

    public void setWeatherCity(String city) {
        this.weatherCity = city;
    }

    public double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(double userLongitude) {
        this.userLongitude = userLongitude;
    }

    public double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public boolean IsCommand(String command) {
        return Arrays.asList(arrayCommands).contains(command);
    }

    public void plusOneCountQuestions() {
        countQuestions = countQuestions + 1;
    }

    public void plusOneWeatherCountQuestions() {
        weatherCountQuestions = weatherCountQuestions + 1;
    }

    public int getWeatherCountQuestions() {
        return weatherCountQuestions;
    }

    public void deleteParams() {
        params = new ArrayList<>();
        countQuestions = 0;
        weatherCountQuestions = 0;
//        userLongitude = 0;
//        userLatitude = 0;

    }

    public void deleteUserCommand() {
        userCommand = "";
    }

    private long FromDateInLong(String date) {

        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));

        LocalDate localDate = LocalDate.of(year, month, day);

        return localDate.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();
    }

}
