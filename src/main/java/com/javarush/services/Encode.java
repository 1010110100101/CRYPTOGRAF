package com.javarush.services;

import com.javarush.entity.Result;
import com.javarush.exception.ApplicationException;
import com.javarush.utilites.FileUtilite;

import static com.javarush.repository.ResultCode.ERROR;
import static com.javarush.repository.ResultCode.OK;

public class Encode implements Function{
    @Override
    public Result execute(String[] parameters) {

        try {
            //анализируем параметры и производим зашифровывание файла
            //нам нужны параметры: [2], [3], [4], [5]

            String inputFilePath = parameters[2];//путь к исходному файлу
            String outputFilePath = parameters[3];//путь к результирующему зашифрованному файлу
            String key = parameters[4];//строка-ключ шифрования
            String shift = parameters[5];//шаг-смещение для ключа шифрования

            String inputText = FileUtilite.getFileContent(inputFilePath).toLowerCase();
            String cryptedText = encrypt(inputText, key, Integer.valueOf(shift)).toLowerCase();

            boolean result = FileUtilite.saveFileContent(outputFilePath, cryptedText);

            System.out.println(result);
        }
        catch (Exception e) {
            return new Result(ERROR, new ApplicationException("Encode operation finished with exception", e));
        }


        return new Result(OK);
    }


    public String encrypt(String text, String cryptoKey, int shift) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i); // конкретный символ из исходного текста

            int index = cryptoKey.indexOf(c); // индекс (номер позиции) исходного символа в строке шаблона "alphabet"

            if (index != -1) { // если символ обнаружен в строке шаблона "alphabet"
                int newIndex = (index + shift); // новый индекс (номер позиции) с учетом сдвига "shift"
                newIndex %= cryptoKey.length();

                result.append(cryptoKey.charAt(newIndex)); // добавление нового символа в результирующий текст
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
