package ru.duckteam.javatgbot;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {

    @Override
    public BotRequest getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите сообщение: ");
        String message = scanner.nextLine();
        return new BotRequest(message);
    }

}
