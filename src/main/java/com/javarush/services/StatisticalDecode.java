package com.javarush.services;

import com.javarush.entity.Result;
import com.javarush.exception.ApplicationException;
import com.javarush.utilites.FileUtilite;

import java.util.*;

import static com.javarush.constants.ApplicationCompletionConstants.UNCRYPTED_FILE;
import static com.javarush.constants.CryptoAlphabet.ALPHABET;
import static com.javarush.repository.ResultCode.ERROR;
import static com.javarush.repository.ResultCode.OK;

public class StatisticalDecode implements Function{
    @Override
    public Result execute(String[] parameters) {

        try {
            //анализируем параметры и производим расшифровывание файла
            //нам нужны параметры: [3], [6]

            String cryptoFilePath = parameters[3];//путь к зашифрованному файлу
            String openTextFilePath = parameters[6];//путь к файлу с открытым текстом алфавита

            String cryptoText = FileUtilite.getFileContent(cryptoFilePath).toLowerCase();
            String openText = FileUtilite.getFileContent(openTextFilePath).toLowerCase();

            String unCryptedText = decrypt(openText, cryptoText);

            boolean result = FileUtilite.saveFileContent(UNCRYPTED_FILE, unCryptedText);

            System.out.println(result);
        }
        catch (Exception e) {
            return new Result(ERROR, new ApplicationException("Decode operation finished with exception", e));
        }

        return new Result(OK);
    }

    public String decrypt(String openText, String cryptoText) {

        //определяем частотность символов в открытом тексте
        Map<Character, Integer> openTextFrequencyMap = calculateCharacterFrequency(openText);
        List<Map.Entry<Character, Integer>> openTextSortedFrequencyList = sortFrequencyMap(openTextFrequencyMap);

        //определяем частотность символов в зашифрованном тексте
        Map<Character, Integer> cryptoTextFrequencyMap = calculateCharacterFrequency(cryptoText);
        List<Map.Entry<Character, Integer>> cryptoTextSortedFrequencyList = sortFrequencyMap(cryptoTextFrequencyMap);

        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < cryptoText.length(); i++) {
            char currentChar = cryptoText.charAt(i);



            int currentIndex = findIndex(cryptoTextSortedFrequencyList, currentChar);
            if(currentIndex == -1)
            {
                decryptedText.append(currentChar);
                continue;
            }

            decryptedText.append(openTextSortedFrequencyList.get(currentIndex).getKey());
        }

        return decryptedText.toString();
    }

    public static int findIndex(List<Map.Entry<Character, Integer>> list, char targetChar) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey() == targetChar) {
                return i;
            }
        }
        return -1; // Повертаємо -1, якщо символ не знайдено
    }

    public static Map<Character, Integer> calculateCharacterFrequency(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char ch : text.toCharArray()) {
            if(ALPHABET.contains(Character.toString(ch)))
               frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        return frequencyMap;
    }

    public static List<Map.Entry<Character, Integer>> sortFrequencyMap(Map<Character, Integer> frequencyMap) {
        List<Map.Entry<Character, Integer>> frequencyList = new ArrayList<>(frequencyMap.entrySet());
        frequencyList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return frequencyList;
    }
}
