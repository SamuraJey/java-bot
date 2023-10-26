package ru.duckteam.javatgbot.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.Handler;
import ru.duckteam.javatgbot.logic.kudago.ApiHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// TODO Избавиться от всего лишнего, комменты, старый код и т.д.

public class MessageHandler implements Handler {
    private static final Logger LOGS = LoggerFactory.getLogger(MessageHandler.class);
    private final List<BotCommand> commands;
    private final UserStatusService userStatus;
    public MessageHandler(List<BotCommand> commands) {
        this.commands = commands;
        userStatus = new UserStatusService();
    }


    @Override
    public void handle(BotRequest request, AnswerWriter writer) {
        /*
        Done_TODO Сейчас бот при выборе режима /events сразу после команды отправляет все ивенты.
        Done_TODO Или, что бы он отправлял ивенты сразу после команды, но переходил после этого в режим /echo
        Done_TODO Сейчас если один пользователь переключает режим, то он меняется у всех пользователей, надо как-то исправлять.
        */
        //BotResponse response;

        for (BotCommand command : commands) {
            if (command.needExecute(request,userStatus)) {
                command.execute(request, writer,userStatus);
                break;
            }
        }
        // Done_TODO Венрунть логи
        LOGS.info("Получено сообщение ID=%s %s".formatted(request.getChatId(), request.getMessage()));
    }
}