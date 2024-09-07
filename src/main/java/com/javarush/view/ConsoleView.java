package com.javarush.view;

import com.javarush.entity.Result;
import com.javarush.utilites.FileUtilite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static com.javarush.constants.ApplicationCompletionConstants.*;
import static com.javarush.constants.CryptoAlphabet.ALPHABET;

public class ConsoleView implements View {

    static {
        printInstructions();
    }

    @Override
    public String[] getParamethers() {

        String[] paramethers = new String[7];

        //paramethers[0] = setMode(); if(paramethers[0].equals("q")) return paramethers;

        paramethers[1] = setFunction();

        if (paramethers[1].equals("q")) return paramethers;
        if (paramethers[1].equals("1"))//если выбрана задача зашифровать файл
        {
            paramethers[2] = setInputFilePath();
            if (paramethers[2].equals("q")) return paramethers;
            paramethers[3] = setOutputFilePath();
            if (paramethers[3].equals("q")) return paramethers;
            paramethers[4] = setCryptoKey();
            if (paramethers[4].equals("q")) return paramethers;
            paramethers[5] = setKeyShift();
            if (paramethers[5].equals("q")) return paramethers;
        }
        else if (paramethers[1].equals("2"))//если выбрана задача расшифровать файл указав ключ дешифрации
        {
            paramethers[3] = setOutputFilePath();
            if (paramethers[3].equals("q")) return paramethers;
            paramethers[4] = setCryptoKey();
            if (paramethers[4].equals("q")) return paramethers;
            paramethers[5] = setKeyShift();
            if (paramethers[5].equals("q")) return paramethers;
        }
        else if (paramethers[1].equals("3a"))//если выбрана задача расшифровать файл методом автоподбора
        {
            paramethers[3] = setOutputFilePath();
            if (paramethers[3].equals("q")) return paramethers;
            paramethers[4] = setCryptoKey();
            if (paramethers[4].equals("q")) return paramethers;
        }
        else if (paramethers[1].equals("3h"))//если выбрана задача расшифровать файл методом ручного подбора
        {
            paramethers[3] = setOutputFilePath();
            if (paramethers[3].equals("q")) return paramethers;
            paramethers[4] = setCryptoKey();
            if (paramethers[4].equals("q")) return paramethers;
        }
        else if (paramethers[1].equals("4"))//если выбрана задача расшифровать файл методом статистического нанализа
        {
            paramethers[3] = setOutputFilePath();
            if (paramethers[3].equals("q")) return paramethers;
            paramethers[6] = setOpenTextFilePath();
            if (paramethers[6].equals("q")) return paramethers;
        }
        else if (paramethers[1].equals("5"))//если выбрана задача вручную доработать автоматически расшифрованный файл
        {

        }

        return paramethers;
    }


    @Override
    public void printResult(Result result) {
        switch ((result.getResultCode())) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
            case EXIT -> System.out.println(QUIT);
        }

