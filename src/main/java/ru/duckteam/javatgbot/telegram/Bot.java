package ru.duckteam.javatgbot.telegram;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.MessageConverter;
import ru.duckteam.javatgbot.command.EventsCommand;
import ru.duckteam.javatgbot.command.StartCommand;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.MessageHandler;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;


public class Bot extends TelegramLongPollingCommandBot implements AnswerWriter {
    private final String botName;
    private final MessageConverter reader = new TelegramMessageConverter();
    private final MessageHandler handler = new MessageHandler();


    public Bot(String apiKey, String botName) throws Exception {
        super(apiKey);
        StartCommand startCommand = new StartCommand();
        EventsCommand eventsCommand = new EventsCommand();
        //SetMyCommands myCommands = new SetMyCommands();
        //BotCommand[] botCommands = new BotCommand[]{new BotCommand("echo" ,""),new BotCommand("events","")};
        //myCommands.setMyCommands(Arrays.asList(botCommands));
        registerAll(startCommand,eventsCommand);
        this.botName = botName;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    public void sendText(BotResponse response) {
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
    public void writeAnswer(BotResponse response) {
        sendText(response);
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        BotRequest request = reader.convertMessage(update);
        handler.handle(request, this);//,isEcho,isEvents);
    }

    @Override
    public boolean filter(Message message) {
        return super.filter(message);
    }

}
