package com.javarush.services;

import com.javarush.entity.Result;
import com.javarush.exception.ApplicationException;
import com.javarush.utilites.FileUtilite;

import static com.javarush.constants.ApplicationCompletionConstants.UNCRYPTED_FILE;
import static com.javarush.repository.ResultCode.ERROR;
import static com.javarush.repository.ResultCode.OK;

public class Decode implements Function {
    @Override
    public Result execute(String[] parameters) {

        try {
            //анализируем параметры и производим расшифровывание файла
            //нам нужны параметры: [3], [4], [5]

            String cryptedFilePath = parameters[3];//путь к зашифрованному файлу
            String key = parameters[4];//строка-ключ шифрования
            String shift = parameters[5];//заданный шаг-смещение для ключа шифрования, которым был зашифрован файл

            String inputText = FileUtilite.getFileContent(cryptedFilePath);
            String decryptedText = decrypt(inputText, key, Integer.valueOf(shift));

            boolean result = FileUtilite.saveFileContent(UNCRYPTED_FILE, decryptedText);

            System.out.println(result);
        }
        catch (Exception e) {
            return new Result(ERROR, new ApplicationException("Decode operation finished with exception", e));
        }


        return new Result(OK);
    }

    public String decrypt(String text, String cryptoKey, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i); // конкретный символ из исходного текста

            int index = cryptoKey.indexOf(c); // индекс (номер позиции) исходного символа в строке шаблона "alphabet"

            if (index != -1) { // если символ обнаружен в строке шаблона "alphabet"
                int newIndex = (index - shift); // новый индекс (номер позиции) с учетом сдвига "shift"
                newIndex %= cryptoKey.length();

                if(newIndex < 0) newIndex = cryptoKey.length() + newIndex; // на случай когда индекс с отрицательным знаком

                result.append(cryptoKey.charAt(newIndex)); // добавление нового символа в результирующий текст
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