        System.out.println(FINISH_TASK);
    }



    private static void printInstructions() {
        //считать содержимое файла
        try {
            // Читаем все строки из файла
            String content = Files.readString(Paths.get(PROGRAM_START_INFO));
            // Выводим содержимое файла в консоль
            System.out.println(content);

        } catch (IOException e) {
            // Обработка исключений при чтении файла
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private String setMode() {

        String mode = "1"; //режим по умолчанию

        try {
            System.out.println(PROGRAM_MODE);
            System.out.println(PUSH_ENTER_TO_CONTINUE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            while (true) {
                try {
                    input = reader.readLine();
                    if (input.equals("q")) {
                        mode = input;
                        break;
                    }
                    else if (input.equals("1") || input.equals("2") || input.equals("3")) {
                        mode = input;
                        break;
                    }
                    else {
                        System.out.println("Неверный ввод. Используйте числа для выбора режима, или нажмите клавишу 'q' для завершения работы программы.");
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении ввода: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            // Обработка исключений при чтении файла
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        System.out.println("Выбран режим: " + mode);
        System.out.println("");
        return mode;
    }

    private String setFunction() {
        String function = "1"; //режим по умолчанию

        try {
            System.out.println(FUNCTION);
            System.out.println(PUSH_ENTER_TO_CONTINUE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            while (true) {
                try {
                    input = reader.readLine();
                    if (input.equals("1")
                            || input.equals("2")
                            || input.equals("3a")
                            || input.equals("3h")
                            || input.equals("4")
                            || input.equals("5")) {
                        function = input;
                        break;
                    }
                    else if (input.equals("q")) {
                        function = input;
                        break;
                    } else {
                        System.out.println("Неверный ввод. Используйте числа для выбора функции, или нажмите клавишу 'q' для завершения работы программы.");
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении ввода: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            // Обработка исключений при чтении файла
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        System.out.println("Выбрана задача: " + function);
        System.out.println("");
        return function;
    }

    private String setInputFilePath() {
        String filePath = "0"; //режим по умолчанию

        try {
            System.out.println(INPUT_FILE_NAME);
            System.out.println(PUSH_ENTER_TO_CONTINUE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            while (true) {
                try {
                    input = reader.readLine();

                    if (input.equals("0")) {
                        filePath = input;
                        break;
                    }
                    if (input.equals("q")) {
                        filePath = input;
                        break;
                    }
                    else if (FileUtilite.isValidFilePath(input)) {
                        filePath = input;
                        break;
                    }
                    else {
                        System.out.println("Ошибка доступа к файлу. Укажите действительный путь к файлу, или нажмите клавишу 'q' для завершения работы программы.");
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении ввода: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            // Обработка исключений при чтении файла
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        System.out.println("Исходный файл для зашифровывания: " + (filePath.equals("0") ? INPUT_FILE_DEMO : filePath));
        System.out.println("");
        return filePath.equals("0") ? INPUT_FILE_DEMO : filePath;
    }

    private String setOutputFilePath() {
        String filePath = "0"; //режим по умолчанию

        try {
            System.out.println(CRYPTED_FILE_NAME);
            System.out.println(PUSH_ENTER_TO_CONTINUE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            while (true) {
                try {
                    input = reader.readLine();
                    if (input.equals("q")) {
                        filePath = input;
                        break;
                    }
                    else if (input.equals("0")) {
                        System.out.println("Выбран демонстрационный файл.");
                        filePath = input;
                        break;
                    }
                    else if (FileUtilite.isValidPathForFileCreation(input)) {
                        filePath = input;
                        break;
                    }
                    else {
                        System.out.println("Ошибка доступа к файлу. Укажите действительный путь к файлу, или нажмите клавишу 'q' для завершения работы программы.");
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении ввода: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            // Обработка исключений при чтении файла
            System.err.println("Ошибка чтения данных: " + e.getMessage());
        }

        System.out.println("Результирующий зашифрованный файл: " + (filePath.equals("0") ? CRYPTED_FILE : filePath));
        System.out.println("");
        return filePath.equals("0") ? CRYPTED_FILE : filePath;
    }

    private String setOpenTextFilePath() {
        String filePath = "0"; //режим по умолчанию

        try {
            System.out.println(OPEN_FILE_NAME);
            System.out.println(PUSH_ENTER_TO_CONTINUE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            while (true) {
                try {
                    input = reader.readLine();

                    if (input.equals("0")) {
                        filePath = input;
                        break;
                    }
                    if (input.equals("q")) {
                        filePath = input;
                        break;
                    }
                    else if (FileUtilite.isValidFilePath(input)) {
                        filePath = input;
                        break;
                    }
                    else {
                        System.out.println("Ошибка доступа к файлу. Укажите действительный путь к файлу, или нажмите клавишу 'q' для завершения работы программы.");
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении ввода: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            // Обработка исключений при чтении файла
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        System.out.println("Опорный текст: " + (filePath.equals("0") ? OPEN_FILE_DEMO : filePath));
        System.out.println("");
        return filePath.equals("0") ? OPEN_FILE_DEMO : filePath;
    }

    private String setCryptoKey() {
        String cryptoKey = "0"; //режим по умолчанию

        try {
            System.out.println(CRYPTO_KEY);
            System.out.println(PUSH_ENTER_TO_CONTINUE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            while (true) {
                try {
                    input = reader.readLine();

                    if (input.equals("q")) {
                        cryptoKey = input;
                        break;
                    }
                    else if (input.equals("0")) {
                        System.out.println("Выбран ключ шифрования по умолчанию.");
                        cryptoKey = input;
                        break;
                    }
                    else if (FileUtilite.isValidCryptoKey(input)) {
                        cryptoKey = input;
                        break;
                    }
                    else {
                        System.out.println("Укажите правильный срипто-ключ, или нажмите клавишу 'q' для завершения работы программы.");
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении ввода: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            // Обработка исключений при чтении файла
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        System.out.println("Ключ шифрования: " + (cryptoKey.equals("0") ? ALPHABET : cryptoKey));
        System.out.println("");
        return cryptoKey.equals("0") ? ALPHABET : cryptoKey;
    }

    private String setKeyShift() {
        String shifKey = "0"; //режим по умолчанию

        try {
            System.out.println(SHIFT_KEY);
            System.out.println(PUSH_ENTER_TO_CONTINUE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            while (true) {
                try {
                    input = reader.readLine();

                    if (input.equals("q")) {
                        shifKey = input;
                        break;
                    }
                    else if (input.equals("0")) {
                        System.out.println("Выбран режим автосмещения.");
                        shifKey = String.valueOf(new Random().nextInt(200));
                        break;
                    }
                    else if (FileUtilite.isValidShiftForCryptoKey(input)) {
                        shifKey = input;
                        break;
                    }
                    else {
                        System.out.println("Укажите число в диапазоне от 1 до " + String.valueOf(Integer.MAX_VALUE) + ", или нажмите клавишу 'q' для завершения работы программы.");
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении ввода: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            // Обработка исключений при чтении файла
            System.err.println("Ошибка при чтения данных: " + e.getMessage());
        }

        System.out.println("Шаг-смещение: " + String.valueOf(shifKey));
        System.out.println("");
        return shifKey;
    }
}
