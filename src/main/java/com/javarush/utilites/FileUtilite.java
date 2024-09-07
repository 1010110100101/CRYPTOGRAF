package com.javarush.utilites;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class FileUtilite {
    public static boolean isValidFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("Путь к файлу пустой или null.");
            return false;
        }

        try {
            // Проверка на допустимость пути
            Paths.get(filePath);
        } catch (InvalidPathException e) {
            System.out.println("Путь к файлу недействителен: " + e.getMessage());
            return false;
        }

        // Проверка на существование файла или директории
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Файл или директория не существуют.");
            return false;
        }

        return true;
    }

    public static boolean isValidPathForFileCreation(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("Путь к файлу пустой или null.");
            return false;
        }

        try {
            // Проверка на допустимость пути
            Path path = Paths.get(filePath);

            // Проверка, что путь является файлом, а не директорией
            if (Files.isDirectory(path)) {
                System.out.println("Указан путь к директории, а не к файлу.");
                return false;
            }

            // Получаем родительский каталог
            Path parentDir = path.getParent();
            if (parentDir != null) {
                // Проверка, существует ли родительский каталог и доступен ли он для записи
                if (!Files.exists(parentDir)) {
                    System.out.println("Родительский каталог не существует.");
                    return false;
                }
                if (!Files.isWritable(parentDir)) {
                    System.out.println("Родительский каталог недоступен для записи.");
                    return false;
                }
            }

            // Проверка, доступен ли файл для записи, если он уже существует
            if (Files.exists(path) && !Files.isWritable(path)) {
                System.out.println("Файл существует, но недоступен для записи.");
                return false;
            }

        } catch (InvalidPathException e) {
            System.out.println("Путь к файлу недействителен: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean isValidCryptoKey(String input) {
        // Проверка, что строка не пустая и имеет более 0 символов
        if (input == null || input.isEmpty()) {
            System.out.println("Строка пустая или null.");
            return false;
        }

        // Проверка, что все символы в строке уникальны
        Set<Character> charSet = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!charSet.add(c)) {
                System.out.println("Строка содержит повторяющиеся символы.");
                return false;
            }
        }

        return true;
    }

    public static boolean isValidShiftForCryptoKey(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Строка пустая или null.");
            return false;
        }

        // Проверка, что строка состоит только из цифр
        if (!input.matches("\\d+")) {
            System.out.println("Строка содержит не только цифры.");
            return false;
        }

        try {
            // Попытка преобразования строки в long
            long value = Long.parseLong(input);
            if (value < 0) {
                System.out.println("Значение меньше нуля.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Число выходит за пределы допустимого диапазона типа long.");
            return false;
        }

        return true;
    }

    public static String getFileContent(String filePath) {

        String content = "";
        try {
            // Читаем все строки из файла
            content = Files.readString(Paths.get(filePath));
        } catch (Exception e) {
            // Обработка исключений при чтении файла
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return content;
    }

    public static boolean saveFileContent(String outputFilePath, String text) {
        File file = new File(outputFilePath);

        try {
            // Создаем родительские каталоги, если они не существуют
            file.getParentFile().mkdirs();

            // Создаем новый файл, если он не существует
            if (!file.exists()) {
                file.createNewFile();
            }

            // Открываем файл для записи и записываем текст
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
            writer.close();
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            return false;
        }
    }

    public static String replaceSymbolsOn(String text, String leftStr, String rightString) {

        if(text == null
                || text.length() == 0
                || leftStr == null
                || leftStr.length() == 0
                || rightString.length() == 0)
            return "";

        String newText = text;
        newText = text.replace(leftStr, "[^@*]");
        newText = newText.replace(rightString, leftStr);
        newText = newText.replace("[^@*]", rightString);

        return newText;
    }

}
