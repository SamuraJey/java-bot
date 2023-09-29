package ru.duckteam.javatgbot;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.duckteam.javatgbot.logic.BotRequest;


public interface InputReader {
    BotRequest getUserInput(Update update, Bot bot);
}
