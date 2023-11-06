package ru.duckteam.javatgbot.telegram;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.Handler;
import ru.duckteam.javatgbot.MessageConverter;
import ru.duckteam.javatgbot.logic.BotCommand;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.MessageHandler;
import ru.duckteam.javatgbot.logic.command.EchoCommand;
import ru.duckteam.javatgbot.logic.command.EventsCommand;
import ru.duckteam.javatgbot.logic.command.StartCommand;

import java.util.List;


public class Bot extends TelegramLongPollingBot implements AnswerWriter {
    private final String botName;
    private final MessageConverter reader = new TelegramMessageConverter();
    private final EventsCommand eventsCommand = new EventsCommand();
    private final EchoCommand echoCommand = new EchoCommand();
    private final StartCommand startCommand = new StartCommand();
    private final Handler handler = new MessageHandler(List.of(new BotCommand[]{echoCommand, eventsCommand,startCommand})); //TODO создавать команды здесь + внелрять в них UserStatusService

    public Bot(String apiKey, String botName){
        super(apiKey);
        this.botName = botName;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }


    @Override
    public void writeAnswer(BotResponse response) {
        SendMessage sm = SendMessage.builder()
                .chatId(response.getChatId().toString()) //Who are we sending a message to
                .text(response.getResponeString()).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        BotRequest request = reader.convertMessage(update);
        handler.handle(request, this);
    }
}
