package ru.duckteam.javatgbot.logic.command;

import ru.duckteam.javatgbot.AnswerWriter;
import ru.duckteam.javatgbot.logic.BotCommand;
import ru.duckteam.javatgbot.logic.BotResponse;
import ru.duckteam.javatgbot.logic.UserStatus;
import ru.duckteam.javatgbot.logic.UserStatusService;

public class StartCommand implements BotCommand {
    private static final String startString = "/start";
    private final UserStatusService userStatusService;

    public StartCommand(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @Override
    public boolean needExecute(String message, UserStatus userStatus, Long chatId) {
        if (userStatus == null) {
            userStatusService.setUserStatus(chatId, startString);
            return true;
        }

        if (startString.equals(message)) {
            userStatusService.setUserStatus(chatId, startString);
            userStatusService.clearUserStatus(chatId);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void execute(String message, Long chatId, AnswerWriter writer, UserStatus userStatus) {
        BotResponse response = new BotResponse(
                chatId,
                getAnswer(message));
        writer.writeAnswer(response);
    }

    private String getAnswer(String message) {

        if (message == null) {
            return "Можно отправлять только текстовые сообщения!\nНапиши /start или другую команду";
        }

        if (message.equals(startString)) {
            return """
                    Привет! Вот что умеет этот бот:
                                        
                    /echo - бот будет повторять ваши текстовые сообщения.
                                        
                    /events - бот задаст несколько вопросов и предложит вам список событий с сайта KudaGo.
                                        
                    /weather - бот спросит город и предоставит текущую погоду по данным сервиса OpenWeatherMap.""";
        }
        return "Напиши /start";
    }
}
