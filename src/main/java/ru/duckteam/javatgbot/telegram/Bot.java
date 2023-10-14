package ru.duckteam.javatgbot.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.MessageConverter;
import ru.duckteam.javatgbot.MessageHandler;
import ru.duckteam.javatgbot.kudago.KgMessage;
import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.EchoMessageHandler;

public class  Bot extends TelegramLongPollingBot implements AnswerWriter {
    private final String botName;
    private final MessageConverter reader = new TelegramMessageConverter();
    private final MessageHandler handler = new EchoMessageHandler();

    public Bot(String apiKey, String botName) {
        super(apiKey);
        this.botName = botName;
    }

    public void onUpdateReceived(Update update) {
        BotRequest request = reader.convertMessage(update);
        KgMessage message = new KgMessage();
        handler.handle(request, this,message);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void writeAnswer(BotResponse response) {
        //String userName = response.getUserName();
        //long userId = response.getUserId();
        String chatId = response.getChatId().toString();
        //int messageId = response.getMessageId();
        String message = response.getMessage();

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(message);
        try {
            execute(new SendMessage(chatId, message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
