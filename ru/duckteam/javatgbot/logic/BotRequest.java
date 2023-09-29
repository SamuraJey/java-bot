package ru.duckteam.javatgbot.logic;

import org.telegram.telegrambots.meta.api.objects.User;
import ru.duckteam.javatgbot.Bot;

public final class BotRequest {
    private final User userName;
    private final Long userId;
    private final Long chatId;
    private final Integer messageId;
    private final Bot bot;

    public BotRequest(User userName, Long userId, Long chatId, Integer messageId, Bot bot) {
        this.userName = userName;
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
        this.bot = bot;
    }

    public String getUserName() {
        return userName.getUserName();
    }

    public Long getUserId() {
        return userId;
    }

    public Long getChatId() {
        return chatId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public Bot getBot() {
        return bot;
    }
}
