package ru.duckteam.javatgbot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

public abstract class BaseCommand implements IBotCommand {

    private static final Logger LOGS = LoggerFactory.getLogger(BaseCommand.class);

    public abstract BotApiMethodMessage answer(AbsSender bot, Message message);

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        try {
            absSender.execute(answer(absSender, message));
        } catch (Exception e) {
            LOGS.error("Error while executing command {}", getCommandIdentifier(), e);
        }
    }
}
