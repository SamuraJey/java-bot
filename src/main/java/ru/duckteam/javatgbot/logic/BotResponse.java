package ru.duckteam.javatgbot.logic;

public final class BotResponse {

    private final Long chatId;
    private final String responeString;

    public BotResponse(Long chatId, String responeString) {
        this.chatId = chatId;
        this.responeString = responeString;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getResponeString() {
        return responeString;
    }

}
