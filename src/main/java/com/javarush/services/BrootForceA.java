package com.javarush.services;

import com.javarush.entity.Result;
import com.javarush.exception.ApplicationException;
import com.javarush.utilites.FileUtilite;

import static com.javarush.constants.ApplicationCompletionConstants.UNCRYPTED_FILE;
import static com.javarush.constants.CryptoAlphabet.ALPHABET;
import static com.javarush.constants.CryptoAlphabet.PATTERN;
import static com.javarush.repository.ResultCode.ERROR;
import static com.javarush.repository.ResultCode.OK;

public class BrootForceA implements Function {
    public Result execute(String[] parameters) {
        try {
            // Аналізуємо параметри і проводимо дешифрування файлу
            String cryptedFilePath = parameters[3]; // Шлях до зашифрованого файлу
            String key = parameters[4]; // Рядок-ключ шифрування

            String inputText = FileUtilite.getFileContent(cryptedFilePath);

            boolean result = false;
            for (int shift = 0; shift < ALPHABET.length(); shift++) {
                String decryptedText = decrypt(inputText, key, Integer.valueOf(shift));
                if(checkTextIsGood(decryptedText))
                {
                    result = FileUtilite.saveFileContent(UNCRYPTED_FILE, decryptedText);
                    break;
                }
            }

            System.out.println(result);
        } catch (Exception e) {
            return new Result(ERROR, new ApplicationException("Decode operation finished with exception", e));
        }

        return new Result(OK);
    }

    private boolean checkTextIsGood(String decryptedText) {

        int count = 0;
        for(String word : PATTERN)
        {
            if(decryptedText.contains(word))
                count++;
        }

        if (count > 5)
            return true;

        return false;
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
