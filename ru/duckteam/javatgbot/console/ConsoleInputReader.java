package ru.duckteam.javatgbot.console;

import ru.duckteam.javatgbot.logic.BotRequest;
import ru.duckteam.javatgbot.InputReader;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {
    public BotRequest getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите сообщение: ");
        String message = scanner.nextLine();
        return new BotRequest(message);
    }

}
