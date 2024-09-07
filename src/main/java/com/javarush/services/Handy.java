package com.javarush.services;

import com.javarush.entity.Result;
import com.javarush.exception.ApplicationException;
import com.javarush.utilites.FileUtilite;

import java.util.Scanner;

import static com.javarush.constants.ApplicationCompletionConstants.UNCRYPTED_FILE;
import static com.javarush.repository.ResultCode.ERROR;
import static com.javarush.repository.ResultCode.OK;

public class Handy implements Function {
    @Override
    public Result execute(String[] parameters) {

        try {
            boolean result = true;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Для замены символов придерживайтесь формата записи: а=b (символы 'a' и 'b' будут взаимозаменены)");

            while(true) {
                System.out.println("Введите сиволы: ");

                String input = scanner.nextLine();

                // Перевірка довжини рядка
                if (input.length() < 3) {
                    System.out.println("Ошибка: строка должна быть не менее трех символов. Попробуйте еще раз.");
                    continue;
                }

                // Перевірка наявності знаку "="
                if (!input.contains("=")) {
                    System.out.println("Ошибка: строка должна содержать знак '='. Попробуйте еще раз.");
                    continue;
                }

                // Розбиття рядка на масив з двох елементів
                String[] parts = input.split("=", 2);

                // Перевірка, чи є частини після розбиття
                if (parts.length != 2) {
                    System.out.println("Ошибка: строка должна быть в формате 'а=b'. Попробуйте еще раз.");
                    continue;
                }

                //получение текста из автоматически расшифрованного файла
                String uncryptedText = FileUtilite.getFileContent(UNCRYPTED_FILE).toLowerCase();
                uncryptedText = FileUtilite.replaceSymbolsOn(uncryptedText, parts[0], parts[1]);
                result = FileUtilite.saveFileContent(UNCRYPTED_FILE, uncryptedText);

                break;
            }

            System.out.println(result);
        }
        catch (Exception e) {
            return new Result(ERROR, new ApplicationException("Decode operation finished with exception", e));
        }

        return new Result(OK);
    }
}
